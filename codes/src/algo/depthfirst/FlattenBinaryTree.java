package algo.depthfirst;

public class FlattenBinaryTree {
    public void flatten(TreeNode root) {
        getNext(root);
    }

    public TreeNode getNext(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        root.right = null;
        TreeNode leftnext = getNext(left);
        TreeNode rightNext = getNext(right);
        if(leftnext == null && rightNext == null){
            return root;
        }
        System.out.println(root.val);
        if(left != null){
            root.right = left;
            if(leftnext != null) {
                leftnext.right = right;
            }
        }else{
            root.right = right;
        }
        if(rightNext == null){
            rightNext = leftnext;
        }
        return rightNext;
    }

    private TreeNode last = null;
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        if (last != null) {
            last.left = null;
            last.right = root;
        }
        last = root;

        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
