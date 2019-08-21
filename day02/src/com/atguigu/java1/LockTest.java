package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 --JDK5.0新增。
 * 1.面试题:synchronized 与Lock的异同。
 *      同：二者都可以解决线程安全问题
 *      异：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器。
 *          lock需要手动的启动同步(lock())，结束同步需要手动实现(unlock())
 *
 * 使用的优先顺序：
 *      Lock  同步代码块（已经进入了方法体，分配了相应资源）  同步方法（在方法体之外）
 *
 * 2.面试题：如何解决线程安全问题?有几种方式(三种)
 *
 */
class Window implements Runnable{

    private int ticket = 100;

    // 1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true); // fair为ReentrantLock的构造器。

    @Override
    public void run() {
        while (true){
            try{
//              调用锁定Lock方法。
                lock.lock();

                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "票号为：" + ticket);
                    ticket --;
                }else {
                    break;
                }
            }finally {
//                3.调用解锁的方法
                lock.unlock();
            }
        }

    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
