package com.mzh.java.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ArrayListNoSafeDemo {


    public static void main(String[] args) {
        //listNotSafe();
        //setNotSafe();


        mapNotSafe();

    }

    private static void mapNotSafe() {
        Map map = new ConcurrentHashMap(); //new HashMap();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0,9),Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+" "+map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set set = new CopyOnWriteArraySet(); //new HashSet();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(Thread.currentThread().getName()+" "+set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List list = new CopyOnWriteArrayList(); //Collections.synchronizedList(new ArrayList()); //new Vector();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(Thread.currentThread().getName()+" "+list);
            },String.valueOf(i)).start();
        }
    }
}
