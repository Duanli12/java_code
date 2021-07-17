package com.dl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //输入第一个字符串A，输入第二个字符串B，统计B在A中出现的次数
        //定义字符串
        String str="abc";
        String ss="abcabcabc";

        //定义返回的次数
        int count=0;
        for (int i=0;i<ss.length();i++) {
            //比较字符串A中截取
            for (int j=i;j<=ss.length();j++) {
                if (ss.substring(i, j).equals(str))
                    count++;
            }
        }
        System.out.println("字符串"+str+"出现了"+count+"次");

       //输入第一个字符串A，输入第二个字符串B，统计B字符串中每个字符在A中出现的次数
      /* String s1 = "acsdaccssa123";
       String s2 = "as1g";*/
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串A：");
        String s1 = sc.next();
        System.out.println("请输入字符串B：");
        String s2 = sc.next();

       for (int i = 0;i<s2.length();i++){
           int a = 0;
           for (int j =0;j<s1.length();j++){
               if (s2.charAt(i)==s1.charAt(j)){
                   a +=1;
               }
           }
           System.out.println(s2.charAt(i)+":"+a);
       }
    }
}
