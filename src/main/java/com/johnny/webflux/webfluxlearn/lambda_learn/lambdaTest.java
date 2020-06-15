package com.johnny.webflux.webfluxlearn.lambda_learn;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author johnny
 * @create 2020-01-23 上午9:22
 **/
@FunctionalInterface
interface MyFunction {
    void hello();
}

public class lambdaTest {

    public static void main(String[] args) {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("lambda 初识");
//            }
//        }).start();
//
//
//        Runnable runnable = () -> System.out.println("lambda 初识");
//        MyFunction myFunction = () -> System.out.println("lambda 初识");
//        new Thread(() -> System.out.println("lambda 初识")).start();
//
//
//        int[] nums = {33, 44, 55, -111, -1};
//
//        int minNum = Integer.MAX_VALUE;
//
//        for (int num : nums) {
//            if (num < minNum) {
//                minNum = num;
//            }
//        }
//
//        System.out.println(minNum);
//
//        int min = IntStream.of(nums).parallel().min().getAsInt();
//        System.out.println(min);
//
//
//        Function<Integer, Integer> function = (i) -> i * 2;
//        function.apply(10);


        Predicate<Integer> predicate = (i) -> i % 2 == 0;
        System.out.println(predicate.test(2));

    }
}