package com.cmy.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        int nThreads = 15;

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
        // 调用以下方法
        ExecutorService pool = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        // 线程池的使用
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new MyThread(i));
            fixedThreadPool.submit(new MyThread(i));
        }

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());


        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        new Executors.FinalizableDelegatedExecutorService
//                (new ThreadPoolExecutor(1, 1,
//                        0L, TimeUnit.MILLISECONDS,
//                        new LinkedBlockingQueue<Runnable>()));
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(nThreads);
    }
}

class MyThread implements Runnable {
    int i = 0;

    public MyThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "=====" + i);
    }

}