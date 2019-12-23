package com.mzh.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AriconditionLock{
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void incriment(){
        lock.lock();
        try{


            while (number!=0){
                condition.await();
            }
            number++;

            System.out.println(Thread.currentThread().getName() +" "+number);

            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decriment(){
        lock.lock();
        try{

            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() +" "+number);

            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}


public class ProduConsumerLockDemo {


    public static void main(String[] args) {

        AriconditionLock ariconditionLock = new AriconditionLock();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ariconditionLock.incriment();
            }


        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ariconditionLock.decriment();
            }
        },"B").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ariconditionLock.incriment();
            }


        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ariconditionLock.decriment();
            }
        },"D").start();


    }
}
