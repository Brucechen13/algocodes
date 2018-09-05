package algo.search;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] maxLens = new int[nums.length];//定义从末尾到当前位置的最长递增子串
        int res = 0;
        for(int i = nums.length-1; i >= 0; i --){
            int maxLen = 0;
            for(int j = i+1; j < nums.length; j ++){
                if(nums[i] < nums[j] && maxLen < maxLens[j]){
                    maxLen = maxLens[j];
                }
            }
            maxLens[i] = maxLen+1;
            res = Math.max(res, maxLens[i]);
        }

        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        int[] LIS = new int[len];
        for (int i = 0; i < len; i++) {
            LIS[i] = Integer.MAX_VALUE;
        }

        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int index = getIndex(LIS, nums[i]);
            LIS[index] = nums[i];
            maxLen = Math.max(maxLen, index + 1);
        }

        return maxLen;
    }

    private int getIndex(int[] nums, int n) { // find index of the first number that larger than n
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int index = left + (right - left) / 2;
            int mid = nums[index];

            if (mid > n) {
                right = index;
            } else if (mid < n) {
                left = index;
            } else {
                left = index;
            }
        }

        return nums[left] >= n ? left : right;
    }
}
