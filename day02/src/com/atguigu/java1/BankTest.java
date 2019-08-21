package com.atguigu.java1;

/**
 * 使用同步机制将单例模型中的懒汉式改写为线程安全的
 *
 */

public class BankTest {
}

class Bank{
    private Bank(){}

    private static Bank instance = null;
    public static Bank getInstance(){

//        方式一：线程安全，但效率不高。
        synchronized (Bank.class){
            if (instance == null){
                instance = new Bank();
            }
            return instance;
        }

    }
}
