package com.ldg.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicUpaterTest {
    public static void main(String[] args) {
      Person p=new Person();
//      for(int i=0;i<10;i++){
//          Thread thread=new Thread(()->{
//              try {
//                  Thread.sleep(20);
//              } catch (InterruptedException e) {
//                  e.printStackTrace();
//              }
//              System.out.println(p.age++);
//          });
//          thread.start();
//      }
//
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater=AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        for(int i=0;i<10;i++){
            Thread thread=new Thread(()->{
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(p));
            });
            thread.start();
        }
    }
}
class Person{
   volatile int age=1;
}
