package cn.edu.ruc.iir.xspace.pixels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.pixels
 * @ClassName: Main
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-02-25 22:26
 **/
public class Main {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            ListNode ret = new Solution().deleteDuplicates(head);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            if (map.get(key) == null) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        num1[0] = -1;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                if (num1[0] == -1)
                    num1[0] = e.getKey();
                else {
                    num1[0] = e.getKey();
                    break;
                }
            }
        }

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        int len;
        pre = head;
        while (true) {
            len = head.val;
            head = head.next;
            if (head == null) {
                break;
            }
            if (len == head.val) {
                pre.next = head.next;
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }

    public ListNode middleNode(ListNode head) {
        ListNode dummy = head, pre;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        int num = 0;
        if (count % 2 == 0) {
            while (true) {
                if (num < count / 2 + 1) {
                    dummy = dummy.next;
                    num++;
                } else {
                    pre = dummy;
                    break;
                }
            }
        } else {
            while (true) {
                if (num < count / 2) {
                    dummy = dummy.next;
                    num++;
                } else {
                    pre = dummy;
                    break;
                }
            }
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        int multi = 0;
        if (k > len) return head;
        else {
            multi = len / k;
        }

        System.out.println(multi);
        int num = 0;
        while (num < multi) {
            System.out.println(num);
            reverseBetween(head, k * num + 1, k * (num + 1));
            num++;
        }
        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;
        ListNode temp = null;
        ListNode cur = null;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;

        int count = 1;
        while (head != null) {
            // 到达反转处
            if (count < m) {
                pre = pre.next;
            } else if (count == m) {
                cur = pre.next;
            }
            // 开始反转，插入到第一个反转的前面
            else if (count >= m && count <= n) {
                // 获得当前节点
                temp = cur.next;
                cur.next = cur.next.next;
                temp.next = pre.next;

                pre.next = temp;

            } else if (count > n) {
                break;
            }
            count++;
        }
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (head != null) {
            head = head.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }
        return pre;
    }

}