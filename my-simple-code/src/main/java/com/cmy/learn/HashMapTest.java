package com.cmy.learn;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(1, 1);

        linkTest();
    }

    private static void linkTest() {

        LinkedHashMap linkedHashMap = new LinkedHashMap(12, 0.75f, true);
        linkedHashMap.put(1, 'a');
        linkedHashMap.put(2, 'b');

        linkedHashMap.get(1);
    }

    private static void LFUTest() {

    }


}


