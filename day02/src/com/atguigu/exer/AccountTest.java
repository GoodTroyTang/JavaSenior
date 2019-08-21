package com.atguigu.exer;

/**
 * 银行有一个账户
 * 有两个储户分别向同一个账户存3000元，每次存1000元，存3次。每次存完打印账户余额。
 * 1：多线程问题：两个储户线程
 * 2：有共享数据：账户(或余额)
 * 3：线程安全问题：因为有共享数据。
 * 4：考虑线程安全问题：三种方式。
 */
class Account{
    private double balance;
    public Account(double balance){
        this.balance = balance;
    }
//    存钱
    public void deposit(double amt){
        if(amt > 0){
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + balance);
        }
    }
}

class Customer extends Thread{
    private Account acct;

    public Customer(Account acct){
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
//        共用同一个账户。
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);
        c1.setName("甲");
        c2.setName("乙");

    }
}
