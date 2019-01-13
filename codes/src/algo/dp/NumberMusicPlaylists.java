package algo.dp;

public class NumberMusicPlaylists {
    public int numMusicPlaylists(int N, int L, int K) {
        if (N == 0 || L == 0 || N > L) return 0;
        if (N  == 1 && L == 1) return 1;
        int mod = 1000000007;
        long[][] dp = new long[N + 1][L + 1];
        // initialize dp
        dp[0][0] = 1;

        for (int l = 1; l <= L; l++) {
            for (int n = 1; n <= N; n++) {
                if (N - n + 1 > 0) dp[n][l] = ((N - n + 1) * dp[n - 1][l - 1]) % mod;
                if (n - K > 0) dp[n][l] = (dp[n][l] + (((n - K) * dp[n][l - 1]) % mod)) % mod;
            }
        }
        return (int) dp[N][L];
    }

    long mod = (long)1e9 + 7;
    public int numMusicPlaylists2(int N, int L, int K) {
        long[][] dp = new long[N + 1][L + 1];
        for (int i = K + 1; i <= N; ++i)
            for (int j = i; j <= L; ++j)
                if ((i == j) || (i == K + 1))
                    dp[i][j] = factorial(i);
                else
                    dp[i][j] = (dp[i - 1][j - 1] * i + dp[i][j - 1] * (i - K))  % mod;
        return (int) dp[N][L];
    }

    long factorial(int n) {
        return n > 0 ? factorial(n - 1) * n % mod : 1;
    }
}
