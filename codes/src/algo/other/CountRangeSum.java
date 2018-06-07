package algo.other;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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




    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        long[] temp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[] count = new int[1];
        mergeSort(sum, 0, sum.length - 1, lower, upper, temp, count);
        return count[0];
    }

    public void mergeSort(long[] sum, int start, int end, int lower, int upper, long[] temp, int[] count) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(sum, start, mid, lower, upper, temp, count);
        mergeSort(sum, mid + 1, end, lower, upper, temp, count);
        merge(sum, start, mid, end, lower, upper, temp, count);
    }

    public void merge(long[] sum, int start, int mid, int end, int lower, int upper, long[] temp, int[] count) {
        int right = mid + 1;
        int low = mid + 1;
        int high = mid + 1;
        int index = start;

        for (int left = start; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) {
                ++low;
            }

            while (high <= end && sum[high] - sum[left] <= upper) {
                ++high;
            }

            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
            count[0] += high - low;
        }

        while (right <= end) {
            temp[index++] = sum[right++];
        }

        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
    }
}
