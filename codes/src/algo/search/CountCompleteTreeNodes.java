package algo.search;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class CountCompleteTreeNodes {
    public int height(TreeNode root){
        int len = -1;
        while (root != null){
            len++;root = root.left;
        }
        return len;
    }
    public int countNodes(TreeNode root){
        int h = height(root);
        return h<0?0:h-1==height(root.right)?(1<<h)+countNodes(root.right)
                :(1<<h-1)+countNodes(root.left);
    }

    public int countNodes2(TreeNode root) {
        if(root == null)return 0;
        int hr = 0, hl = 0;
        TreeNode cur = root;
        while (cur != null){
            hl ++;
            cur = cur.left;
        }
        cur = root;
        while (cur != null){
            hr ++;
            cur = cur.right;
        }
        if(hr == hl)return (int)Math.pow(2, hl) - 1;
        else return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
