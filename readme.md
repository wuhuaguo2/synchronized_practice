《java高并发之魂---Synchronized》
https://www.imooc.com/learn/1086
定义：JVM会自动通过使用monitor来加锁和解锁，保证同时只有1个线程可以执行指定代码，从而保证线程安全。具有可重入和不可中断的性质。
作用：能保证在[同一时刻]最多只有[1个线程]执行该段代码，以达到保证并发安全的效果。
地位：1是java关键字，支持原生；2是最基本的互斥同步手段；3是并发编程中的元老级角色和必学内容
不用并发手段的后果预警：2-4
后果的代码演示和原因分析：ShowUnsafe1

i++虽然是1行代码，实际上包含3个动作：
A.读取i的值
B.计算i+1
C.把i+1的计算结果写回内存，赋值给i


【对象锁】
包括方法锁(默认锁对象为this当前实例对象)和同步代码(自己指定锁对象)
代码块形式：手动指定锁对象
方法锁形式：synchronized修饰普通方法，锁对象默认为this


【类锁】
指synchronized修饰静态的方法或指定锁为Class对象
只有1个Class对象：java类可能有很多个对象，但只有1个Class对象
本质：类锁是对于“Class对象”的锁
用法和效果：类锁同一时刻最多被一个对象拥有
形式1：synchronized加在static方法上
形式2：synchronized(*.class)代码块


多线程访问同步方法的7种情况(面试常考)
1、2个线程同时访问1个对象的同步方法
    答：SynObjMethod41，同时运行

2、2个线程同时访问2个对象的同步方法
    答：SynObjMethod42，同时运行。2个对象不一样，互不影响，能正常同步

3、2个线程同时访问2个对象的静态同步方法
    答：SynObjMethod43，排队运行。静态方法是类锁只有1个：必须一个线程执行完了，再执行下一个线程

4、2个线程同时访问1个对象的同步方法与非同步方法
    答：SynObjMethod44，同时运行。加锁的同步方法和不加锁的非同步方法，同步方法最多只被1个线程调用，但非同步方法无限制

5、2个线程访问1个对象的不同的普通同步方法
    答：SynObjMethod45，排队运行。两个同步方法是synchronized，都是调用this同一个对象，所以是同一个锁，排队使用。

6、2个线程同时访问静态同步方法和非静态同步方法
    答：SynObjMethod46，同时运行。静态同步方法加的锁是类锁，非静态同步方法加的锁是对象锁，两把不同锁，所以可以同时运行。

7、2个线程访问1个对象的不同的普通同步方法，方法抛出异常后，会释放锁
    答：SynObjMethod47，一个线程【先抛出异常后释放锁】，下一个线程继续运行。

synchronized的性质
1、可重入：
同一个线程的外层函数获得锁之后，内层函数可以直接再次获得锁。好处：避免死锁，提升封装性
详见：SynchronizedRecursion1，SynchronizedRecursion2

2、不可中断：
一旦这个锁已经被别人获得了，如果我还想获得，我只能选择等待或阻塞，直到别的线程释放这个锁。 如果别人永远不释放锁，那我只能永远等待下去。


synchronized的原理
1、加锁或释放锁的原理：看字节码
加锁或释放锁的时机：进入和退出同步代码块(包括抛出异常)
等价代码：SynToLock
看字节码：monitor相关指令，
    Decompilation -> 右键copy path=/Users/didi/web/synchronized-practice/src/com/imooc/syn/Decompilation.java
    -> 在终端执行，cd /Users/didi/web/synchronized-practice/src/com/imooc/syn
    -> javac Decompilation.java
    -> javap -verbose Decompilation.class
    monitorenter获取锁，monitorexit释放锁，1个monitorenter却2个monitorexit的原因：正常释放锁和异常释放锁

2、可重入原理：加锁次数计数器
jvm会记录被加锁的次数；第1次加锁时，次数从0变1，之后再次加锁就依次+1；退出一层同步代码块，计数-1，当计数=0时，代表锁释放

3、保证可见性原理：内存模型
一个线程执行的结果，另外的线程不一定可见；线程1操作x=5,之后线程2可能读取x=3；synchronized可以保证可见性


synchronized的缺陷
效率低：1.锁的释放情况少(正常释放或抛异常释放) 2.试图获得锁时不能设定超时 3.不能中断一个正在试图获取锁的线程
不够灵活(读写锁更灵活)：1.加锁和释放的时机单一 2.每个锁仅有单一的条件(某个对象)，可能是不够的
无法知道成功获取锁

synchronized常见面试题
1、使用注意点：锁的范围不宜过大，避免锁的嵌套
2、如何选择Lock和Synchronized关键字?=>能使用Synchronized的时候使用它，不能用时再用Lock
3、多线程访问同步方法的各种具体情况
4、Synchronized使得同时只有一个线程可以执行，性能较差，有什么办法可以提升性能？=>读多写少，可以用读写锁。
5、如何更灵活地控制锁的获取和释放(现在释放锁的时机都被规定死了) =>Lock