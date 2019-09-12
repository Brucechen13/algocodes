package algo.tree;

public class HouseRobberIII {
    public int rob(TreeNode root) {
        return dfs(root)[1];
    }
    private int[] dfs(TreeNode x) {
        if (x == null) return new int[2];
        int[] left = dfs(x.left);
        int[] right = dfs(x.right);
        int[] res = new int[2];
        res[0] = left[1] + right[1];
        res[1] = Math.max(res[0],left[0] + x.val + right[0]);
        return res;
    }
}
