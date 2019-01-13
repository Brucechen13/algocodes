package algo.matrics;

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length < 1)return 0;
        int h = matrix.length;
        int w = matrix[0].length;
        int[][] dp = new int[h+1][w+1];

        int res = 0;
        for(int i = 1; i <= h; i ++){
            for(int j = 1; j <= w; j ++){
                if(matrix[i-1][j-1] == '0')continue;
                dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                res = Math.max(res, dp[i][j]);
            }
        }
        return res*res;
    }


    // 使用一维DP
    public int maximalSquare2(char[][] matrix) {
        if(matrix == null || matrix.length < 1)return 0;
        int h = matrix.length;
        int w = matrix[0].length;
        int[] dp = new int[w+1];
        int res = 0;
        for(int i = 1; i <= h; i ++){
            int pre = 0;
            for(int j = 1; j <= w; j ++){
                if(matrix[i-1][j-1] == '0'){
                    dp[j] = 0;
                    continue;
                }
                int tmp = dp[j];
                dp[j] = 1 + Math.min(pre, Math.min(dp[j-1], dp[j]));
                pre = tmp;
                res = Math.max(res, dp[j]);
            }
        }
        return res*res;
    }

    public static void main(String[] args){
        MaximalSquare sq = new MaximalSquare();
        char[][] matrix = {{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},
        {'1','1','0','1','1'},{'0','1','1','1','1'}};
        System.out.println(sq.maximalSquare2(matrix));
    }
}
