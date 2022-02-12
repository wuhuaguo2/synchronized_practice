package com.imooc.syn;

public class SynchronizedRecursion2 {

    public synchronized void method1() {
        System.out.println("这是method1");
        method2();
    }

    public synchronized void method2() {
        System.out.println("这是method2");
    }

    public static void main(String[] args) {
        SynchronizedRecursion2 s = new SynchronizedRecursion2();
        s.method1();
    }
}
/*
这是method1
这是method2
*/