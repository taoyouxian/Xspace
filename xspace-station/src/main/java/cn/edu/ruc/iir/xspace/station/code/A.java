package cn.edu.ruc.iir.xspace.station.code;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.station.code
 * @ClassName: A
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-03-14 19:21
 **/
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, n1 = 0, n2 = 0;
        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];
        boolean[] a1 = new boolean[n];
        boolean[] b1 = new boolean[n];
        for (int j = 0; j < n; j++) {
            a[j] = sc.nextInt();
            a1[j] = true;
        }
        for (int j = 0; j < n; j++) {
            b[j] = sc.nextInt();
            b1[j] = true;
        }

        Arrays.sort(a);
        Arrays.sort(b);

        System.out.println(Arrays.toString(a));
        while (n > 0) {
            // 妞妞

            // 牛牛

            n--;
        }
        System.out.println(ans);
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int num = in.nextInt();
//        int sum = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        while (queue.isEmpty()) {
//
//        }
//    }
}
