package com.dl.base;

/*
在给变量进行赋值的时候，如果右侧的表达式当中全都是常量，没有任何变量，
那么编译器javac将会直接将若干个常量表达式计算得到结果。
short result = 5 + 8; // 等号右边全都是常量，没有任何变量参与运算
编译之后，得到的.class字节码文件当中相当于【直接就是】：
short result = 13;
右侧的常量结果数值，没有超过左侧范围，所以正确。

这称为“编译器的常量优化”。

但是注意：一旦表达式当中有变量参与，那么就不能进行这种优化了。
*/
public class Demo13Notice {

    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 8;

        short result = 5+8;             //编译器的常量优化
        System.out.println(result);

//        short result2 =  num1 + num2;//错误，需要强转
        short result2 = (short) (num1 + num2);
    }

}