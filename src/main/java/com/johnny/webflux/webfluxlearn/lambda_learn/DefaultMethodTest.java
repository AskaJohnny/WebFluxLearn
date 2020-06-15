package com.johnny.webflux.webfluxlearn.lambda_learn;

/**
 * JDK8 特性
 *
 * JDK 8 默认方法
 * @FunctionalInterface
 *
 *
 *
 * @author johnny
 * @create 2020-02-19 上午11:25
 **/

@FunctionalInterface
interface Interface1 {
    int doubleNumber(int i);

    default void someMethod(){
        System.out.println("Interface1 default Method ");
    }
}

@FunctionalInterface
interface  Interface2{
    int doubleNumber(int i);

    default void someMethod(){
        System.out.println("Interface2 someMethod ");
    }
}

@FunctionalInterface
interface  Interface3 extends Interface1 , Interface2{

    @Override
    default void someMethod() {
        Interface2.super.someMethod();
    }
}

public class DefaultMethodTest {
    public static void main(String[] args) {

        Interface1 interface1 = (i) -> i * 2;

        System.out.println(interface1.doubleNumber(50));
        interface1.someMethod();
    }



}