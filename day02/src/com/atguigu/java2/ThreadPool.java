package com.atguigu.java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式四：使用线程池(开发中一般都是使用线程池)
 * 好处：
 *  提高响应速度（减少了创建新线程的时间）
 *  降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 *  便于线程管理：
 *  corePoolSize：核心池的大小
 *  maximumPoolSize：最大线程数
 *  keepAliveTime：线程没有任务时最多保持多长时间后会
 */
class NumberThread implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<= 100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
//        第1步：提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
//        第2步：执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象。
//        service1.setCorePoolSize(10);
//        设置线程池的属性

        service.execute(new NumberThread());  // 适合使用于Runnable
        service.execute(new NumberThread1());
//        service.submit();   // 适合适用于Callable
// 第三步：关闭线程池
        service.shutdown();   // 关闭线程池

    }
}
