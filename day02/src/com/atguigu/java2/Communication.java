package com.atguigu.java2;
/**
线程通信的例子：使用两个线程打印 1-100。线程1, 线程2 交替打印。

 涉及的三个方法：
    wait():一旦执行此方法，当前线程进入阻塞状态，并释放同步监视器。
    notify():一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程，那首先唤醒优先级高的线程。
    notifyAll():一旦执行此方法，就会唤醒所有被wait的线程。

 说明：
    1.wait()，notify()，notifyAll()三个方法必须使用在同步代码块和同步方法中。
    2.这三个方法的调用者必须是同步代码块或同步方法中的同步监视器。否则会出现异常。
    3.这三个方法定义在java.lang.Object()下。
 面试题：sleep()方法和wait()方法的异同？
    1.同：一旦执行方法都可以使得当前的线程进入阻塞状态。
    2；异：(1)两个声明的位置不一样：Thread类中声明sleep()，Object类中声明wait()
            (2)调用的范围不一样：sleep()可以在任何场景下使用，wait()必须定义在同步代码块和同步方法中。
            (3)关于是否释放同步监视器：如果两个方法都是用在同步代码块和同步方法中，sleep()不会释放锁，wait()会释放锁。
 */
class Number implements Runnable{
    private int number = 1;
//    private Object obj = new Object();
    @Override
    public void run() {
        while (true){
//            使用同步代码块的方式解决线程安全问题。
            synchronized (this){      // synchronized (this){}表示同步代码块。
//                唤醒，和wait()搭配。唤醒wait()阻塞的程序。
//                第一步线程一阻塞后，线程而进来的时候遇到了notify()而唤醒了线程一，此时因为线程二掌握了锁，因此打印的是线程二
//                线程2打印后同样被阻塞(wait())，此时又开始打印线程一，如此反复...。
                this.notify(); //this可省略

//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

//                    wait()方法 ：使得线程进入阻塞状态
                    try {
                        this.wait();  // wait()还可以释放锁。this可省略
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    break;
                }
            }
        }
    }
}

public class Communication {
    public static void main(String[] args) {
        Number number = new Number();

//        创建两个线程。
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);
        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}
