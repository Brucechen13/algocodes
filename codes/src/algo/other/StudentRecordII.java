package algo.other;

public class StudentRecordII {
    public int checkRecord(int n) {
        int[][][] dp = new int[n+1][3][2];
        dp[0][0][0] = dp[0][0][1] = dp[0][1][0] = 1;
        dp[0][1][1] = 0;
        for(int i = 1; i < n; i ++){
            dp[i][0][0] = modAdd(modAdd(dp[i-1][0][0], dp[i-1][1][0]), dp[i-1][2][0]);
            dp[i][1][0] = modAdd(dp[i-1][0][0], 0);
            dp[i][2][0] = modAdd(dp[i-1][1][0], 0);
            dp[i][0][1] = modAdd(modAdd(modAdd(dp[i-1][0][0], dp[i-1][0][1]), modAdd(dp[i-1][1][0], dp[i-1][1][1])),
                    modAdd(dp[i-1][2][1], dp[i-1][2][0]));
            dp[i][1][1] = modAdd(dp[i-1][0][1], 0);
            dp[i][2][1] = modAdd(dp[i-1][1][1], 0);
        }
        return modAdd(modAdd(dp[n-1][0][0],  dp[n-1][0][1]), modAdd(modAdd(dp[n-1][1][0], dp[n-1][1][1]),
        modAdd(dp[n-1][2][0], dp[n-1][2][1])));
    }

    public int modAdd(int val1, int val2){
        int mod = (int)10E9+7;
        return (val1%mod + val2%mod)%mod;
    }

    private static final int MOD = 1000000000 + 7;

    public int checkRecord2(int n) {
        int[][] p = new int[][] {
                {1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0}
        };

        int[][] ret = new int[6][6];
        for (int i = 0; i < 6; i++) {
            ret[i][i] = 1;
        }

        for (int i = 0; i < 17; i++) {
            if ((n & (1 << i)) > 0) {
                ret = multiply(ret, p);
            }
            p = multiply(p, p);
        }

        long ans = 0;
        for (int i = 0; i < 6; i++) {
            ans += ret[i][0];
        }

        return (int) (ans % MOD);
    }

    public int[][] multiply(int[][] A, int [][] B) {
        final int[][] ret = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                long t = 0;
                for (int k = 0; k < 6; k++) {
                    t += (long) A[i][k] * B[k][j];
                }
                ret[i][j] = (int) (t % MOD);
            }
        }

        return ret;
    }
}
