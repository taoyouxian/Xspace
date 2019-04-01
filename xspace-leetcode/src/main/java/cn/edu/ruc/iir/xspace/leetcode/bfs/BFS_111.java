package cn.edu.ruc.iir.xspace.leetcode.bfs;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_111
 * @Description:
 * @author: tao
 * @date: Create in 2019-04-01 21:32
 **/
public class BFS_111 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        // 深度优先搜索DFS
        public int minDepth(TreeNode root) {
            if (null == root) return 0;
            if (root.left == null)
                return 1 + minDepth(root.right);
            if (root.right == null)
                return 1 + minDepth(root.left);
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
        // 迭代，层序遍历，记录遍历的层数(一旦我们遍历到第一个叶结点，就将当前层数返回，即为二叉树的最小深度)
    }
}
