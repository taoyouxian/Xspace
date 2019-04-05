package cn.edu.ruc.iir.xspace.leetcode.xiaomi.oj;

import java.util.Scanner;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.xiaomi.oj
 * @ClassName: LovePwd
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-29 20:34
 **/
public class LovePwd {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        int[] prime = new int[500001];
        prime[1] = 2;
        int max = 1, MAXN = 500000;
        int a = 1, b = 1, x;
        for (int i = 3; i < Integer.MAX_VALUE; i++) {
            x = a + b;
            if (isPrime(x)) {
                prime[max++] = x;
            }
            if (max == MAXN) {
                break;
            }
            a = b;
            b = x;
        }
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            // please write your code here
            String[] str = line.split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            System.out.println(prime[n] / 3 % m);
            // System.out.println("answer");
        }
    }

    public static boolean isPrime(int a) {
        boolean flag = true;
        if (a < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static int climbStairs(int n) {
        int f = 1;
        int g = 0;
        while (n > 1) {
            n--;
            f += g;
            g = f - g;
        }
        return f;
    }

}
