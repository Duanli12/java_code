package com.dl.base;

/*
四则运算当中的加号“+”有常见的三种用法：

1. 对于数值来说，那就是加法。
2. 对于字符char类型来说，在计算之前，char会被提升成为int，然后再计算。
char类型字符，和int类型数字，之间的对照关系表：ASCII、Unicode
3. 对于字符串String（首字母大写，并不是关键字）来说，加号代表字符串拼接操作。
任何数据类型和字符串进行相+的时候，结果都会变成字符串
*/
public class Demo05Plus {

    //A:65
    public static void main(String[] args) {
        char c1 = 'Y';
        byte b1 = 20;
        int num =  c1+b1;
        System.out.println(num);

        String s1 = "hello";
        System.out.println(s1);

        System.out.println(s1+"world");

        String s2 = "java";
        System.out.println(s2 + 20);
//                         "java20"
        System.out.println(s2 + 20 + 30);

        System.out.println(s2 + (20 + 30));


    }
}