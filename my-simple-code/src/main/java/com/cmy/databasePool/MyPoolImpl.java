package com.cmy.databasePool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class MyPoolImpl implements IMyPool {
    /**
     * 驱动名称
     */
    private String jdbcDriver;
    /**
     * 连接地址
     */
    private String jdbcUrl;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 初始化连接数
     */
    private int initCount;
    /**
     * 步进连接数
     */
    private int stepSize;
    /**
     * 最大连接数
     */
    private int poolMaxSize;

    /**
     * 连接池容器
     */
    private static Vector<PooledConnection> pooledConnections = new Vector<PooledConnection>();

    /**
     * 构造函数，执行初始化方法
     */
    public MyPoolImpl() {
        init();
    }

    /**
     * <p>Discription:初始化连接池</p>
     *
     * @author : lcma
     * @update : 2016年12月5日下午8:42:37
     */
    private void init() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDriver = pro.getProperty("jdbcDriver");
        jdbcUrl = pro.getProperty("jdbcUrl");
        userName = pro.getProperty("userName");
        password = pro.getProperty("password");
        initCount = Integer.parseInt(pro.getProperty("initCount"));
        stepSize = Integer.parseInt(pro.getProperty("stepSize"));
        poolMaxSize = Integer.parseInt(pro.getProperty("poolMaxSize"));

        try {
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
            //将driver注册
            DriverManager.registerDriver(driver);
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建连接
        createConnection(initCount);
    }

    /**
     * Discription:创建连接
     *
     * @param count
     */
    @Override
    public void createConnection(int count) {
        if (poolMaxSize <= 0 || pooledConnections.size() + count > poolMaxSize) {
            System.out.println("创建连接失败，超过最大连接数");
            Thread.yield();
//            throw new RuntimeException("创建连接失败，超过最大连接数");
            return;
        }
        try {
            //循环创建连接
            int size = pooledConnections.size();
            for (int i = 0; i < count; i++) {
                //创建连接
                Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
                //实例化连接池中的连接
                PooledConnection pooledConnection = new PooledConnection(connection, false);
                pooledConnection.setName("conn:" + (size + i));
                //存入连接池容器
                pooledConnections.add(pooledConnection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Discription:获取连接
     */
    @Override
    public PooledConnection getConnection() {
        if (pooledConnections.size() <= 0) {
            System.out.println("获取连接失败，连接池为空");
//            throw new RuntimeException("获取连接失败，连接池为空");
        }
        PooledConnection connection = getRealConnection();
        //判断是否为空
        while (connection == null) {
            //创建connection，步进数
            createConnection(stepSize);
            //重新获取连接，有可能获取的还为空,采用while循环判断
            getRealConnection();
            //防止其他线程过来拿连接
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //判断我们是否拿到有效的连接对象
    private synchronized PooledConnection getRealConnection() {
        //先判断连接池是不是有我们需要的空闲连接对象
        for (PooledConnection connection : pooledConnections) {
            //未处于繁忙状态
            if (!connection.isBusy()) {
                Connection conn = connection.getConn();
                try {
                    //判断这个连接是不是有效，isValid就是创建了一个statement，执行sql语句，看是否成功
                    if (!conn.isValid(2000)) {
                        Connection validConn = DriverManager.getConnection(jdbcUrl, userName, password);
                        connection.setConn(validConn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //设置为繁忙
                connection.setBusy(true);
                return connection;
            }
        }
        return null;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitCount() {
        return initCount;
    }

    public void setInitCount(int initCount) {
        this.initCount = initCount;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public void setPoolMaxSize(int poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }

}
