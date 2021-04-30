package com.cmy.temp;

import java.util.*;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 4:06 下午
 * @Description:
 */
public class conslu {
    public static void main(String[] args) {
        String[] deadends = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target = "8888";

//        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
//        String target = "0202";

        int res = openLock(deadends, target);
        System.out.println(res);
    }

    public static int openLock(String[] deadends, String target) {
        // 1.
        List<String> deadendList = Arrays.asList(deadends);
        String init = "0000";

        int step = 0;
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        list.add(init);
        Set<String> visit = new HashSet<>();
        visit.add(init);
        while (list.size() > 0) {
            String num = list.pop();
            if (deadendList.contains(num)) {
                continue;
            }
            if (target.equals(num)) {
                return step;
            }
            // 对nums进行 + -
            change(visit, num, list2);
            if (list.size() == 0) {
                step++;
                list.addAll(list2);
                list2 = new LinkedList<>();
            }
        }
        return -1;
    }

    public static void change(Set<String> visit, String num, LinkedList<String> list2) {
        int[] array = new int[4];
        for (int i = 0; i < num.length(); i++) {
            array[i] = num.charAt(i) - '0';
        }
        for (int i = 0; i < num.length(); i++) {
            // add
            int old = array[i];
            if (old == 9) {
                array[i] = 0;
            } else {
                array[i]++;
            }
            String n = getString(array);
            if (!visit.contains(n)) {
                list2.add(n);
                visit.add(n);
            }
            array[i] = old;
        }
        for (int i = 0; i < num.length(); i++) {
            // add
            int old = array[i];
            if (old == 0) {
                array[i] = 9;
            } else {
                array[i]--;
            }
            String n = getString(array);
            if (!visit.contains(n)) {
                list2.add(n);
                visit.add(n);
            }
            array[i] = old;
        }
    }

    public static String getString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append("").append(i);
        }
        return sb.toString();
    }
}
