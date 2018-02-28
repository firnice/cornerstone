package com.cornerstone.thread;

import java.util.concurrent.Callable;
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
public class ExecutorsTest2 {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(3);

        Future future1 = e.submit(new A("a", 5));
        Future future2 = e.submit(new A("b", 6000));
        Future future3 = e.submit(new A("c", 7000));

        String name = "";
        while (true) {
            //System.out.println(finishName);
            if (!"".equals(finishName)) {
                name = finishName;
                break;
            }

        }

        System.out.println(name + " 线程执行完");

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
                done(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + name + "执行完毕");
            return name;
        }
    }

    public static synchronized void done(String name) {
        if ("".equals(finishName)) {
            finishName = name;
            System.out.println("finishName_"+name);
        }
        return;
    }

    //加volatile 防止出线程问题
    public volatile static String finishName = "";
}
