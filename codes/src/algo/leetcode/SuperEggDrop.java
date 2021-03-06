package algo.leetcode;


public class SuperEggDrop {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N+1][K+1];
        int m = 0;
        while (dp[m][K] < N){
            m++;
            for(int k = 1; k <= K; k ++){
                dp[m][k] = dp[m-1][k-1] + dp[m-1][k] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args){
        System.out.print(new SuperEggDrop().superEggDrop(2, 6));
    }
}
