package algo.other;

import algo.depthfirst.TreeNode;

public class BinaryTreeMaximumPathSum {
    public int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null)return 0;
        int cur = maxSumPath(root);
        return Math.max(max, cur);
    }

    public int maxSumPath(TreeNode root){
        if(root == null)return 0;
        int left = maxSumPath(root.left);
        int right = maxSumPath(root.right);
        if(root.left != null) {
            max = Math.max(max, left);
            max = Math.max(max, left + root.val);
        }
        if(root.right != null) {
            max = Math.max(max, right);
            max = Math.max(max, right + root.val);
        }
        if(root.left != null && root.right != null) {
            max = Math.max(max, left + root.val + right);
        }
        return Math.max(root.val, Math.max(left + root.val, right + root.val));
    }
}
