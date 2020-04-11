package com.cmy.thread;

public class TicketThread {

    public static void main(String[] args) {
        // 定义资源对象
        Resource r = new Resource();
        //定义一个生产者和一个消费者
        Producer p = new Producer(r);
        Consumer c = new Consumer(r);
        //启动四个线程，2个负责生产者，两个消费者
        Thread t1 = new Thread(p);
        t1.setName(" producer 1 : ");
        Thread t2 = new Thread(p);
        t2.setName(" producer 2 : ");

        Thread t3 = new Thread(c);
        t3.setName(" consumer 1 : ");

        Thread t4 = new Thread(c);
        t4.setName(" consumer 2 : ");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

//商品类
class Resource {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //产生商品
    public synchronized void add() {
        while (count == 10) {
            try {
                System.out.println(Thread.currentThread().getName() + " 已经有10张票了");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "   剩余 " + count);
        this.notifyAll();
    }

    //取出商品
    public synchronized void sail() {
        while (count == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 没有票了 ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " 剩余 " + count);
        this.notifyAll();
    }
}

//定义生产者
class Producer implements Runnable {

    private Resource res;

    public Producer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.add();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//定义消费者
class Consumer implements Runnable {

    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.sail();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}