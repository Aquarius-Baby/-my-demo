package com.cmy.generatorCode;

import org.mybatis.generator.api.ShellRunner;


/**
 * @Author: cmy
 * @Date: Created in  2021/1/28 11:31 上午
 * @Description:
 */
public class T {
    public static void main(String[] args) {
        String config = T.class.getClassLoader()
                .getResource("generatorConfig.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
}