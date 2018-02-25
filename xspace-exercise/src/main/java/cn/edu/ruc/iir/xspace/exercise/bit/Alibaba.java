package cn.edu.ruc.iir.xspace.exercise.bit;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise.bit
 * @ClassName: Alibaba
 * @Description: 阿里的实习面试就被问到一道题：有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
 * @author: taoyouxian
 * @date: Create in 2018-02-24 15:23
 **/
public class Alibaba {
    static int num = 10;
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int randomResult = random.nextInt(num);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        BitSet bitSet = new BitSet(num);
        for (int i = 0; i < num; i++) {
            bitSet.set(list.get(i));
        }

        System.out.println("0~1亿不在上述随机数中有 " + bitSet.size());
        for (int i = 0; i < num; i++) {
            if (!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
