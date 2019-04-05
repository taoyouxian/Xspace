package cn.edu.ruc.iir.xspace.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode
 * @ClassName: Main1
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-04-02 19:43
 **/
public class Main1 {
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> v = new ArrayList<>();
        dfs(arr, 0, v);
        System.out.println(ans);
    }

    private static void dfs(int[] arr, int index, List<Integer> v) {
        if (v.size() == 3) {
            if (ifOne(arr[0], arr[1], arr[2])) {
                ans++;
                return;
            }
        }
        if (index >= arr.length)
            return;
        v.add(arr[index]);
        dfs(arr, index + 1, v);
        v.remove(v.size() - 1);
        dfs(arr, index + 1, v);
    }

    private static boolean ifOne(int i, int i1, int i2) {
        int x = gcd(i, i1);
        return gcd(x, i2) == 1;
    }

    private static int gcd(int x, int y) {
        int z = y;
        while (x % y != 0) {
            z = x % y;
            x = y;
            y = z;
        }
        return z;
    }
}
