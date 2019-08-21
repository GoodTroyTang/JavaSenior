package atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现runnable接口的方式。
 * 仍然存在线程的安全问题。
 */
class Window1 implements Runnable{

    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ":票号为：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();
//一个对象放在三个构造器里面，相当于三个构造器共用100张票，而不是分别使用100张。
//        不像继承的方式还要加static，这里通过接口的方式可以自动实现共享数据。
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
