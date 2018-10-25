package com.cs.console.service;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.junit.Test;

import java.io.IOException;

public class VideoServiceTest {

    @Test
    public void test() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/usr/local/bin/ffmpeg");
        FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");


        String inFilename = "/Users/yiruike/Downloads/9.mp4";
        FFmpegProbeResult in = ffprobe.probe(inFilename);

        FFmpegBuilder builder =
                new FFmpegBuilder()
//                        .setInput(inFilename) // Filename, or a FFmpegProbeResult
                        .setInput(in)
                        .overrideOutputFiles(true) // Override the output if it exists
                        .addOutput("output.mp4") // Filename for the destination
                        .setFormat("mp4") // Format is inferred from filename, or can be set
//                        .setTargetSize(1024_000) // Aim for a 250KB file
                        .setVideoBitRate(1000000)
                        .disableSubtitle() // No subtiles
                        .setAudioChannels(1) // Mono audio
                        .setAudioCodec("aac") // using the aac codec
                        .setAudioSampleRate(48_000) // at 48KHz
                        .setAudioBitRate(32768) // at 32 kbit/s
                        .setVideoCodec("libx264") // Video using x264
                        .setVideoFrameRate(30) // at 24 frames per second
//                        .setVideoResolution(1280, 720) // at 640x480 resolution
                        .setVideoFilter("scale=-1:480")
                        .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                        .done();


        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();

// Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run();
    }

}
