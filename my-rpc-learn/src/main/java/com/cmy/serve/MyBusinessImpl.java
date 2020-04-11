package com.cmy.serve;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

/**
 * 具体实现的接口
 */
public class MyBusinessImpl implements MyBusiness {
    @Override
    public String sayHello(String name) {
        System.out.println("service method ");
        return "hello!!!" + name;
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return MyBusiness.versionID;
    }

    /**
     * 创建签名
     *
     * @param s
     * @param l
     * @param i
     * @return
     * @throws IOException
     */
    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return new ProtocolSignature(MyBusiness.versionID, null);
    }
}
