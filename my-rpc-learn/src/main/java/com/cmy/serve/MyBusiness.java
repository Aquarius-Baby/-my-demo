package com.cmy.serve;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * 接口
 */
public interface MyBusiness extends VersionedProtocol {

    public static long versionID = 1;

    public String sayHello(String name);
}
