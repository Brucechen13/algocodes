package algo.leetcode;

import java.util.*;

public class FallingSquares {

    class SegNode {
        boolean isUpdate;
        int height;
        SegNode left, right;

        public SegNode(int height) {
            isUpdate = true;
            this.height = height;
        }
    }

    public int getMaxHeight(SegNode node, int L, int R, int l, int r){
        if (L >= l && R <= r && node.isUpdate) {
            return node.height;
        }else if(L > r || R < l){
            return 0;
        }
        int mid = L + (R - L) / 2;

        if (node.isUpdate || node.left == null)
            node.left = new SegNode(node.height);
        if (node.isUpdate || node.right == null)
            node.right = new SegNode(node.height);
        int leftHei = getMaxHeight(node.left, L, mid, l, Math.min(mid, r));
        int rightHei = getMaxHeight(node.right, mid + 1, R, Math.max(mid, l), r);
        return Math.max(leftHei, rightHei);
    }

    public void updateNode(SegNode node, int L, int R, int l, int r, int height) {
        if (L >= l && R <= r) {
            node.height = height;
            node.isUpdate = true;
            return;
        }else if(L > r || R < l){
            return;
        }
        node.isUpdate = false;
        int mid = L + (R - L) / 2;
        if (node.left == null)
            node.left = new SegNode(node.height);
        if (node.right == null)
            node.right = new SegNode(node.height);

        updateNode(node.left, L, mid, l, Math.min(mid, r), height);
        updateNode(node.right, mid + 1, R, Math.max(mid, l), r, height);
    }

    private int curMaxHeight;

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        SegNode node = new SegNode(0);
        for (int[] posi : positions) {
            int hei = getMaxHeight(node, 0, (int) (1e9), posi[0], posi[0] + posi[1] - 1) + posi[1];
            updateNode(node, 0, (int) (1e9), posi[0], posi[0] + posi[1] - 1, hei);
            curMaxHeight = Math.max(curMaxHeight, hei);
            res.add(curMaxHeight);
        }
        return res;
    }

    public static void main(String[] args){
        int[][] posi =  {{1, 2}, {2, 3}, {6, 1}};//{{9,7},{1,9},{3,1}};//
        for(int val : new FallingSquares().fallingSquares(posi)){
            System.out.print(val + " ");
        }
    }
}
