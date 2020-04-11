package com.cmy.databasePool;

import java.sql.*;

public class PooledConnection {
    /**
     * 连接管道对象
     */
    private Connection conn;

    /**
     * 连接状态，true-繁忙，false-空闲
     */
    private boolean isBusy = false;
    private String name;

    public PooledConnection(Connection conn, boolean isBusy) {
        this.conn = conn;
        this.isBusy = isBusy;
    }

    public void close() {
        this.isBusy = false;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Discription:创建查询方法,用于测试使用</p>
     *
     * @param sql
     * @return
     * @author : lcma
     * @update : 2016年12月5日下午11:19:18
     */
    public ResultSet queryBySql(String sql) {
        ResultSet rs = null;
        Statement sm = null;
        try {
            conn.setAutoCommit(false);
            sm = conn.createStatement();
            rs = sm.executeQuery(sql);
            conn.commit();
        } catch (SQLException e) {
//            conn.rollback();
//            sm.close();
//            conn.close();
            e.printStackTrace();
        }
        return rs;
    }

}