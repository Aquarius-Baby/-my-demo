package com.cmy.temp;

import java.util.Map;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 4:06 下午
 * @Description:
 */
public class conslu {
    public static void main(String[] args) {
        int startYear = 2021;
        int endYear = 2043;
        int stopYear = 2043;
        test(20, 2500);
//        System.out.println("====================");
//        test1(startYear, endYear, stopYear);
    }

    /**
     * 按月计算
     *
     * @param year
     * @param principal
     */
    static void test(int year, int principal) {
        double monthProportion = 0.0095;
        double total = 0;
        double principalTotal = principal;

        int month = 12 * year;
        for (int i = 1; i <= month; i++) {
            total += principal;
            total = total * (1 + monthProportion);
            if (i % 12 == 0) {
                System.out.println(String.format("%s 年: 投入 %s  余额：%s", i / 12, principalTotal, total));
            }
            principalTotal = principalTotal + principal;
        }
    }

    /**
     * 按年计算
     *
     * @param startYear
     * @param endYear
     * @param stopYear
     */
    static void test1(int startYear, int endYear, int stopYear) {
        double yearProportion = 0.1;
        // 3
        double principal = 3;
        double total = 0;
        double principalTotal = 0;
        double yearCost = 20;
        for (int year = startYear; year < endYear; year++) {
            if (year < stopYear) {
                total = total * (1 + yearProportion) + principal;
                principalTotal = principalTotal + principal;
                System.out.println(String.format("%s年末 -- %s: 投入 %s  余额：%s", year, year - 1993, principalTotal, total));
            } else {
                total = total * (1 + yearProportion);
                total = total - yearCost;
                System.out.println(String.format("%s年末 -- %s: 支出 %s  余额：%s", year, year - 1993, yearCost, total));
            }
        }
    }
}
