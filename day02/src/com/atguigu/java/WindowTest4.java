package com.atguigu.java;

/**
 * 使用同步方法来处理继承Thread类的方式中的线程安全问题。
 */

class window4 extends Thread{
    //    static：共享同一个静态变量，不然会出现100赋值给三个线程。
    private  static int ticket = 100;

    @Override
    public void run() {
        while (true){
            show();
        }
    }
//    需要在synchronized前加上静态修饰static。
    private static synchronized void show(){ //同步监视器为当前类：windows4.class
//    private synchronized void show(){ //需要同步监视器t1/t2/t3。
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 卖票。票号为:" + ticket);
            ticket--;
        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        window4 t1 = new window4();
        window4 t2 = new window4();
        window4 t3 = new window4();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
