package atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式。
 * 存在线程安全问题，待解决。
 */

class window extends Thread{

//    static：共享同一个静态变量，不然会出现100赋值给三个线程。
    private  static int ticket = 100;

    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ": 卖票。票号为:" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {
        window t1 = new window();
        window t2 = new window();
        window t3 = new window();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
