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
        int[] a = new int[]{10, 5, -3, 2, 6, 7, 11, 20, 5, 6, 5, -6};

//        mergeSortUp2Down(a, 0, a.length - 1);
//        mergeSortDown2Up(a);
//        insertSort(a, a.length);
//        quickSort(a, 0, a.length - 1);
        qSort(a, 0, a.length - 1);
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

    // 插入排序
    public static void insertSort(int[] a, int n) {
        int i, j, temp;
        for (i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];

                for (j = i; j >= 1 && temp < a[j - 1]; j--) {
                    a[j] = a[j - 1];
                }
                a[j] = temp;
            }
        }
    }

    // 快速排序
    public static void quickSort(int[] a, int left, int right) {
        if (left >= right)
            return;
        int pos = findPos(a, left, right);
        quickSort(a, left, pos - 1);
        quickSort(a, pos + 1, right);
    }

    private static int findPos(int[] a, int left, int right) {
        int key = a[left];
        while (left < right) {
            while (left < right && a[right] >= key) right--;
            if (left < right) a[left++] = a[right];
            while (left < right && a[left] <= key) left++;
            if (left < right) a[right--] = a[left];
        }
        a[left] = key;
        return left;
    }

    public static void qSort(int[] a, int low, int high) {
        if (low >= high) return;
        int first = low;
        int last = high;

        int left = low;
        int right = high;

        int leftLen = 0;
        int rightLen = 0;

        int key = selectMidOfThree(a, low, high);

        while (low < high) {
            while (low < high && a[high] >= key) {
                if (a[high] == key) {
                    swap(a, right, high);
                    right--;
                    rightLen++;
                }
                high--;
            }
            if (low < high)
                a[low++] = a[high];
            while (low < high && a[low] <= key) {
                if (a[low] == key) {
                    swap(a, left, low);
                    left++;
                    leftLen++;
                }
                low++;
            }
            if (low < high)
                a[high--] = a[low];
        }
        a[low] = key;

        int i = low - 1;
        int j = first;
        while (j < left && a[i] != key) {
            swap(a, i, j);
            i--;
            j++;
        }
        i = low + 1;
        j = last;
        while (j > right && a[i] != key) {
            swap(a, i, j);
            i++;
            j--;
        }
        qSort(a, first, low - 1 - leftLen);
        qSort(a, low + 1 + rightLen, last);
    }


    private static int selectMidOfThree(int[] a, int low, int high) {
        int mid = (high - low) / 2 + low;
        if (a[low] > a[mid])
            swap(a, low, mid);
        if (a[low] > a[high])
            swap(a, low, high);
        if (a[mid] > a[high])
            swap(a, mid, high);
        return a[mid];
    }

    private static void swap(int[] a, int i, int i1) {
        int t = a[i];
        a[i] = a[i1];
        a[i1] = t;
    }

}
