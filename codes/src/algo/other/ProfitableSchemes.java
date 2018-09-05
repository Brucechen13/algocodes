package algo.other;

public class ProfitableSchemes {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int len = profit.length;
        int[][][] dp = new int[len][G+1][P+1];

        int sum = dfs(group, profit, 0, G, P, dp);
        return sum;
    }

    public int dfs(int[] group, int[] profit, int index, int leftG, int leftP, int[][][] dp){
        if(dp[index][leftG][leftP]!= 0)return dp[index][leftG][leftP];
        int len = group.length;
        int sum = 0;
        if(leftP <= 0){
            sum = (len - index)*(len - index + 1)/2;
        }else{
            sum += dfs(group, profit, index+1, leftG, leftP, dp);
            sum += dfs(group, profit, index+1, leftG-group[index],
                    leftP-group[index], dp);
        }
        dp[index][leftG][leftP] = sum;
        return sum;
    }

    private int mod = (int)1e9 + 7;
    public int profitableSchemes2(int G, int P, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][G + 1][P + 1];
        dp[0][0][0] = 1;
        for (int k = 1; k <= group.length; k++) {
            int g = group[k - 1];
            int p = profit[k - 1];
            for (int i = 0; i <= G; i++) {
                for (int j = 0; j <= P; j++) {
                    dp[k][i][j] = dp[k - 1][i][j];
                    if (i >= g) {
                        dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - g][Math.max(0, j - p)])%mod;
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 0; i <= G; i++){
            sum = (sum + dp[group.length][i][P])%mod;
        }
        return sum;
    }

    public int profitableSchemes3(int G, int P, int[] group, int[] profit) {
        int[][] dp = new int[G + 1][P + 1];
        dp[0][0] = 1;
        for (int k = 1; k <= group.length; k++) {
            int g = group[k - 1];
            int p = profit[k - 1];
            for (int i = G; i >= g; i--) {
                for (int j = P; j >= 0; j--) {
                    dp[i][j] = (dp[i][j] + dp[i - g][Math.max(0, j - p)])%mod;
                }
            }
        }
        int sum = 0;
        for(int i = 0; i <= G; i++){
            sum = (sum + dp[i][P])%mod;
        }
        return sum;
    }
}
