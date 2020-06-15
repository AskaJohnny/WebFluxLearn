package com.johnny.webflux.webfluxlearn.stream_learn;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 中间操作
 * 1.map
 * 2.flatMap
 * 3.peek
 * 4.limit
 *
 * @author johnny
 * @create 2020-02-21 下午7:23
 **/
public class StreamMidOperator {


    public static void main(String[] args) {



        Student student = new Student(1, "johnny", 23);
        Student student1 = new Student(2, "candy", 25);
        Student student2 = new Student(2, "ruby", 25);
        Student student3 = new Student(3, "lucy", 33);
        Student student4 = new Student(4, "jack", 53);


        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        //filter
        List<Student> filterStudent = studentList.stream().filter(s -> s.getAge() > 25)
                .collect(Collectors.toList());


        //distinct

        List<Integer> ageList = studentList.stream().map(s -> s.getAge())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(ageList);

        //limit
        List<Integer> limitAgeList = studentList.stream().map(s -> s.getAge())
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(limitAgeList);

        //skip
        List<Integer> skipAgeList = studentList.stream().map(s -> s.getAge())
                .skip(2)
                .collect(Collectors.toList());

        //map
        List<Integer> mapAageList = studentList.stream().map(s -> s.getAge())
                .collect(Collectors.toList());

        //flatMap

        System.out.println("--------flatMap---------");
        List<Integer> flatMapList = studentList.stream()
                .limit(2)
                .flatMap(s -> s.getName().chars().boxed())
                .collect(Collectors.toList());
        flatMapList.stream().forEach(i -> System.out.println((char) i.intValue()));


        Stream.of("Hello", "Stream")
                .forEach(System.out::println);

        //anyMatch 查找流中是否有满足谓语的，存在就返回true 否则就返回false
        boolean isExistAgeUp50 = studentList.stream()
                .anyMatch(s -> s.getAge() > 50);

        System.out.println(isExistAgeUp50);

        //allMatch
        boolean allMatchFlat = studentList.stream()
                .allMatch(s -> s.getAge() > 10);
        System.out.println(allMatchFlat);


        boolean noneMatchFlag = studentList.stream()
                .noneMatch(s -> s.getAge() > 100);
        System.out.println(noneMatchFlag);

        Optional<Student> studentOptional = studentList.parallelStream()
                .findAny();

        if (studentOptional.isPresent()) {
            System.out.println(studentOptional.get());
        }


        studentList.stream()
                .map(s -> s.getAge() * 2)
                .filter(i -> i % 2 == 0)
                .findFirst().ifPresent(i -> System.out.println(i));


        List<Integer> nums = Arrays.asList(3, 4, 5, 6, 7);
        int sum = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        studentList.stream()
                .sorted(Comparator.comparing(Student::getAge))
                .forEach(System.out::println);


        nums.stream()
                .sorted(Comparator.reverseOrder());


        studentList.stream()
                .map(Student::getAge)
                .collect(Collectors.toSet());

        // Stream.of(); of 内部不能直接给list 可以是数组
        //map 中间操作，用于取得内部的属性
//        Stream.of(new Student[]{student, student1, student2, student3}).filter(s -> s.getAge() > 25).map(s -> s.getAge())
//                .forEach(System.out::println);
//
//
//        //flatMap 中间操作， A->B的属性（是一个集合 。比如这里 s.getName.chars() 得到一个集合 IntStream ） 所有A的B的属性的集合
//        //主要IntStream/LongStream 并不是Stream的子类，所以需要boxed 进行装箱操作
//        System.out.println("flatMap ---------");
//        studentList.stream().flatMap(s -> s.getName().chars().boxed())
//                .forEach(i -> System.out.println((char) i.intValue()));
//
//
//        //peek 是一个debugg ， 它是中间操作，必须要有终止操作 才能执行，和forEach 一样
//        System.out.println("peek---------");
//        studentList.stream().peek(s -> System.out.println(s.getAge()))
//                .map(s -> s.getId()).forEach(System.out::println);
//
//
//        //limit
//        System.out.println("limit--------");
//        new Random().ints().filter(i -> i > 100 && i < 10000).limit(5)
//                .forEach(System.out::println);


    }

    static class Student {


        int id;

        String name;

        int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}