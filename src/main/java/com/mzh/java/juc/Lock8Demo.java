package com.mzh.java.juc;


import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized  void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("**********sendEmail");
    }

    public synchronized void sendSms(){
        System.out.println("**********sendSms");
    }

    public void sayHello(){
        System.out.println("**********sayHello");
    }
}

public class Lock8Demo {



    //8.锁
    //标准访问，先打邮件还是短信
    //暂停邮件方法4秒，先打邮件还是短信
    //新增普通的sayHello方法，先打邮件还是sayHello
    //两部手机，先打邮件还是短信
    //两个静态的同步方法，同一部手机，先打邮件还是短信
    //两个静态的同步方法，两部手机，先打邮件还是短信
    //一个静态同步方法，一个同步方法，同一部手机，先打邮件还是短信
    //一个静态同步方法，一个同步方法，两部手机，先打邮件还是短信
    public static void main(String[] args) {

        //标准访问，先打邮件还是短信
        lock(); //邮件 短信
        //暂停邮件方法4秒，先打邮件还是短信
        // 邮件 短信

        //新增普通的sayHello方法，先打邮件还是sayHello
        //sayHello 邮件

        //两部手机，先打邮件还是短信
        // 短信 邮件

        //两个静态的同步方法，同一部手机，先打邮件还是短信
        // 邮件 短信

        //两个静态的同步方法，两部手机，先打邮件还是短信
        // 邮件 短信

        //一个静态同步方法，一个同步方法，同一部手机，先打邮件还是短信
        //短信 邮件

        //一个静态同步方法，一个同步方法，两部手机，先打邮件还是短信
        // 短信 邮件

    }

    private static void lock() {
        Phone phone1 = new Phone();

        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }

        },"1").start();

        try{
            Thread.sleep(400);
        } catch (Exception e){
            e.printStackTrace();
        }


        new Thread(() -> {
            try {
                //phone1.sendSms();
                //phone.sayHello();
                phone2.sendSms();
            }catch (Exception e){
                e.printStackTrace();
            }

        },"2").start();
    }
}
