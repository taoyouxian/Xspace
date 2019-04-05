package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_103
 * @Description:
 * @author: tao
 * @date: Create in 2019-04-01 21:32
 **/
public class BFS_103 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (null == root) return list;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> li = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode t = queue.poll();
                    li.add(t.val);
                    if (t.left != null)
                        queue.add(t.left);
                    if (t.right != null)
                        queue.add(t.right);
                }
                if ((count & 1) == 1) {
                    Collections.reverse(li);
                }
                count++;
                list.add(li);
            }
            return list;
        }
    }
}
