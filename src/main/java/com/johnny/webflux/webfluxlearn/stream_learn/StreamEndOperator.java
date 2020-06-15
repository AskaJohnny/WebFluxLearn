package com.johnny.webflux.webfluxlearn.stream_learn;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author johnny
 * @create 2020-02-21 下午8:00
 **/
public class StreamEndOperator {


    public static void main(String[] args) {
        Student student = new Student(1, "johnny", 23);
        Student student1 = new Student(2, "candy", 25);
        Student student2 = new Student(3, "lucy", 33);
        Student student3 = new Student(4, "jack", 53);


        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);


        //collect 收集， 结合Collectors去操作
        List<Integer> list = studentList.stream().map(Student::getAge).collect(Collectors.toList());
        System.out.println(list);


        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i -> i % 2 == 0)
                .distinct() //去重元素2
                .forEach(System.out::println);


        //


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
    }

}