package algo.hiho;

import java.util.Scanner;

public class Hiho8dpCompress {

    public static int cal(int val, int M){
        int count = 0;
        for(int i = 0; i < M; i ++){
            if(((val>>(i+1))&1) == 1)count++;
        }
        return count;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int M = scanner.nextInt();
        int Q = scanner.nextInt();
        int[] vals = new int[n];
        for(int i = 0; i < n; i ++){
            vals[i] = scanner.nextInt();
        }

        int margin = (int)Math.pow(2, M);
        int[][] dp = new int[n][margin];
        dp[0][1] = vals[0];
        for(int i = 1; i < n; i ++){
            for(int j = 0; j < margin; j ++){
                dp[i][j/2*2] = Math.max(dp[i][j/2*2], dp[i-1][j]);
                if(cal(j/2, M) < Q){
                    dp[i][j/2*2 + 1] = Math.max(dp[i][j/2*2 + 1],
                            dp[i-1][j] + vals[i]);
                }else{
                    dp[i][j/2*2 + 1] = 0;
                }
            }
        }
        int max = 0;
        for(int i = 0;  i < margin; i ++){
            max = Math.max(max, dp[n-1][i]);
        }
        System.out.println(max);
    }
}
