package algo.competition;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class ValidPermutations {
    public int numPermsDISequence(String S) {
        int n = S.length(), mod = (int)1e9 + 7;
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 0; j <= n; j++) dp[0][j] = 1;
        for (int i = 0; i < n; i++)
            if (S.charAt(i) == 'I')
                for (int j = 0, cur = 0; j < n - i; j++)
                    dp[i + 1][j] = cur = (cur + dp[i][j]) % mod;
            else
                for (int j = n - i - 1, cur = 0; j >= 0; j--)
                    dp[i + 1][j] = cur = (cur + dp[i][j + 1]) % mod;
        return dp[n][0];
    }

    public int numPermsDISequence2(String S) {
        int MOD = (int)1e9 + 7;
        int n = S.length();
        int[] dp = new int[n+1];
        List<Integer> meno = new ArrayList<>();
        meno.add(1);
        //memo.push_back(1);
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == 'D') {
                meno.add(0);
                for (int j = i; j >= 0; j--) {
                    //dp[j] = (dp[j] + dp[j + 1]) % MOD;
                    meno.set(j, (meno.get(j) + meno.get(j+1))%MOD);
                }
            } else {
                meno.add(0, 0);
                for (int j = 1; j <= i + 1; j++) {
                    //dp[j] = (dp[j] + dp[j - 1]) % MOD;
                    meno.set(j, (meno.get(j) + meno.get(j-1))%MOD);
                }
            }
        }
        int sum = 0;
        for (int num: meno) {
            sum = (sum + num) % MOD;
        }
        return sum;
    }

    public static void main(String[] args){
        //853197538
        System.out.println(new ValidPermutations().numPermsDISequence2("IDDDIIDIIIIIIIIDIDID"));
    }
}
