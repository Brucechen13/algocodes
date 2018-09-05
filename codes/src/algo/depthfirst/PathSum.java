package algo.depthfirst;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null && sum - root.val == 0) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        addPathSum(root, sum, cur, res);
        return res;
    }

    public void addPathSum(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res){
        if(root == null) return;

        cur.add(root.val);
        if(root.left == null && root.right == null && sum - root.val == 0){
            res.add(cur);
            return;
        }
        List<Integer> left = new ArrayList<>(cur);
        addPathSum(root.left, sum - root.val, left, res);

        List<Integer> right = new ArrayList<>(cur);
        addPathSum(root.right, sum - root.val, right, res);
    }
}
