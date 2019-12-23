package com.mzh.java.juc;

class Aricondition{
    private int number = 0;

    public synchronized void increment() throws Exception {

        while (number != 0){
            System.out.println("等待生产");
            this.wait();
        }

        number++;

        System.out.println(Thread.currentThread().getName() +" "+number);

        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        while (number == 0){
            System.out.println("等待消费");
            this.wait();
        }

        number--;

        System.out.println(Thread.currentThread().getName() +" "+number);

        this.notifyAll();
    }
}

public class ProduConsumerDemo {
    public static void main(String[] args) throws Exception {
        Aricondition aricondition = new Aricondition();

        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
                try {
                    aricondition.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
                try {
                    aricondition.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"B").start();


        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
                try {
                    aricondition.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
                try {
                    aricondition.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"D").start();
    }
}
