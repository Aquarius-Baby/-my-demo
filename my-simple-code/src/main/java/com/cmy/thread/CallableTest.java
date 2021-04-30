package com.cmy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: cmy
 * @Date: Created in  2021/3/8 3:23 下午
 * @Description:
 */
public class CallableTest {

    public static void main(String[] args) {
//
//        ExecutorService pool = new ThreadPoolExecutor(5, 200,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable c = new MyCallable(i + " ");
            Future f = pool.submit(c);
            list.add(f);
        }
        pool.shutdown();
        for (Future f : list) {
            try {
                System.out.println("res：" + f.get().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class MyCallable implements Callable {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MyCallable() {

        }

        public MyCallable(String name) {
            this.name = name;
        }


        @Override
        public Object call() throws Exception {
            return name + "end。。。。。";
        }

//        @Override
//        public void run() {
//            System.out.println(name + "is Running");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
