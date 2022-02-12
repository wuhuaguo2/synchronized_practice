package com.imooc.syn;

public class SynObjMethod47 implements Runnable {
    private static SynObjMethod47 instant = new SynObjMethod47();

    public synchronized void method1() {
        System.out.println("我是加锁1的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public synchronized void method2() {
        System.out.println("我是加锁2的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instant);
        Thread t2 = new Thread(instant);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {}
        System.out.println("finished");
    }
}
/*
我是加锁1的方法。我叫Thread-0
我是加锁2的方法。我叫Thread-1
Exception in thread "Thread-0" java.lang.RuntimeException
    at com.imooc.syn.SynObjMethod47.method1(SynObjMethod47.java:13)
    at com.imooc.syn.SynObjMethod47.run(SynObjMethod47.java:28)
    at java.lang.Thread.run(Thread.java:750)
Thread-1运行结束
finished
*/