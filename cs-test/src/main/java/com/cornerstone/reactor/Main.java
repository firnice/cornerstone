package com.cornerstone.reactor;

import java.io.IOException;

/**
 * @author: liyl
 * @date: 2018/2/11 下午3:50
 * @since 1.0.0
 */
public class Main {

    public static void main(String[] args) {
        try {
            Reactor reactor = new Reactor(1333);
            new Thread(reactor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
