package lock_support;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    static ChangeObjectThread t3 = new ChangeObjectThread("t3");
    static ChangeObjectThread t4 = new ChangeObjectThread("t4");
    static ChangeObjectThread t5 = new ChangeObjectThread("t5");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override
        @SneakyThrows
        public void run() {

                System.out.println("-------"+"in  "+ getName());
                sleep(10 * 1000);
                System.out.println("醒了");
                LockSupport.park();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("-------"+getName()+" 被中断了");
                }
                System.out.println("-------"+getName()+" 继续执行");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        System.out.println("线程1已经开启，接下来睡眠3秒钟");
        t1.interrupt();
        Thread.sleep(3000L);
        System.out.println("线程1睡眠完毕");

        System.out.println("=============================");
        t2.start();
        System.out.println("线程2已经开启，接下来睡眠3秒钟");
        Thread.sleep(3000L);
        System.out.println("线程2睡眠完毕，接来下打断线程1");
        t1.interrupt();
        System.out.println("线程1已打断，接下来unparkt线程2");
        LockSupport.unpark(t2);
        System.out.println("线程2unparkt完毕");

        System.out.println("=========================");
        LockSupport.unpark(t3);
        System.out.println("t3先被unpark再启动，看会不会被park");
        t3.start();
        System.out.println("t3已经启动");

        System.out.println("=========================");
        System.out.println("尝试再次启动t2");
        //这里会报错的，不能再次start
        //t2.start();

        System.out.println("=========================");
        t4.start();
        System.out.println("第一次已start，开始第二次start");
        //连续start也会出错
        //t4.start();
        System.out.println("t4被启动");
        t4.interrupt();
        // 这里重新启动会报错的
        // t4.start();

        t3.join();
        t1.join();
        t2.join();
    }
}