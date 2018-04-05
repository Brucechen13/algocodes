package algo.depthfirst;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSide {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        searchRightVal(root, res, 0);
        return res;
    }

    public void searchRightVal(TreeNode root, List<Integer> res, int level){
        if(root == null){
            return;
        }
        if(level >= res.size()){
            res.add(root.val);
        }
        searchRightVal(root.right, res, level+1);
        searchRightVal(root.left, res, level+1);
    }
}
