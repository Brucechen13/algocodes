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
        TreeNode root = findRoot(inorder, postorder, postorder.length-1, 0, postorder.length, 0, postorder.length);
        return root;
    }
    public TreeNode findRoot(int[] inorder, int[] postorder, int rootVal, int ileft, int iend, int pleft, int pend){
        if (ileft>iend || pleft>pend) return null;
        int index = -1;
        for(int i = ileft; i < iend; i ++){
            if(inorder[i] == postorder[rootVal]){
                index = i;
            }
        }
        System.out.println(ileft + " " + iend + " " + pleft + " " + pend + " " + rootVal + " " + index);
        TreeNode node = new TreeNode(postorder[rootVal]);
        node.left = findRoot(inorder, postorder, pleft+index-ileft-1, ileft, index, pleft, pleft+index-ileft);
        node.right = findRoot(inorder, postorder, pend-1, index+1, iend, pleft+index-ileft+1, pend);
        return node;
    }

}
