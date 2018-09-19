package algo.hiho;

import java.util.Scanner;

public class Hiho8dpcomp {

    public static int cal(int x, int M){
        int count = 0;
        for(int i = 0; i < M; i ++){
            if(((x>>i)&1) == 1)count++;
        }
        return count;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int M = scanner.nextInt();
        int Q = scanner.nextInt();
        int margin = (int)Math.pow(2, M);
        int[] vals = new int[n];
        for(int i = 0; i < n; i ++)vals[i] = scanner.nextInt();

        int[][] dp = new int[n][margin];
        int HEAD = 1 << (M-1);
        dp[0][HEAD] = vals[0];
        for(int i = 1; i < n; i ++){
            for(int j = 0; j < margin; j ++){
                dp[i][j/2] = Math.max(dp[i][j/2], dp[i-1][j]);
                if(cal(j/2, M) < Q){
                    dp[i][j/2 + HEAD] = Math.max(dp[i][j/2 + HEAD], dp[i-1][j] + vals[i]);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < margin; i ++)max = Math.max(max, dp[n-1][i]);
        System.out.println(max);
    }
}
