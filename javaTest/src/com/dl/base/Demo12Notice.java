package com.dl.base;

/*
对于byte/short/char三种类型来说，如果右侧赋值的数值没有超过范围，
那么javac编译器将会自动隐含地为我们补上一个(byte)(short)(char)。

1. 如果没有超过左侧的范围，编译器补上强转。
2. 如果右侧超过了左侧范围，那么直接编译器报错。
*/
public class Demo12Notice {
    public static void main(String[] args) {
        byte num1 = /*(byte)*/ 127;
//        byte num1 = 128;//超过byte取值范围，报错

        short num2 = 30000;

    }

}