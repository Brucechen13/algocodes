package algo.leetcode;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];//dp[i][j] (0,i)word1 (o,j)word2 equal min ops
        for(int i = 0; i < len1+1; i ++){
            dp[i][0] = i;
        }
        for(int j = 0; j < len2+1; j ++){
            dp[0][j] = j;
        }
        for(int i = 0; i < word1.length(); i ++){
            for(int j = 0; j < word2.length(); j ++){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }else{
                    int a = dp[i][j+1];
                    int b = dp[i+1][j];
                    int c = dp[i][j];
                    dp[i+1][j+1] = Math.min(Math.min(a, b), c) + 1;
                }
            }
        }
        return dp[len1][len2];
    }



    // 1-d array for dp
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2+1];
        for (int i = 1; i <= len2; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            int prev = i;
            for (int j = 1; j <= len2; j++) {
                int cur;
                // Last characters are the same
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    cur = dp[j-1];
                } else {
                    // If the last characters are different:
                    // 1) Replace the current last character with the target last character
                    // 2) Insert the target last character
                    // 3) Delete current last character
                    cur = 1 + Math.min(
                            dp[j-1] /* substitution */,
                            Math.min(prev /* insertion */, dp[j] /* deletion */));
                }
                dp[j-1] = prev;
                prev = cur;
            }
            dp[len2] = prev;
        }
        return dp[len2];
    }
}
