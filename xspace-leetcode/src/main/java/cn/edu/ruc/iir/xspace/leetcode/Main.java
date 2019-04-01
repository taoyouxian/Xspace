package cn.edu.ruc.iir.xspace.leetcode;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode
 * @ClassName: Main
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-20 09:04
 **/
public class Main {
    public static void main(String[] args) {
        String str = "123";
        System.out.println(str.substring(0, 0));
        TreeSet<Integer> s = new TreeSet<>();
        s.add(1);
        s.add(11);
        s.add(13);
        s.add(23);
        s.add(14);
        System.out.println(s.toString());
        SortedSet ss = s.headSet(13);
        for (Object i : ss) {
            System.out.print(i + ",");
        }
        System.out.println();

        Queue<Integer> q = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }

        });
        q.add(1);
        q.add(-1);
        q.add(12);
        q.add(-11);
        q.add(20);
        q.add(0);
        System.out.println(q.toString());

        Person p = new Person();
        System.out.println(p.toString());
        change(p);
        System.out.println(p);

        p = new Person(5);
        System.out.println(p);
        changeVal(p);
        System.out.println(p);
        System.out.println(p.getA());
    }

    private static void changeVal(Person p) {
        System.out.println("O:" + p);
        System.out.println(p.getA());
        p.setA(55);
        System.out.println("1:" + p);
        p = new Person(55);
        System.out.println("2:" + p);
    }

    private static void change(Person p) {
        p = new Person();
        System.out.println(p);
    }

    static class Person {
        public Person() {
        }

        public Person(int a) {
            this.a = a;
        }

        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "a=" + a + this.hashCode() +
                    '}';
        }
    }
}
