package cn.edu.ruc.iir.xspace.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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
    }
}
