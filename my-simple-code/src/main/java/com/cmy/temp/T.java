package com.cmy.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: cmy
 * @Date: Created in  2021/1/28 11:31 上午
 * @Description:
 */
public class T {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        MyTree forest = new MyTree("", "");
        MyTree current = forest;
        List<String> list = new ArrayList<>();

        list.add("/");
        list.add("/bd-server/watch");
        list.add("/bd-server/watch/redis");
        list.add("/bd-server/watch/mysql");
        list.add("/bd-server/watch/mysql/scrm");
        list.add("/bd-server/watch/mysql/bundle_db");
        list.add("/bd-server/business/scrm");
        list.add("/bd-server/business/main");

        for (String tree : list) {
            MyTree root = current;
            for (String data : tree.split("/")) {
                current = current.child(data, tree);
            }
            current = root;
        }
        System.out.println(1);
    }
}


/**
 * @author cmy
 * @date 2020/7/6 10:26 上午
 */

class MyTree implements Comparable {

    private Set<MyTree> children = new TreeSet<>();

    private String name;
    private String completePath;

    public MyTree(String name, String completePath) {
        this.name = name;
        this.completePath = completePath;
    }

    public MyTree child(String name, String completePath) {
        for (MyTree child : children) {
            if (child.name.equals(name)) {
                return child;
            }
        }
        if (this.completePath.equals("/")) {
            return child(new MyTree(name, "/" + name));
        } else {
            return child(new MyTree(name, this.completePath + "/" + name));
        }
    }

    MyTree child(MyTree child) {
        children.add(child);
        return child;
    }

    public Set<MyTree> getChildren() {
        return children;
    }

    public void setChildren(Set<MyTree> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompletePath() {
        return completePath;
    }

    public void setCompletePath(String completePath) {
        this.completePath = completePath;
    }

    @Override
    public int compareTo(Object o) {
        MyTree tree1 = (MyTree) o;
        String name1 = tree1.getName();
        return this.getName().compareToIgnoreCase(name1);
    }
}

