package com.cmy.thread;

public class TwoThread implements Runnable {

    static int i = 1;

    public static void main(String[] args) throws InterruptedException {
        TwoThread twoThread = new TwoThread();
        Thread thread1 = new Thread(twoThread);
        Thread thread2 = new Thread(twoThread);
        Thread thread3 = new Thread(twoThread);
        thread1.setName("thread 1 : ");
        thread2.setName("thread 2 : ");
        thread3.setName("thread 3 : ");

        thread2.start();
        thread1.start();

        thread2.join();
        thread3.start();
    }

    @Override
    public void run() {
        for (int j = 1; j <= 10; j++) {
            System.out.println(Thread.currentThread().getName() + "   " + j);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
