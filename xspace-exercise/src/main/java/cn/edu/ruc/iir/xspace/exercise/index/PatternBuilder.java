package cn.edu.ruc.iir.xspace.exercise.index;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise.index
 * @ClassName: PatternBuilder
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-02-25 12:42
 **/
public class PatternBuilder {

    private static String DUP_MARK = "_rainbow_dup_";

    public static void main(String[] args) {
        String column = "Column_10_1,Column_100_2,Column_100_1";
        String[] replicas = column.split(",");
        for (String s : replicas) {
            if (s.contains(DUP_MARK)) {
                System.out.println(s);
                String temp = s.split(DUP_MARK)[0];
                System.out.println(temp);
            }
        }

        Map<String, String> m = new HashMap<>();
        m.put("a", "123");
        m.put("b", "1");
        m.put("a", "1234");
        System.out.println(m.keySet());
        System.out.println(m.values());

        Set<String> set = new HashSet<String>() {{
            add("list");
            add("zhao");
            add("long");
            add("ri");
        }};
        System.out.println(set.toString());

        List<String> list =  Arrays.asList("list", "zhao","long","ri");
        System.out.println(list.toString());
    }
}
