package com.cornerstone.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: liyl
 * @date: 2018/2/24 上午10:57
 * @since 1.0.0
 *
 * 三个线程 只要有一个返回结果就执行下一步
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(3);

        Future future1 = e.submit(new A("a", 5000));
        Future future2 = e.submit(new A("b", 6000));
        Future future3 = e.submit(new A("c", 7000));

        String name = "";
        while (true) {

            try {
                if (future1.isDone()) {
                    name = (String)future1.get();
                    break;
                }
                if (future2.isDone()) {
                    name = (String)future2.get();
                    break;
                }
                if (future3.isDone()) {
                    name = (String)future3.get();
                    break;
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }

        }

        System.out.println(name+" 线程执行完");

    }

    public static class A implements Callable {

        private String name;

        private int sleep;

        public A(String name, int sleep) {
            this.name = name;
            this.sleep = sleep;
        }

        @Override
        public Object call() throws Exception {
            System.out.println("正在执行task " + name);
            try {
                Thread.currentThread().sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + name + "执行完毕");
            return name;
        }
    }
}
