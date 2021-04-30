package com.cmy.temp.factory;

/**
 * @Author: cmy
 * @Date: Created in  2021/4/25 5:18 下午
 * @Description:
 */
public class FactoryDemo {
    public static void main(String[] args) {
        ChildFactory childFactory = new ChildFactory();
        ChildPlay boy = childFactory.getChild("boy");
        ChildPlay girl = childFactory.getChild("girl");
        boy.play();
        girl.play();
    }
}
