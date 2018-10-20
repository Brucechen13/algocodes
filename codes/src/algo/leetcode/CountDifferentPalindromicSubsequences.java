package algo.leetcode;

import java.util.Arrays;

public class CountDifferentPalindromicSubsequences {
    public int countPalindromicSubsequences(String S) {
        int len = S.length();
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i ++){
            dp[i][i] = 1;
        }

        for(int dis = 1; dis < len; dis ++){
            for(int i = 0; i < len-dis; i ++){
                int j = i + dis;
                if(S.charAt(i) == S.charAt(j)){
                    int low = i+1;
                    int high = j-1;
                    while (low <= high && S.charAt(low) != S.charAt(i))low++;
                    while (low <= high && S.charAt(high) != S.charAt(j))high--;
                    if(low > high){
                        dp[i][j] = dp[i+1][j-1]*2 + 2;
                    }else if(low == high){
                        dp[i][j] = dp[i+1][j-1]*2 + 1;
                    }else{
                        dp[i][j] = dp[i+1][j-1]*2 - dp[low+1][high-1];
                    }
                }else{
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + (int)(1e9+7) : dp[i][j] % (int)(1e9+7);
            }
        }
        return dp[0][len-1];
    }



    static int[][] memo;
    static int[][] pre;
    static int[][] next;
    static byte[] A;
    static int mod = 1_000_000_007;

    public static int countPalindromicSubsequences2(String S) {
        int N = S.length();
        pre = new int[N][4];
        next = new int[N][4];
        memo = new int[N][N];
        for (int [] row : pre) {
            Arrays.fill(row,  -1);
        }
        for (int[] row : next) {
            Arrays.fill(row,  -1);
        }
        A = new byte[N];
        int index = 0;
        for (char c : S.toCharArray()) {
            A[index++] = (byte) (c - 'a');
        }
        int[] last = new int[4];
        Arrays.fill(last, -1);
        for (int i = 0; i < N; i++) {
            last[A[i]] = i;
            for (int k = 0; k < 4; k++) {
                pre[i][k] = last[k];
            }
        }
        Arrays.fill(last, -1);
        for (int i = N - 1; i >= 0; i--) {
            last[A[i]] = i;
            for (int k = 0; k < 4; k++) {
                next[i][k] = last[k];
            }
        }
        return dp(0, N - 1) - 1;
    }

    private static int dp(int i, int j) {
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        int ans = 1;
        if (i <= j) {
            for (int k = 0; k < 4; k++) {
                int i0 = next[i][k];
                // i0 是从i开始的下一个
                int j0 = pre[j][k];
                // j0 是从j开始的前一个
                if (i <= i0 && i0 <= j) {
                    // means at least there is one single character between [i, j], thus + 1
                    ans++;
                }
                if (-1 < i0 && i0 < j0) {
                    // if i0 == -1 means there is no character between [i,j], if i0 == j0 means there is only single character in [i,j]
                    ans += dp(i0 + 1, j0 - 1);
                }
                if (ans >= mod) {
                    ans -= mod;
                }
            }
        }
        memo[i][j] = ans;
        return ans;
    }
}
