package cn.edu.ruc.iir.xspace.pixels;

import java.util.Arrays;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.pixels
 * @ClassName: TestSort
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-02-28 9:58
 **/
public class TestSort {
    public static void main(String[] args) {
        int[] array = new int[]{6, 7, 9, 4, 5};
        quickSort(array, 0, array.length - 1);
    }

    static int findPosition(int array[], int left, int right) {
        int baseKey = array[left];
        while (left < right) {
            while (left < right && array[right] >= baseKey) right--;
            if (left < right) array[left++] = array[right];
            while (left < right && array[left] <= baseKey) left++;
            if (left < right) array[right--] = array[left];
        }
        array[left] = baseKey;
        return left;
    }

    static void quickSort(int array[], int left, int right) {
        if (left >= right) return ;
        int pos = findPosition(array, left, right);
        System.out.println(Arrays.toString(array));
        quickSort(array, left, pos - 1);
        quickSort(array, pos + 1, right);
    }
}
