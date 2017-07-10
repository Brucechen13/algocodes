package algo.array;


/**
 * Created by chenc on 2017/7/9.
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class ConstructBinaryTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder==null || postorder.length < 1){
            return null;
        }
        TreeNode root = findRoot(inorder, postorder, 0, postorder.length, 0, postorder.length);
        return root;
    }
    public TreeNode findRoot(int[] inorder, int[] postorder, int ileft, int iend, int pleft, int pend){
        if (ileft>=iend || pleft>=pend) return null;
        int index = -1;
        for(int i = ileft; i < iend; i ++){
            if(inorder[i] == postorder[pend-1]){
                index = i;
            }
        }
        TreeNode node = new TreeNode(postorder[pend-1]);
        node.left = findRoot(inorder, postorder, ileft, index, pleft, pleft+index-ileft);
        node.right = findRoot(inorder, postorder, index+1, iend, pleft+index-ileft, pend-1);
        return node;
    }

}
