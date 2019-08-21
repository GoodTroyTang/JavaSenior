package com.atguigu.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现callable接口。 --JDK 5.0新增
 * 与使用Runnable相比， Callable功能更强大些
 *  相比run()方法，call()方法可以有返回值；
 *  call()方法可以抛出异常；
 *  支持泛型的返回值
 *  需要借助FutureTask类，比如获取返回结果
 */
// 第1步：创建一个实现Callable的实现类
class NumThread implements Callable{

    @Override
//    第2步.重写call()方法，将此线程执行的操作声明在call()方法中
    public Object call() throws Exception {
        int sum = 0;
        for(int i=1;i<= 100;i++){
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
//        此方法中有返回值。
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
//        第3步.创建Callable类接口实现类的对象
        NumThread numThread = new NumThread();
//        第4步：将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask= new FutureTask(numThread);
//        第5步：将FutureTask类的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()方法。
        new Thread(futureTask).start();

        try {
//            第6步：获取Callable中call方法的返回值。
//            get()方法的返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。(return sum)
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
