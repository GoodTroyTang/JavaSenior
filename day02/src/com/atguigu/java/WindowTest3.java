package com.atguigu.java;

/**
 * 使用同步方法解决实现Runnable方法解决线程安全问题。
 * 关于同步方法的总结：
 *      1：同步方法任然涉及到同步监视器，只是不需要我们显示声明。
 *      2：非静态的同步方法，同步监视器：this。
 *          静态的同步方法，同步监视器是：当前类本身。
 */

class Window3 implements Runnable{

    private int ticket = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while(true){
            show();
        }
    }

//    单独写成一个方法。将方法声明成synchronized，再在run()方法中调用。
    private synchronized void show() {  // 同步方法中的监视器为：this。
//        synchronized (this) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":票号为：" + ticket);
                ticket--;
            }
//        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

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
