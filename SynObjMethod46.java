package com.imooc.syn;

public class SynObjMethod46 implements Runnable {
    private static SynObjMethod46 instant = new SynObjMethod46();

    public static synchronized void method1() {
        //static synchronized是类锁，加在SynObjMethod46上
        System.out.println("我是静态同步方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

    public synchronized void method2() {
        //synchronized是类锁，加在instant上
        System.out.println("我是非静态同步方法。我叫"+Thread.currentThread().getName());
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
我是非静态同步方法。我叫Thread-1
我是静态同步方法。我叫Thread-0
Thread-0运行结束
Thread-1运行结束
finished
*/