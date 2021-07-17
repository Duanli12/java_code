package com.dl.base;/*
	变量：程序运行期间，可以改变的量
		
	java中一共只有两种大的数据类型：
		1.基本数据类型(四类八种)
			1.整型,byte,short,int,long
			2.浮点型,float,double
			3.布尔型,boolean
			4.字符型,char
		2.引用数据类型
		
	变量的格式：
		1.分步定义:
			数据类型	变量名;      定义一个变量
			变量名 = 值;			 把值赋给变量（赋值）
		2.一步到位的格式:
			数据类型 变量名 = 值 ; 
			
	变量的作用域：
		从定义的位置开始，一直到包含它的最近的}截止
	
	注意：在同一个作用域内，不能存在两个名字相同的变量
	试一试：1.可以把一个变量赋给另一个变量吗？
			2.变量没有赋值，可以直接输出吗？
*/

public class A03VariableTest {


    public static void main(String[] args) {

        {
            int a= 10;
            System.out.println(a);
        }

        //    数据类型	变量名;      定义一个变量
        int a;
        //    变量名 = 值;			 把值赋给变量（赋值）
        a = 20;
        System.out.println(a);
        //    数据类型 变量名 = 值 ;
        double d = 10.2;

//        int a = 100;    //在同一个作用域内，不能存在两个名字相同的变量

        int b = a;
        System.out.println(b);

        boolean boo ;
//        System.out.println(boo);      变量没有赋值（初始化），不可以直接输出

    }

}