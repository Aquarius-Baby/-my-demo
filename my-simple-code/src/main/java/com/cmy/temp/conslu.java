package com.cmy.temp;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 4:06 下午
 * @Description:
 */
public class conslu {
    public static void main(String[] args) {
        coinBCH(3000, 3500);
        coinBCH(3000, 4000);
        coinBCH(3000, 4500);

        System.out.println("============");
        coinBCH(4000, 5000);

        //        test1(2021, 2043, 2043);
    }

    static void coinBCH(double current, double except) {
        double res = (2000 - 0.1259) / (except - current);
        System.out.println(res * current);
    }

    /**
     * 按年计算
     *
     * @param startYear 开始存入年份
     * @param endYear   停止存入年份
     * @param stopYear  开始取出年份
     */
    static void test1(int yearMoney, int startYear, int endYear, int stopYear) {
        double yearProportion = 0.1;
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


    static void test2(int yearMoney, int yearProportion, int startYear, int endYear, int stopYear, int yearCost) {
        int totalCost = 0;
        int summary = 0;

        for (int year = startYear; year < endYear; year++) {
//            if (year < stopYear) {
//
//                total = total * (1 + yearProportion) + principal;
//                principalTotal = principalTotal + principal;
//                System.out.println(String.format("%s年末 -- %s: 投入 %s  余额：%s", year, year - 1993, principalTotal, total));
//            } else {
//                total = total * (1 + yearProportion);
//                total = total - yearCost;
//                System.out.println(String.format("%s年末 -- %s: 支出 %s  余额：%s", year, year - 1993, yearCost, total));
//            }
        }

    }
}
