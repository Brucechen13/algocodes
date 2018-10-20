package algo.leetcode;

public class MaximumSum3 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[][] dp = new int[nums.length][3];
        int[] posi = new int[3];
        int max = findMaxSubarrays(nums, k, 0, 2, dp, posi);
        return posi;
    }

    public int findMaxSubarrays(int[] nums, int k, int start, int l, int[][] dp, int[] posi){
        if(l < 0)return 0;
        if(dp[start][l] != 0)return dp[start][l];
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int[] temp = new int[3];
        for(int i = start; i < n-k*(l+1); i +=3){
            int cur = 0;
            for(int j = start; j < start + k; j ++){
                cur += nums[j];
            }
            cur += findMaxSubarrays(nums, k, i+k, l-1, dp, posi);
            if(cur > max){
                max = cur;
                posi[l] = i;
                for(int z = l; z < 3; z ++){
                    temp[z] = posi[z];
                }
            }
        }
        for(int z = l; z < 3; z ++){
            posi[z] = temp[z];
        }
        dp[start][l] = max;
        return max;
    }

    public static void main(String[] args){
        int[] vals = {1,2,1,2,6,7,5,1};
        new MaximumSum3().maxSumOfThreeSubarrays(vals, 2);
    }
}
