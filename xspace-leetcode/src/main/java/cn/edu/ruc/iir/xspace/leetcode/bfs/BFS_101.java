package cn.edu.ruc.iir.xspace.leetcode.bfs;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_101
 * @Description:
 * @author: tao
 * @date: Create in 2019-04-01 21:15
 **/
public class BFS_101 {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root != null) return false;
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if ((left == null && right != null) || (left != null && right == null) || (left.val != right.val))
                return false;
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
    }

// 迭代(两个队列queue)
//    class Solution {
//        public:
//        bool isSymmetric(TreeNode* root) {
//            if (!root) return true;
//            queue<TreeNode*> q1, q2;
//            q1.push(root->left);
//            q2.push(root->right);
//            while (!q1.empty() && !q2.empty()) {
//                TreeNode *node1 = q1.front(); q1.pop();
//                TreeNode *node2 = q2.front(); q2.pop();
//                if (!node1 && !node2) continue;
//                if((node1 && !node2) || (!node1 && node2)) return false;
//                if (node1->val != node2->val) return false;
//                q1.push(node1->left);
//                q1.push(node1->right);
//                q2.push(node2->right);
//                q2.push(node2->left);
//            }
//            return true;
//        }
//    };
}
