package com.cmy.databasePool;

public class PoolManager {
    private static class creatPool {
        private static MyPoolImpl poolImpl = new MyPoolImpl();
    }

    //多个线程在加载内部类的时候线程是互斥的，所以用单例模式的内部类形式避免线程混乱
    public static MyPoolImpl getInstace() {
        return creatPool.poolImpl;
    }

}
