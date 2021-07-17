package com.dl.base;

/*
数字和字符 的对照关系表（编码表）：

ASCII码表：American Standard Code for Information Interchange，美国信息交换标准代码。 0 -127
Unicode码表：万国码。

48 - '0'
65 - 'A'
97 - 'a'
*/
public class Demo03DataTypeChar {
    public static void main(String[] args) {
        //如果只是打印，不运算，输出结果就是字符
        //如果发生了运算，结果就是对应的数值

        char c1 = 'b';
        System.out.println(c1);
        char c2 = 98;
        System.out.println(c2);

        System.out.println(c1+5);
        System.out.println(c2+5);

        char c3 = '中';
        System.out.println(c3);
        System.out.println(c3+0);

        char c4 = '騦';
        System.out.println(c4+0);

    }
}