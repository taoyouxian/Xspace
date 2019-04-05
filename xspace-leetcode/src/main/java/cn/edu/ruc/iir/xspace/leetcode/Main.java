package cn.edu.ruc.iir.xspace.leetcode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        if (n == null)
            return;
        StringBuilder sb = new StringBuilder(n);
        sb.reverse();
        n = sb.toString();
        int len = n.length();
        int k = 0, j, i;
//        System.out.println(n);
        char[] str = n.toCharArray();
        while (k < len) {
            i = j = k;
            while (str[j] != ' ') {
                j++;
                if (j == len || str[j] < 'A' || str[j] > 'z' || (str[j] > 'Z' && str[j] < 'a'))
                    break;
            }
            k = j + 1;
            for (j = j - 1; i < j; j--, i++) {
                if (str[j] < 'A' || str[j] > 'z' || (str[j] > 'Z' && str[j] < 'a'))
                    i++;
                swap(i, j, str);
            }
//            System.out.println(new String(str));
        }
        System.out.println(new String(str));
    }


    private static void swap(int i, int j, char[] str) {
        char t = str[j];
        str[j] = str[i];
        str[i] = t;

    }
}
