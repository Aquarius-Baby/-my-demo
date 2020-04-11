package com.cmy.client;

import com.cmy.rpc.serve.MyBusiness;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //调用serve 服务

        try {
            System.setProperty("hadoop.home.dir", "/Users/wgf/code/hadoopPath");

            MyBusiness myBusiness = RPC.getProxy(MyBusiness.class, // 调用的接口
                    MyBusiness.versionID, //版本id
                    new InetSocketAddress("localhost", 9090), // 服务器地址,端口
                    new Configuration()  //配置文件
            );
            System.out.println(myBusiness.sayHello("Lily"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
