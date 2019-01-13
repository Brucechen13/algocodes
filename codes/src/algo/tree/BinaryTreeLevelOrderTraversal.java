package algo.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while (!queue.isEmpty()){
            LinkedList<TreeNode> tmp = new LinkedList<>();
            List<Integer> vals = new ArrayList<>();
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node.left != null)tmp.add(node.left);
                if(node.right != null)tmp.add(node.right);
                vals.add(node.val);
            }

            if(reverse) {
                Collections.reverse(vals);
            }
            reverse = !reverse;
            res.add(vals);
            queue = tmp;
        }
        return res;
    }
}
