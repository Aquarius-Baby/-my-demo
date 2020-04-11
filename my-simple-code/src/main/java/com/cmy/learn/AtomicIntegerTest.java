package com.cmy.learn;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicIntegerTest implements Runnable {

    static AtomicInteger num;
//    static int num;

    public static void main(String[] args) {
//        num = 1;
        num = new AtomicInteger();
        num.set(1);
        AtomicIntegerTest thread = new AtomicIntegerTest();
        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);
        thread1.setName("A:");
        thread2.setName("B:");
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        while (num.get() < 100) {
            increase2();
        }
    }
//    @Override
//    public void run() {
//        while (num < 100) {
//            increase();
//        }
//    }

//    private synchronized void increase(){
//        System.out.println(Thread.currentThread().getName() + " " + num);
//        num++;
//    }

    private void increase2() {
        System.out.println(num.getAndIncrement());


        ReentrantLock reentrantLock = new ReentrantLock();
    }

}
