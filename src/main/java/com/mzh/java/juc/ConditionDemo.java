package com.mzh.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }


    public void print1() {
        lock.lock();

        try {
            while (number != 1) {
                condition1.await();
            }

            print(5);

            number = 2;
            condition2.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();

        try {
            while (number != 2) {
                condition2.await();
            }

            print(10);

            number = 3;
            condition3.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();

        try {
            while (number != 3) {
                condition3.await();
            }

            print(15);

            number = 1;
            condition1.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print4() {
        lock.lock();
        String name = Thread.currentThread().getName();
        Condition await = null;
        Condition signal = null;
        int flagNumber = 0;
        switch (name) {
            case "A":
                await = condition1;
                signal = condition2;
                flagNumber=1;
                break;
            case "B":
                await = condition2;
                signal = condition3;
                flagNumber=2;
                break;
            case "C":
                await = condition3;
                signal = condition1;
                flagNumber=3;
                break;
        }


        try {

            while (number != flagNumber) {
                await.await();
            }

            print(flagNumber*5);

            number = flagNumber%3 +1;
            signal.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print4();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print4();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print4();
            }
        }, "C").start();
    }
}
