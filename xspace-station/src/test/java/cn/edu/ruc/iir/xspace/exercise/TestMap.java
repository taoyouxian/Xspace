package cn.edu.ruc.iir.xspace.exercise;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static cn.edu.ruc.iir.xspace.exercise.A.a;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise
 * @ClassName: TestMap
 * @Description:
 * @author: tao
 * @date: Create in 2019-02-24 18:45
 **/
public class TestMap {

    @Test
    public void testLinkedHashMap() {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> treeMap = new TreeMap<>();
        Map<String, String> hashtable = new Hashtable<>();
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.isEmpty();
        queue.peek();

    }
}

interface A {

    int a = 0;
}

interface B extends A{

    int a = 1;
}

class C implements A, B {

    void print() {
        System.out.println(A.a);
    }
}
