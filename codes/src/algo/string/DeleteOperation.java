package algo.string;

/**
 * 很难求解
 */
public class DeleteOperation {
    public int minDistance(String word1, String word2) {
        return findMinSteps(word1, word2, 0);
    }

    public int findMinSteps(String word1, String word2, int step){
        if(word1.equals(word2)){
            return step;
        }
        int minStep = Integer.MAX_VALUE;
        for(int i = 0; i < word1.length(); i ++){
            minStep = Math.min(minStep, findMinSteps(word1.substring(0,i)+word1.substring(i+1), word2, step+1));
        }
        for(int i = 0; i < word2.length(); i ++){
            minStep = Math.min(minStep, findMinSteps(word1, word2.substring(0,i)+word2.substring(i+1), step+1));
        }
        return minStep;
    }

    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        // dp[i][j] stands for distance of first i chars of word1 and first j chars of word2
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args){
        System.out.println(new DeleteOperation().minDistance("spartan",
                "part"));
    }
}
