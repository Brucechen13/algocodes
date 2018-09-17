package algo.other;

public class StrangePrinter {
    public int strangePrinter(String s) {
        char[] arrs = s.toCharArray();
        int len = arrs.length;
        int[][][] dp = new int[len][len][len];
        int count = subPrinter(arrs, 0, len-1, 0, dp);
        return count;
    }

    public int subPrinter(char[] arrs, int start, int end, int k, int[][][] dp){
        if(start > end)return k;
        if(dp[start][end][k] != 0)return dp[start][end][k];
        int next = start+1;
        while (next <= end){
            if(arrs[next]!=arrs[start])break;
            next++;k++;
        }
        int min = 1 + subPrinter(arrs, next, end, 0, dp);
        for(int i = next+1; i <= end; i ++){
            if(arrs[i] == arrs[start]){
                min = Math.min(min, subPrinter(arrs, next, i-1, 0, dp) +
                        subPrinter(arrs, i, end, k+1, dp));
            }
        }
        dp[start][end][k] = min;
        return min;
    }


    public int strangePrinter2(String s) {
        int len = s.length();

        int[][] dp = new int[len][len];

        return dfs(s.toCharArray(), 0, len - 1, dp);
    }
    public int dfs(char[] cArr, int i, int j, int[][] dp){
        if(i > j)   return 0;

        if(dp[i][j] != 0)    return dp[i][j];


        dp[i][j] = dfs(cArr, i, j - 1, dp) + 1;

        for(int m = i;m < j;m++){
            if(cArr[m] == cArr[j]){
                dp[i][j] = Math.min(dp[i][j], dfs(cArr, m + 1, j - 1, dp) + dfs(cArr, i, m, dp));
            }
        }

        return dp[i][j];
    }

    public static void main(String[] args){
        System.out.println(new StrangePrinter().strangePrinter("abba"));
    }
}
