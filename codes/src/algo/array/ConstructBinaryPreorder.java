package algo.array;

import java.util.Stack;

/**
 * Created by chenc on 2017/7/11.
 */
public class ConstructBinaryPreorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(inorder.length == 0) return null;
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int pi = 0;
        int ii = 0;
        TreeNode root = new TreeNode(preorder[pi]);
        stack.add(root);
        pi++;
        while (pi < preorder.length){
            while (!stack.isEmpty() && stack.peek().val == inorder[ii]){
                prev = stack.pop();
                ii++;
            }
            TreeNode newNode = new TreeNode(preorder[pi]);
            if(prev != null){
                prev.right = newNode;
            }else{
                TreeNode currTop = stack.peek();
                currTop.left = newNode;
            }
            stack.push(newNode);
            prev = null;
            pi++;
        }
        return root;
    }
}
