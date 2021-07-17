package com.dl.base;

/*
运算符：进行特定操作的符号。例如：+
表达式：用运算符连起来的式子叫做表达式。例如：20 + 5。又例如：a + b

四则运算：
加：+
减：-
乘：*
除：/

取模（取余数）：%

首先计算得到表达式的结果，然后再打印输出这个结果。
复习一下小学三年级的除法公式：
被除数 / 除数 = 商 ... 余数

对于一个整数的表达式来说，除法用的是整除，整数除以整数，结果仍然是整数。只看商，不看余数。
只有对于整数的除法来说，取模运算符才有余数的意义。如果存在小数，就可能没有余数的概念了

注意事项：
	1. 一旦运算当中有不同类型的数据，那么结果将会是数据类型范围大的那种。
*/
public class Demo04Operator {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a/b);

        System.out.println(a/3);//取整（取商）
        System.out.println(a%3);//取模（取余）

        //取整只在运算符两边都是整数时才生效，如果存在一边有浮点数，那么结果就是取精确值
        System.out.println(a/3.0); //3.3333333333333335
        System.out.println(a%3.2);

        //浮点数float、double只能表示一个近似值，不能表示精确的值，可能存在误差
        System.out.println(a==5);
        System.out.println(a==10);

        //编译器底层是二进制，二进制有不能精确表示的数
        //十进制有没有不能精确表示的数？ 1/3，0.333333333333333333
        System.out.println("===============================");
        System.out.println(1.1 == 1.099999);//false
        System.out.println(1.1 == 1.09999999999999999999);//true

        int x = 20;

        float result = (float) (x + 2.5);
        System.out.println(result);
    }
}