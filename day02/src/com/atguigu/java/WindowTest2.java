package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式。
 * 存在线程安全问题，待解决。
 *
 * 使用同步代码块解决继承Theard类的方式的线程安全问题。
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器。
 */

class window2 extends Thread{

    //    static：共享同一个静态变量，不然会出现100赋值给三个线程。
    private  static int ticket = 100;

//    因为创建了三个对象，因此这里也需要加上static关键字。
//    监视器需要加上static。
    private  static Object obj = new Object();
    @Override
    public void run() {
        while (true){
//            synchronized (obj){
//            synchronized (this){  // 由于有三个对象，此时this指向多个对象，导致锁不唯一。无法解决重复不安全问题。
            synchronized (window2.class){  // 放入类也可以：其实类也是对象。Class clazz = windew2.class。并且只会加载一次。
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 卖票。票号为:" + ticket);
                    ticket--;
                }else {
                    break;
                }
            }

        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        window2 t1 = new window2();
        window2 t2 = new window2();
        window2 t3 = new window2();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
