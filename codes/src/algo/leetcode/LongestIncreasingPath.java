package algo.leetcode;

public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length < 1)return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        int max = 0;
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[0].length; j ++){
                if(dp[i][j] != 0)continue;
                max = Math.max(max, dfs(matrix, i, j, dp));
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int[][] dp){
        if(dp[i][j] != 0)return dp[i][j];
        int max = 0;
        if(i > 0 && matrix[i][j] < matrix[i-1][j]){
            max = Math.max(max, dfs(matrix, i-1, j, dp));
        }
        if(i < matrix.length-1 && matrix[i][j] < matrix[i+1][j]){
            max = Math.max(max, dfs(matrix, i+1, j, dp));
        }
        if(j > 0 && matrix[i][j] < matrix[i][j-1]){
            max = Math.max(max, dfs(matrix, i, j-1, dp));
        }
        if(j < matrix[0].length-1 && matrix[i][j] < matrix[i][j+1]){
            max = Math.max(max, dfs(matrix, i, j+1, dp));
        }
        dp[i][j] = max + 1;
        return dp[i][j];
    }
}
