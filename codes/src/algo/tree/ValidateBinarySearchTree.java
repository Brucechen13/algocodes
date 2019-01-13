package algo.tree;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {

        if(root==null)return true;
        return isValidBSTBound(root, Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
    }

    public boolean isValidBSTBound(TreeNode root, int min, int max, boolean isLeft, boolean isRight){
        if((isLeft && root.val <= min) || (isRight && root.val >= max))return false;

        boolean flag = true;
        if(root.left != null){
            if(root.val <= root.left.val)return false;
            flag &= isValidBSTBound(root.left, min, isRight?Math.min(max, root.val):root.val, isLeft, true);
        }

        if(!flag)return false;

        if(root.right != null){
            if(root.val >= root.right.val)return false;
            flag &= isValidBSTBound(root.right, isLeft?Math.max(min, root.val):root.val, max, true, isRight);
        }
        return flag;
    }
}
