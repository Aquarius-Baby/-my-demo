package com.cmy.temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: cmy
 * @Date: Created in  2021/1/5 4:02 下午
 * @Description:
 */
public class patternTest {
    public static void main(String[] args) {
        String str = "http://10.3.7.48:2379";
        String ipPatternStr = "^http://((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?):[1-9]{1,}$";
        Pattern ipPattern = Pattern.compile(ipPatternStr);
        Matcher ipMatter = ipPattern.matcher(str);
        boolean ipFlag = ipMatter.find();
        System.out.println(ipFlag);

    }
}
