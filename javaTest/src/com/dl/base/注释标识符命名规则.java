package com.dl.base;//这是一个java程序

//  单行注释

/*多行注释
多行注释*/

/**
 *
        文本注释
 */


/*关键字（由jdk提供的）：
	1.notepad++中有特殊颜色修饰的
	2.全是小写字母
	3.由jdk提供的，不需要我们自己去定义，可以直接使用的
	
标识符（由我们程序员自己定义的）： 类名、变量名、方法名
	硬性规定：
		1.可以由字母 、数字 、下划线_ 、美元符号$组成
		2.不能以数字开头
		3.不能与关键字同名
	
	软性规定：
		1.类的命名：大驼峰式，首字母大写，后面的每个单词的首字母也大写         eg:HelloWorld
		2.变量的命名：小驼峰式，首字母小写，后面的每个单词的首字母大写		eg:myHabbitGood
		3.方法的命名：同变量的命名，小驼峰式，后面跟一对小括号				eg:canSwim()
	
	
*/
class 注释标识符命名规则 {//  单行注释

    public static void main(String[] args) {
        int myHabbitGood = 120;
        System.out.println(myHabbitGood);
    }


}

