package com.imooc.syn;

public class SynchronizedRecursion1 {
    int a = 0;

    public synchronized void method1() {
        System.out.println("这是method1，a="+a);
        if (a == 0) {
            a++;
            method1();
        }
    }

    public static void main(String[] args) {
        SynchronizedRecursion1 s = new SynchronizedRecursion1();
        s.method1();
    }
}
/*
这是method1，a=0
这是method1，a=1
*/