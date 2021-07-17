package com.dl.base;

/*
强制类型转换
	1. 特点：代码需要进行特殊的格式处理，不能自动完成。
	2. 规则：数据范围从大到小
	2. 格式：范围小的类型 范围小的变量名 = (范围小的类型) 原本范围大的数据;

注意事项：
	1. 强制类型转换一般不推荐使用，因为有可能发生精度损失、数据溢出。
	2.char类型可以进行算术运算，比如+,-,*,/
	3. byte/short/char这三种类型在运算的时候，都会被首先提升成为int类型，然后再计算。
	4. boolean类型不能发生数据类型转换
*/
public class Demo02DataType {
    public static void main(String[] args) {
        //范围小的类型 范围小的变量名 = (范围小的类型) 原本范围大的数据;
        //long -> int
        //大   -> 小

        int num = (int)10L;
        System.out.println(num);

        //int最大取值：21亿多

        int num2 =  (int)3000000000L;//数据溢出
        System.out.println(num2);

        int num3 = (int)3.99;
        System.out.println(num3);//精度损失

        byte num4 = 40;
        byte num5 = 50;

        //byte/short/char这三种类型在运算的时候，都会被首先提升成为int类型，然后再计算。
        //byte + byte
        //int + int
        //int
        int result = num4+num5;
        System.out.println(result);

        short num6 = 60;

        //byte + short
        //int + int
        //int
        short result2 = (short) (num4 + num6);
        System.out.println(result2);





    }
}