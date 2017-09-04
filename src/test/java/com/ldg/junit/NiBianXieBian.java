package com.ldg.junit;

import com.ldg.nibianxiebian.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class NiBianXieBian {
    @Test
    public void t1() throws Exception {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();
        fruit[1] = new Jonathan();
        try {
            fruit[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            fruit[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //extends是限制数据来源的（生产者）
    @Test
    public void t2() throws Exception {
        List<? extends Fruit> flist = new ArrayList<Apple>();
        //flist.add(new Apple());  // 编译错误
        //flist.add(new Fruit());  // 编译错误
        //flist.add(new Object());  // 编译错误


    }

    //super是限制数据流入的（消费者）
    @Test
    public void t3() throws Exception {
        List<? super Apple> flist = new ArrayList<Fruit>();
        flist.add(new Apple());
        flist.add(new Apple_1());
        flist.add(new Apple_2());
        //flist.add(new Fruit());  // 编译错误
        //flist.add(new Object());  // 编译错误


    }

    @Test
    public void t4() throws Exception {
        List<Apple_1> apple_1s = new ArrayList<>();//pe  限制数据来源的生产者
        apple_1s.add(new Apple_1());
        apple_1s.add(new Apple_1());
        apple_1s.add(new Apple_1());
        List<Apple> apples=new ArrayList<>();  //cs   //限制数据入的消费者
        apples.add(new Apple());
        apples.add(new Apple());
        apples.add(new Apple());
        Collections.copy(apples,apple_1s);//这里是apple_1s生产的数据被apples消费了
        System.out.println(apples);
    }
    @Test
    public void t5() throws Exception {
        List<Object> list=new ArrayList<>();
        List<? super Apple> sa=list;
        sa.add(new Apple_1());
        List<Apple_2> ap2list=new ArrayList<>();
        ap2list.add(new Apple_2());
        List<? extends Apple> el=ap2list;
        Apple apple = el.get(0);
    }
}
