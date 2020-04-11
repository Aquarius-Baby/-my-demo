package com.cmy.learn;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = Stream.of("a", "b", "c")
                .collect(Collectors.toList());


        List<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(0, 2);
        for (Integer t : list2) {

        }
    }
}
