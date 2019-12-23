package com.mzh.java.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        TimeUnit.SECONDS.sleep(2);
        System.out.println("************* come in callable method()");
        return 1024;
    }
}

class GetThreadName implements Callable<String>{
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        new Thread(futureTask, "A").start();

        Integer result = futureTask.get();
        System.out.println(111);
        System.out.println(result);

        GetThreadName getThreadName = new GetThreadName();

        List<FutureTask<String>> futureTasks = new ArrayList<>();

        for (int i = 0; i <100; i++) {
            FutureTask<String> stringFutureTask = new FutureTask<String>(getThreadName);
            futureTasks.add(stringFutureTask);
            new Thread(stringFutureTask,Integer.toString(i)+" "+System.currentTimeMillis()).start();

        }

        for (FutureTask<String> task : futureTasks) {
            System.out.println(task.get());
        }


    }
}
