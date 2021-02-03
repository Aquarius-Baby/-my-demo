package com.cmy.learn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 如果一个数据在最近一段时间没有被访问到，那么可以认为在将来它被访问的可能性也很小。因此，当空间满时，最久没有访问的数据最先被置换（淘汰）
 *
 * @Author: cmy
 * @Date: Created in  2021/1/26 7:53 下午
 * @Description:
 */
class LRU<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    public LRU(int cacheSize) {
        super(16, (float) 0.75, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] args) {
        LRU<String, String> lru = new LRU<String, String>(3);
        lru.put("1","a");
        lru.put("2","b");
        lru.put("3","c");

        System.out.println(lru.toString());

        lru.put("4","d");
        System.out.println(lru.toString());
        lru.put("1","d");
        System.out.println(lru.toString());
    }
}