package com.ldg.junit;

/**
 * Created by LiuDongguang on 2017/10/16.
 */
public class Calculator {
    public int add(int a,int b){
        return a+b;
    }
    public int subtract(int a,int b){
        return a-b;
    }
    public int multiply(int a,int b){
        return a*b;
    }
    public int divide(int a,int b) throws Exception {
        if(b==0){
            throw new Exception("除数不能为0！");
        }
        return a/b;
    }
}
