package atguigu.java;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1：创建一个继承于Thread类的子类；
 * 2：重写Thread类的run()方法；--->将此线程执行的操作声明在run()中.
 * 3:创建Thread类的子类的对象；
 * 4:通过此对象调用start()。
 *
 * 例子：遍历100以内的所有的素数。
 */

// 1：创建一个继承于Thread类的子类；
class MyThread extends  Thread{
// 2：重写Thread类的run()方法

    @Override
//    重写run()方法。
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
//                使用Thread.currentThread().getName()可查看运行在那个线程里面。
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
//        3:创建Thread类的子类的对象；
        MyThread t1 = new MyThread();
//        4:通过此对象调用start(),作用：(1)启动当前线程;(2)调用当前线程的run()。
        t1.start();
//        t1.run();
//      t1.start();之前都是主线程做的事情。调完start后，自动调用run()方法，开始独立执行。
//        如果直接调用t1.run()，就不是在运行多线程了。
//        (1):因此，我们不能通过直接调用run()的方式启动线程。

//        (2)再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行，会报IllegalThread错误.
//        我们需要重新创建一个线程的对象。
        MyThread t2 = new MyThread();
        t2.start();


//        如下的操作仍然是在主线程(main)下执行的。
//        System.out.println("hello");
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i + "***********main()*************");
            }
        }
    }
}
