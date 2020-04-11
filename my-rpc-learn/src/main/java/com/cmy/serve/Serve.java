package com.cmy.serve;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

public class Serve {

    public static void main(String[] args) throws IOException {
        //创建一个RPC serve
        System.setProperty("hadoop.home.dir", "/Users/wgf/code/hadoopPath");
        RPC.Builder builder = new RPC.Builder(new Configuration());
        // address and port
        builder.setBindAddress("localhost");
        builder.setPort(9090);
        // 发布我的服务
        // 定义的接口
        builder.setProtocol(MyBusiness.class);
        //实际的实现

        builder.setInstance(new MyBusinessImpl());
        //创建serve
        Server server = builder.build();

        // 启动  serve
        server.start();

    }

}
