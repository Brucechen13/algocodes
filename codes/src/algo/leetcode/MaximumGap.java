package algo.leetcode;

import java.util.Arrays;

public class MaximumGap {
    public int maximumGap(int[] nums) {
        if(nums.length < 2)return 0;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length-1; i ++){
            max = Math.max(nums[i+1] - nums[i], max);
        }
        return max;
    }


    public int maximumGap2(int[] nums) {
        int n = nums.length;
        if(n < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // Pigeonhole principle
        int capacity = (max - min) / (n - 1);
        if((max - min) % (n - 1) != 0) {
            capacity += 1;
        }
        if(capacity == 0) {
            return 0;
        }
        int nBucket = (max - min) / capacity + 1;
        int[][] buckets = new int[2][nBucket];
        Arrays.fill(buckets[0], -1);
        for(int num : nums) {
            int idx = (num - min) / capacity;
            if(buckets[0][idx] == -1) {
                buckets[0][idx] = num;
                buckets[1][idx] = num;
            }
            else {
                buckets[0][idx] = Math.min(buckets[0][idx], num);
                buckets[1][idx] = Math.max(buckets[1][idx], num);
            }
        }
        int ans = Integer.MIN_VALUE;
        int pre = min;
        for(int i = 0; i < nBucket; ++i) {
            if(buckets[0][i] == -1) {
                continue;
            }
            ans = Math.max(ans, buckets[0][i] - pre);
            pre = buckets[1][i];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] vals = {1,2,4,7};
        System.out.println(new MaximumGap().maximumGap2(vals));
    }
}
