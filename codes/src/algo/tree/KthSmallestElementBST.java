package algo.tree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementBST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> queue = new ArrayList<>();

        return preOrder(root, k, queue);

    }

    public int preOrder(TreeNode node, int k, List<Integer> queue){
        if(node.left != null) {
            int res = preOrder(node.left, k, queue);
            if(res != -1){
                return res;
            }
        }
        if(queue.size() == k-1){
            return node.val;
        }
        queue.add(node.val);
        if(node.right != null) {
            int res = preOrder(node.right, k, queue);
            if(res != -1){
                return res;
            }
        }
        return -1;
    }
}
