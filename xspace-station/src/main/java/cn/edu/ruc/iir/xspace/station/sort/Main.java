package cn.edu.ruc.iir.xspace.station.sort;

import java.util.Arrays;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.station.Sort
 * @ClassName: Main
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-14 15:00
 **/
public class Main {

    public static void main(String[] args) {
        int[] a = new int[]{10, 5, 3, 2, 6, 7};

//        mergeSortUp2Down(a, 0, a.length - 1);
        mergeSortDown2Up(a);

        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] a, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (a[i] >= a[j])
                temp[k++] = a[j++];
            else
                temp[k++] = a[i++];
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= end) {
            temp[k++] = a[j++];
        }

        for (i = 0; i < temp.length; i++)
            a[start + i] = temp[i];

        temp = null;
    }

    // 从上往下
    public static void mergeSortUp2Down(int[] a, int start, int end) {
        if (a == null || start >= end)
            return;
        int mid = (end + start) >> 1;
        mergeSortUp2Down(a, start, mid);
        mergeSortUp2Down(a, mid + 1, end);

        merge(a, start, mid, end);
    }

    public static void mergeGroups(int[] a, int len, int gap) {
        int i;
        int twoLen = gap << 1;

        for (i = 0; i + twoLen - 1 < a.length; i += twoLen)
            merge(a, i, i + gap - 1, i + twoLen - 1);

        // 剩余一个子数组没有匹配
        if (i + gap - 1 < len - 1)
            merge(a, i, i + gap - 1, len - 1);
    }

    // 从下往上
    public static void mergeSortDown2Up(int[] a) {
        if (a == null)
            return;
        for (int i = 1; i < a.length; i *= 2)
            mergeGroups(a, a.length, i);
    }
}
