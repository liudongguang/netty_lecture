package com.ldg.junit;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LiuDongguang on 2017/10/16.
 */
public class CalculatorTest {
    @Test
    public void add() throws Exception {
        Calculator ca=new Calculator();
        int rs=ca.add(3,6);
        assertEquals(9,rs);//expected 期望
    }

    @Test
    public void subtract() throws Exception {
    }

    @Test
    public void multiply() throws Exception {
    }

    @Test
    public void divide() throws Exception {
        Calculator ca=new Calculator();
        int rs=ca.divide(3,0);
    }

    /**
     * 出了异常测试下面的判断对不对，出了异常fail方法不会执行
     */
    @Test
    public void testDivideByZero()  {
        Calculator ca=new Calculator();
        Throwable tx=null;
        try {
            ca.divide(3, 0);
            fail();
        }catch (Exception e){
            tx=e;
        }
        assertEquals(Exception.class,tx.getClass());
        assertEquals("除数不能为0！",tx.getMessage());
    }
}