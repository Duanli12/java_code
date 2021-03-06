package com.dl.base;

/*
自增运算符：++
自减运算符：--

基本含义：让一个变量涨一个数字1，或者让一个变量降一个数字1
使用格式：写在变量名称之前，或者写在变量名称之后。例如：++num，也可以num++
使用方式：
	1. 单独使用：不和其他任何操作混合，自己独立成为一个步骤。
	2. 混合使用：和其他操作混合，例如与赋值混合，或者与打印操作混合，等。
使用区别：
	1. 在单独使用的时候，前++和后++没有任何区别。也就是：++num;和num++;是完全一样的。
	2. 在混合的时候，有【重大区别】
		A. 如果是【前++】，那么变量【立刻马上+1】，然后拿着结果进行使用。	【先加后用】
		B. 如果是【后++】，那么首先使用变量本来的数值，【然后再让变量+1】。	【先用后加】
		
注意事项：
	只有变量才能使用自增、自减运算符。常量不可发生改变，所以不能用。
*/
public class Demo06Operator {
    public static void main(String[] args) {
        int num1 = 10;

        //num1 = num1 + 1 ;
        ++num1;//和num1++效果一致
        System.out.println(num1);//11

        //如果是【前++】，那么变量【立刻马上+1】，然后拿着结果进行使用。	【先加后用】
        System.out.println(++num1);//12
        System.out.println(num1);//12

        //如果是【后++】，那么首先使用变量本来的数值，【然后再让变量+1】。	【先用后加】
        int num2 = 20;
        System.out.println(num2++);//20
        System.out.println(num2);//21

        int num3 = 30;
        int result1 = --num3;
        System.out.println(num3);//29
        System.out.println(result1);//29

        int num4 = 40;
        int result2 = num4--;
        System.out.println(num4);//39
        System.out.println(result2);//40

        System.out.println("====================");
        int x =10;
        int y =20;
        //             11 +  20
        int result3 = ++x + y--;
        System.out.println(x);//11
        System.out.println(y);//19
        System.out.println(result3);//31


    }
}