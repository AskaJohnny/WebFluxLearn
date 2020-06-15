package com.johnny.webflux.webfluxlearn.stream_learn;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author johnny
 * @create 2020-02-21 下午6:49
 **/
public class StreamDemo1 {

    public static void main(String[] args) {

        Student student = new Student(1,10);
        Student student2 = new Student(2,30);
        Student student3 = new Student(2,3);
        //student.setIsUsed("1");

        List<Student> list = new ArrayList();
        list.add(student);
        list.add(student2);
        list.add(student3);

        Student minStudent  = list.stream()
                .min(Comparator.comparing(Student::getAge))
                .get();


        boolean flag = list.stream()
                .anyMatch(s -> s.getIsUsed() != null && s.getIsUsed().equals("1"));

        System.out.println(flag);

        System.out.println("{MinStudent : {} " +  minStudent.age);
    }

    static class Student{
        public Student(int id, int age) {
            this.id = id;
            this.age = age;
        }

        int id;

        Integer age;

        String isUsed;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(String isUsed) {
            this.isUsed = isUsed;
        }
    }

//    public static void main(String[] args) {
//
//        List<Integer> list = new ArrayList<>();
//
//        //1.从集合创建流
//        list.stream();
//        list.parallelStream();
//
//        //2.从数组创建流
//
//        Arrays.stream(new int[] {1, 2, 3,});
//
//        //3.创建数字流
//
//        int intarr[] = {1,2,3};
//        IntStream.of(1,2,3);
//        IntStream.of(intarr);
//
//        long longarr[] = {1L , 2L , 3L};
//
//        LongStream.of(longarr);
//
//        //range  不包括最后一个值 / rangeClosed包括最后一个值
//        IntStream.range(0,10);
//       // IntStream.rangeClosed(0,100).forEach((i) -> System.out.println(i));
//
//        //4.Random创建一个无限流 limit是限制个数
//
//        new Random().longs();
//        new Random().doubles();
//        //new Random().ints().limit(10).forEach((i) -> System.out.println(i));
//
//
//        //5.自己创建Stream
//        Random random = new Random();
//        Stream.generate(() -> random.nextInt()).limit(20).forEach((i) -> System.out.println(i));
//
//
//
//        String str= "kwhtw_mg";
//        System.out.println(str.toUpperCase());
//
//        Gson gson = new Gson();
//
//        Map map = new HashMap<>();
//        map.put("101247086","5003897779");
//        String gsonStr = gson.toJson(map);
//
//        Map<String,String> map1 = gson.fromJson(gsonStr , Map.class);
//        System.out.println(map1);
//
//        System.out.println(map1.get("101247086"));
//
//
//        Map map2 = new HashMap();
//        map2.put("2542137", "5000117733");
//        System.out.println(map2);
//        System.out.println(map2.get(2542137));
//    }
}