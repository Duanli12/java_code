package com.dl.base;

/*
当数据类型不一样时，将会发生数据类型转换。

自动类型转换（隐式）
	1. 特点：代码不需要进行特殊处理，自动完成。
	2. 规则：数据范围从小到大。

强制类型转换（显式）
*/
public class Demo01DataType {
    public static void main(String[] args) {

        System.out.println(10);//10是什么类型？，int类型
        System.out.println(10.0);//10.0是什么类型，double类型

        //左边是long类型，右边是int类型，不一致，会发生类型转换
        //int -> long,
        //小 -> 大
        //自动类型转换
        long num1 = 100;
        System.out.println(num1);

        //float ->double
        //小 -> 大
        //自动类型转换
        double num2 = 2.5F;
        System.out.println(num2);

        //long -> float
        //小 -> 大
        //自动类型转换
        float num3 = 30L;
        System.out.println(num3);




    }

}