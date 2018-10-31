package algo.leetcode;

import java.util.*;

public class CountRangeSum {
    class SegmentTree{
        public long min;
        public long max;
        public int count;
        public SegmentTree left;
        public SegmentTree right;

        public SegmentTree(long min, long max){
            this.min = min;
            this.max = max;
        }
    }

    public SegmentTree buildSegmentTree(Long[] values, int l, int r){
        SegmentTree root = new SegmentTree(values[l], values[r]);
        if(l < r) {
            int mid = (l + r) / 2;
            root.left = buildSegmentTree(values, l, mid);
            root.right = buildSegmentTree(values, mid+1, r);
        }
        return root;
    }

    public void updateSegmentTree(SegmentTree root, long val){
        if(root == null)return;
        if(root.min <= val && root.max >= val){
            root.count ++;
            updateSegmentTree(root.left, val);
            updateSegmentTree(root.right, val);
        }
    }

    public int countSegmentTree(SegmentTree root, long left, long right){
        if(root == null || root.max < left || root.min > right)return 0;
        if(root.min >= left && root.max <= right ){
            return root.count;
        }
        return countSegmentTree(root.left, left, right) + countSegmentTree(root.right, left, right);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length < 1)return 0;
        Set<Long> sums = new HashSet<>();
        long sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            sums.add(sum);
        }

        Long[] ss  = sums.toArray(new Long[1]);
        Arrays.sort(ss);
        SegmentTree root = buildSegmentTree(ss, 0, ss.length-1);

        int ans = 0;
        for(int i = nums.length-1; i >= 0; i --){
            updateSegmentTree(root, sum);
            sum -= nums[i];
            ans += countSegmentTree(root, sum+lower, sum+upper);
        }
        return ans;
    }
}
