package atguigu.java;

/**
 * 测试Thread中的常用方法:
 * 1:start():启动当前线程;调用当前线程的run()。
 * 2:run():通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中。
 * 3:currentThread():返回当前代码执行的线程。
 * 4:getName():获取当前线程的名字；
 * 5:setName():设置当前线程的名字；
 *
 * 6.yield():释放当前CPU的执行，但是有可能再下一步还是当前CPU。
 * 7：join：在线程A中调用线程B的join()方法,此时线程A进入阻塞的状态，直到线程B完全执行完以后，线程A才结束阻塞状态。
 *      低优先级的线程也可以获得执行。
 * 8：stop()方法：已过时，强制线程生命期结束，一般不使用该方法。
 * 9：static void sleep(long millis)： (指定时间:毫秒)：令当前活动线程在指定时间段内放弃对CPU控制,使其他线程有机会被执行,时间到后
 *                                  重排队。抛出InterruptedException异常。
 *10：boolean isAlive()： 返回boolean，判断线程是否还活着。
 *
 * 线程的优先级
 * 1.线程的优先级等级：最小1，最大10.
 * 都是Thread里面的。
 * MAX_PRIORITY： 10
 * MIN _PRIORITY： 1
 * NORM_PRIORITY： 5 --> 默认优先级。
 *
 * 2.如何获取和设置当前线程的优先级
 *  getPriority() ： 返回(获取)线程优先值
 *  setPriority(int newPriority) ： 改变(设置)线程的优先级
 *  说明：
 *      高优先级的线程要抢占低优先级线程CPU的执行权，只是从概率上讲，高优先级会以很大的概率较低优先级之前出现，并不意味着高优先级执行结束后
 *              才执行低优先级。
 */

class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
//                try {
////                    当前线程放弃对CPU的控制。
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }
//            if(i % 20 == 0){
////                此处this表示当前类的对象，也就是Thread.currentThread()。可省略
////                yield():释放当前CPU的执行权，被另外一个线程拿到。
//                this.yield();
//            }
        }
    }
    public HelloThread(String name){
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {

//        采用构造器的方法给线程命名。
        HelloThread h1 = new HelloThread("Thread:1");
//        采用setName()的方法给线程命名：要在start之前给线程命名。
//        h1.setName("线程一");
//        设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();

//      给主线程命名
        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }
//到20时，将分线程执行完后，才执行主线程。20分钟前的状态可能主、分线程都有参与。
//            if(i == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
