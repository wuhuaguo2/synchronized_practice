package com.imooc.syn;
//描述：演示线程不安全
public class ShowUnsafe1 implements Runnable{
    static ShowUnsafe1 r = new ShowUnsafe1();
    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    //改造1
    /*public synchronized void run2() {
        //等价于 synchronized (this) {如下for语句}
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }*/
    //改造2
    /*public void run() {
        synchronized (ShowUnsafe1.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}