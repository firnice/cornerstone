package com.cornerstone.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: liyl
 * @date: 2018/2/23 下午5:16
 * @since 1.0.0
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //只看一个结果
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

}
