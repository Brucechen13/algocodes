package algo.other;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSmallerNumbers {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        TreeLenNode root = null;
        for(int i = nums.length-1; i >= 0; i --){
            //构建搜索树
            if(root == null){
                root = new TreeLenNode(nums[i]);
                res.add(0);
            }else{
                res.add(addTreeNode(root, nums[i]));
            }
        }
        Collections.reverse(res);
        return res;
    }

    public int addTreeNode(TreeLenNode root, int val){
        if(root.val < val){
            if(root.right == null){
                root.right = new TreeLenNode(val);
                return root.size + root.leftTreeSize;
            }else{
                return root.size + root.leftTreeSize + addTreeNode(root.right, val);
            }
        }else if(root.val > val){
            root.leftTreeSize += 1;
            if(root.left == null){
                root.left = new TreeLenNode(val);
                return 0;
            }
            return addTreeNode(root.left, val);
        }else{
            root.size += 1;
            return root.leftTreeSize;
        }
    }

    public class TreeLenNode{
        public int val;
        public int size;
        public int leftTreeSize;
        public TreeLenNode left;
        public TreeLenNode right;
        public TreeLenNode(int val){
            this.val = val;
            this.size = 1;
        }
    }
}
