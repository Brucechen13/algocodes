package algo.other;

public class DecodeWaysII {
    public int numDecodings(String s) {
        int n = s.length();
        long[][][] dp = new long[n][n][3];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                dp[i][i][0] = 9;
                dp[i][i][1] = 1;
                dp[i][i][2] = 1;
            } else {
                dp[i][i][0] = 1;
                if (s.charAt(i) == '1') {
                    dp[i][i][1] = 1;
                } else if (s.charAt(i) == '2') {
                    dp[i][i][2] = 1;
                }else if(s.charAt(i) == '0'){
                    dp[i][i][0] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) == '*') {
                    dp[i][j][0] = dp[i][j - 1][0] * 9 + dp[i][j - 1][1] * 9 + dp[i][j - 1][2] * 6;
                    dp[i][j][1] = dp[i][j - 1][0];
                    dp[i][j][2] = dp[i][j - 1][0];
                } else if (s.charAt(j) <= '6') {
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1] + dp[i][j - 1][2];
                    if (s.charAt(j) == '1') {
                        dp[i][j][1] = dp[i][j - 1][0];
                    } else if (s.charAt(j) == '2') {
                        dp[i][j][2] = dp[i][j - 1][0];
                    }else if(s.charAt(j) == '0'){
                        dp[i][j][0] = dp[i][j-1][1] + dp[i][j - 1][2];
                    }
                } else {
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
                }
                dp[i][j][0] = (long)(dp[i][j][0] % (1e9+7));
                dp[i][j][1] = (long)(dp[i][j][1] % (1e9+7));
                dp[i][j][2] = (long)(dp[i][j][2] % (1e9+7));
            }
        }
        return (int)((dp[0][n - 1][0])%(1e9+7));
    }


    public int numDecodings2(String s) {
        /* initial conditions */
        long[] dp = new long[s.length()+1];
        dp[0] = 1;
        if(s.charAt(0) == '0'){
            return 0;
        }
        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        /* bottom up method */
        for(int i = 2; i <= s.length(); i++){
            char first = s.charAt(i-2);
            char second = s.charAt(i-1);

            // For dp[i-1]
            if(second == '*'){
                dp[i] += 9*dp[i-1];
            }else if(second > '0'){
                dp[i] += dp[i-1];
            }

            // For dp[i-2]
            if(first == '*'){
                if(second == '*'){
                    dp[i] += 15*dp[i-2];
                }else if(second <= '6'){
                    dp[i] += 2*dp[i-2];
                }else{
                    dp[i] += dp[i-2];
                }
            }else if(first == '1' || first == '2'){
                if(second == '*'){
                    if(first == '1'){
                        dp[i] += 9*dp[i-2];
                    }else{ // first == '2'
                        dp[i] += 6*dp[i-2];
                    }
                }else if( ((first-'0')*10 + (second-'0')) <= 26 ){
                    dp[i] += dp[i-2];
                }
            }

            dp[i] %= 1000000007;
        }
        /* Return */
        return (int)dp[s.length()];
    }
}
