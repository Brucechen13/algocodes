package algo.hiho;

import java.util.Arrays;
import java.util.Scanner;

public class HIho6Bag {


    public static int dfs(int[] vals, int[] costs, int index, int left, int[][] dp){
        if(index == vals.length)return 0;
        if(dp[index][left] != -1)return dp[index][left];
        int max = dfs(vals, costs, index+1, left, dp);

        if(left >= costs[index]) {
            max = Math.max(max, dfs(vals, costs, index,
                    left-costs[index], dp) + vals[index]);
        }
        dp[index][left] = max;
        return max;
    }

    public static int dfs2(int[] vals, int[] costs, int total){
        int[] dp = new int[total+1];
        int n = vals.length;
        for(int i = 0; i < n; i ++){
//            for(int j = total; j >= costs[i]; j --){
//                dp[j] = Math.max(dp[j], dp[j-costs[i]] + vals[i]);
//            }
            for(int j = costs[i]; j <= total; j ++){
                dp[i] = Math.max(dp[i], dp[j-costs[i] + vals[i]]);
            }
        }
        return dp[total];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int total = scanner.nextInt();
        int[] vals = new int[n];
        int[] costs = new int[n];
        int[][] dp = new int[n][total+1];
        for(int i = 0; i < n; i ++){
            Arrays.fill(dp[i], -1);
            costs[i] = scanner.nextInt();
            vals[i] = scanner.nextInt();
        }
        //int max = dfs2(vals, costs, total);
        int max = dfs(vals, costs, 0, total, dp);
        System.out.println(max);
    }
}
