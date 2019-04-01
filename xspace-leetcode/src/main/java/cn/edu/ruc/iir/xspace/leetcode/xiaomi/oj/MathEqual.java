package cn.edu.ruc.iir.xspace.leetcode.xiaomi.oj;

import java.util.Scanner;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.xiaomi.oj
 * @ClassName: MathEqual
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-29 19:07
 **/
public class MathEqual {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            // please write your code here
            String[] str = line.split(" ");
            short[] hash = new short[25000200];
//            for (int i = 0; i < hash.length; i++) {
//                hash[i] = 0;
//            }
            short a = Short.parseShort(str[0]);
            int b = Short.parseShort(str[1]);
            int c = Short.parseShort(str[2]);
            int d = -Short.parseShort(str[3]);
            int e = -Short.parseShort(str[4]);
            int i, j, k, sum;
            for (i = -50; i <= 50; i++) {
                for (j = -50; j <= 50; j++) {
                    if (j != 0 && i != 0) {
                        sum = -(a * i * i * i + b * j * j * j);
                        if (sum < 0)
                            sum = sum + 25000000;
                        hash[sum]++;
                    }
                }
            }

            int ans = 0;
            for (i = -50; i <= 50; i++) {
                for (j = -50; j <= 50; j++) {
                    for (k = -50; k <= 50; k++) {
                        if (i != 0 && j != 0 && k != 0) {
                            sum = c * i * i * i + d * j * j * j + e * k * k * k;
                            if (sum < 0)
                                sum = sum + 25000000;
                            if (hash[sum] > 0)
                                ans = ans + hash[sum];
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
