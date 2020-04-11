package com.cmy.learn;

public class SwapInteger {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = a;

        System.out.println(a == b);
        System.out.println(a == c);
//        System.out.println("before swap : " + a +"    "+b);
//
//        swap(a,b);
//        System.out.println("after swap : " + a +"    "+b);
    }

    static void swap(Integer a, Integer b) {
        System.out.println("enter swap : " + a + "    " + b);

        Integer c = new Integer(a);
        a = new Integer(b);
        b = c;

        System.out.println("swap end: " + a + "    " + b);

    }
}
