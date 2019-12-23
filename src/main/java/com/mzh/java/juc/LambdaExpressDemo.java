package com.mzh.java.juc;


public class LambdaExpressDemo {

    @FunctionalInterface
    interface Foo{
        public int add(int x,int y);

        public default int mul(int x,int y){
            return x*y;
        }

        public static int div(int x,int y){
            return x/y;
        }

    }


    public static void main(String[] args) {
        Foo foo = (x,y) -> {return x+y;} ;

        System.out.println(foo.add(3, 3));

        System.out.println(foo.mul(3, 3));

        System.out.println(Foo.div(3,3));
    }
}
