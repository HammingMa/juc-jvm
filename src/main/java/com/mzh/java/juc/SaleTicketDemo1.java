package com.mzh.java.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 3000;

    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {
            if(number>0) {
                System.out.println(Thread.currentThread().getName() + "售票员卖出第" + (number--) + "张票，还剩" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo1 {
    //三个售票员卖三十张票

    public static void main(String[] args) {
        final Ticket ticket = new Ticket();

        new Thread(() -> { for(int i = 0; i < 3001; i++) ticket.sale();},"A").start();
        new Thread(() -> { for(int i = 0; i < 3001; i++) ticket.sale();},"B").start();
        new Thread(() -> { for(int i = 0; i < 3001; i++) ticket.sale();},"C").start();
        /*
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3001; i++) {
                    ticket.sale();
                }

            }
        }, "A").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3001; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3001; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
         */
    }
}
