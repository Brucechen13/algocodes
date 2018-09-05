package algo.array;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for(int i = 1; i < m; i ++){
            res[i][0] = 1;
            for(int j = 1; j < n; j ++){
                res[0][j] = 1;
                res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return Math.max(1, res[m-1][n-1]);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i<m;i++){
            if(obstacleGrid[i][0]==1){
                break;
            }
            res[i][0] = 1;
        }
        for(int j= 0;j<n;j++){
            if(obstacleGrid[0][j]==1){
                break;
            }
            res[0][j]=1;
        }
        for(int i = 1; i < m; i ++){
            res[i][0] = 1;
            for(int j = 1; j < n; j ++){
                res[0][j] = 1;
                if(obstacleGrid[i][j] == 1){
                    res[i][j] = 0;
                    continue;
                }
                res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return Math.max(obstacleGrid[0][0]!=0?1:0, res[m-1][n-1]);
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
