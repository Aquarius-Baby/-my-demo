package com.cmy.temp.factory;

/**
 * @Author: cmy
 * @Date: Created in  2021/4/25 5:17 下午
 * @Description:
 */
public class ChildFactory {
    public ChildPlay getChild(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("boy")) {
            return new Boy();
        } else if (type.equalsIgnoreCase("girl")) {
            return new Girl();
        }
        return null;
    }
}
