package algo.depthfirst;

public class SumRoot {
    public int sumNumbers(TreeNode root) {
        return sumLeft(root, 0);
    }

    public int sumLeft(TreeNode root, int cur){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return cur*10+root.val;
        }
        int left = sumLeft(root.left, cur*10+root.val);
        int right = sumLeft(root.right, cur*10+root.val);

        return left+right;
    }

}
