package com.cornerstone.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyl
 * @date: 2018/2/23 下午5:16
 * @since 1.0.0
 */
public class CountDownLatchTest {
    static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //只看一个结果
        try {
            Future future1 = executorService.submit(new A("a", 1000));
            Future future2 = executorService.submit(new A("b", 7000));
            Future future3 = executorService.submit(new A("c", 7000));
            System.out.println("执行。。。");
            countDownLatch.await(8, TimeUnit.SECONDS);
            System.out.println("全部执行完");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + name + "执行完毕");
            System.out.println(countDownLatch.getCount());
            return name;
        }
    }
}
