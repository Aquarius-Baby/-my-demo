package com.cmy.temp;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 4:06 下午
 * @Description:
 */
public class conslu {
    public static void main(String[] args) {
        long init = 1000;
        double a = 0.12;

        int year = 5;
        double res = 1;
        for (int i = 1; i <= year; i++) {
            res = res * (1 + a);
            System.out.println(String.format("%s: %s", i, res));
        }
    }
}
