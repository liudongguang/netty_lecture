package com.ldg.nibianxiebian;

public class BasicHolder<T> {
    T element;
    void set(T arg) { element = arg; }
    T get() { return element; }
    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

// CRGWithBasicHolder.java
class Subtype extends BasicHolder<Subtype> {}

