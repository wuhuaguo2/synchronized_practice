package com.imooc.syn;

public class SynObjMethod41 implements Runnable{
    private static SynObjMethod41 instant = new SynObjMethod41();

    public synchronized void method() {//synchronized
        System.out.println("我是对象锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

    public void run() {
        method();
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
我是对象锁的方法修饰形式。我叫Thread-0
Thread-0运行结束
我是对象锁的方法修饰形式。我叫Thread-1
Thread-1运行结束
finished
*/