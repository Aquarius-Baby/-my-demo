package com.cmy.temp;

/**
 * @Author: cmy
 * @Date: Created in  2021/1/14 1:44 下午
 * @Description:
 */
public class Food {
    public static void main(String[] args) {
        double weight = 55;
        double totalCalories = weight * 30;
        double carbohydrateCalories = totalCalories * 0.55;
        double proteinCalories = totalCalories * 0.15;
        double fatCalories = totalCalories * 0.3;

        // 粗粮细粮各一半
        double carbohydrateWeight = carbohydrateCalories /4;
        // 动植物蛋白质应该各占一半
        // 1个鸡蛋、300毫升牛奶和约150克肉类或鱼类（相当于3两)
        double proteinWeight = proteinCalories /4;
        // 植物油占一半，为每天35克。动物油
        double fatWeight = fatCalories / 9 ;
        System.out.println(String.format("碳水：%.3f 蛋白质：%.3f 油脂：%.3f", carbohydrateWeight,proteinWeight,fatWeight));

    }

    void document() {
        //地中海膳食结构
        //
        //碳水化合物、蛋白质、脂类、维生素、矿物质、膳食纤维和水
        //
        //
        //
        //能量消耗主要有以下三个方面：基础代谢、运动和食物消化
        //
        //
        //
    }
}
