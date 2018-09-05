package algo.other;

import java.util.PriorityQueue;
import java.util.Queue;

public class BurstBalloons{
    public int maxCoins(int[] nums) {
        int[][]dp = new int[nums.length+2][nums.length+2];
        for(int r = 1; r < nums.length+1; r ++){
            for(int l = r; l >= 1; l --){
                for(int k = l; k <= r; k ++){
                    int left = l-2>=0?nums[l-2]:1;
                    int right = r<nums.length?nums[r]:1;
                    dp[l][r] = Math.max(dp[l][r], dp[l][k-1] + left*nums[k-1]*right + dp[k+1][r]);
                }
            }
        }
        return dp[1][nums.length-1];
    }
}
