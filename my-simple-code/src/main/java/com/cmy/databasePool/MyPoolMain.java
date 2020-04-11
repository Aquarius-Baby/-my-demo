package com.cmy.databasePool;

import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyPoolMain {
    /**
     * 获取连接池容器实现类
     */
    private static MyPoolImpl poolImpl = PoolManager.getInstace();

    /**
     * 单个连接查询测试
     */
    public static void selectData() {
        PooledConnection connection = poolImpl.getConnection();
        ResultSet rs = connection.queryBySql("select * from miaosha_goods");
        try {
            System.out.println("==========" + Thread.currentThread().getName() + "=============");
            System.out.println(connection.getName());
            while (rs.next()) {
                System.out.print(rs.getString("id") + "\t\t");
                System.out.println("stock_count:" + rs.getString("stock_count") + "\t\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            connection.close();
        }
    }

    /**
     * <p>Discription:测试2000个线程</p>
     *
     * @param args
     * @author : lcma
     * @update : 2016年12月5日下午11:44:18
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    selectData();
                }
            }).start();
        }

    }

}
