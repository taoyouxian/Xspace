package cn.edu.ruc.iir.xspace.station;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.station
 * @ClassName: Main
 * @Description: Coding for life
 * @author: tao
 * @date: Create in 2019-02-24 20:23
 **/
public class Main {

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
//        System.out.println(Arrays.toString(array));

        Collections.sort(null);
    }
    Map<String, String> map =new LinkedHashMap<>();
    ListNode[] a = new ListNode[10];
    int[] hash = new int[128];
    StringBuffer s = new StringBuffer();

    //Insert one char from stringstream
    public void Insert(char ch) {
        s.append(ch);
        if (hash[ch] == 0)
            hash[ch] = 1;
        else hash[ch] += 1;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        char[] str = s.toString().toCharArray();
        for (char c : str) {
            if (hash[c] == 1)
                return c;
        }
        return '#';
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length) {
            return list;
        }
        Arrays.sort(input);

        for (int i = 0; i < k; i++)
            list.add(input[i]);
        return list;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public ListNode deleteDuplication(ListNode pHead) {

        return null;
    }
}


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}


class Solution {
    Stack<Integer> data = new Stack<Integer>();

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int count = 0;
        for (int i = 0; i < pushA.length; i++) {
            data.push(pushA[i]);
            if (data.peek() == popA[count++]) {
                data.pop();
            }
        }
        for (int i = count; i < popA.length; i++) {
            if (popA[i] == data.peek())
                data.pop();
        }
        return data.empty();
    }

    Stack<Integer> min = new Stack<Integer>();

    Integer temp = null;

    public void push(int node) {
        data.push(node);
        if (temp == null) {
            temp = node;
            min.push(node);
        } else {
            if (temp >= node) {
                temp = node;
                min.push(node);
            }
        }
    }

    public void pop() {
        int num = data.pop();
        int min_num = min.peek();
        if (num == min_num)
            min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return min.peek();
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        Integer len = array.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        Integer key;
        for (int i : array) {
            key = map.get(i);
            if (null == key) {
                map.put(i, 1);
            } else {
                map.put(i, key + 1);
                if (key + 1 > len) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void Mirror(TreeNode root) {
        if ((root == null) || (root.left == null && root.right == null))
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null)
            Mirror(root.left);

        if (root.right != null)
            Mirror(root.right);
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int circle = ((row < col ? row : col) - 1) / 2 + 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < circle; i++) {

            for (int j = i; j < col - i; j++) {
                list.add(matrix[i][j]);
            }

            for (int k = i + 1; k < row - i; k++) {
                list.add(matrix[col - i - 1][k]);
            }

            for (int l = col - i - 2; l >= i; l--) {
                list.add(matrix[col - i - 1][l]);
            }

            for (int m = row - i - 2; m > i; m--) {
                list.add(matrix[m][i]);
            }
            System.out.println(list.toString());

        }
        return list;
    }
}
