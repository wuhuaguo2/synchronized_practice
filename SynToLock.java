package com.imooc.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynToLock {
    Lock lock = new ReentrantLock();

    private synchronized void method1() {
        System.out.println("我是Synchronized形式");
    }

    private void method2() {
        lock.lock();
        try {
            System.out.println("我是Lock形式");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynToLock s = new SynToLock();
        s.method1();
        s.method2();
    }
}
/*
我是Synchronized形式
我是Lock形式
*/