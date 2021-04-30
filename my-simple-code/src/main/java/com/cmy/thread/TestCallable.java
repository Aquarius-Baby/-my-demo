package com.cmy.thread;

import org.testng.ITest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: cmy
 * @Date: Created in  2021/3/8 3:36 下午
 * @Description:
 */

class TestCallable implements Callable {
    private int taskNum;

    public TestCallable(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * call方法的实现，主要用于执行线程的具体实现，并返回结果
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        System.out.println(">>> " + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>> " + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }

    //1，2主要区别是创建线程的方式
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
        test2();
    }

    /**
     * 使用Executors.newFixedThreadPool创建线程池
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void test1() throws InterruptedException, ExecutionException {
        System.out.println("---- test1 程序开始运行 ----");
        Date date1 = new Date();
        int taskSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new TestCallable(i);
            Future f = pool.submit(c);
            list.add(f);
        }
        pool.shutdown();
        for (Future f : list) {
            System.out.println(">>>" + f.get().toString()); //OPTION + return 抛异常
        }
        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

    /**
     * 线程直接使用new Thread来创建
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test2() throws ExecutionException, InterruptedException {
        System.out.println("---- test2 程序开始运行 ----");
        Date date1 = new Date();
        int taskSize = 5;
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            FutureTask task = new FutureTask(new TestCallable(i));
            list.add(task);
            Thread t = new Thread(task);
            t.start();
        }
        for (Future f : list) {
            System.out.println(">>>" + f.get().toString()); //OPTION + return 抛异常
        }
        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

}