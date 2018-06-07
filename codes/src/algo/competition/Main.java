package algo.competition;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int m = in.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i < n; i ++){
                nums[i] = in.nextInt();
            }
            long[][][] dp = new long[n][n][m+1];
            long minMax = dfs(nums, dp, 0, nums.length-1, m, 0);
            System.out.println(minMax);
        }
    }

    public static long dfs(int[] nums, long[][][] dp, int i, int j, int k, long preMax){
        if(k <= 0){
            return preMax;
        }
        if(dp[i][j][k] != 0)return dp[i][j][k];
        long curMax = 0;
        long minMax = Long.MAX_VALUE;
        for(int z = i; z <= j - k + 1; z ++){
            curMax += (long)nums[z];
            if(k != 1 || z == j) {
                long num_max = dfs(nums, dp, z + 1, j, k - 1, curMax);
                long tmpMax = Math.max(curMax, num_max);
                minMax = Math.min(minMax, tmpMax);
            }
        }
        dp[i][j][k] = minMax;
        return minMax;
    }
}