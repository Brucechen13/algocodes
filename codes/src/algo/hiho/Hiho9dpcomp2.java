package algo.hiho;

import java.util.Arrays;
import java.util.Scanner;

public class Hiho9dpcomp2 {
    public static int MOD = (int)(1e9)+7;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][][] dp = new int[N][M][2048];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++)Arrays.fill(dp[i][j], -1);
        }
        System.out.println(dp(N, M, 0, 0, 0, dp));
    }

    public static int dp(int N, int M, int i, int j, int status, int[][][] dp){
        int sum = 0;
        if(i == N)return 1;
        if(dp[i][j][status] != -1)return dp[i][j][status];
        //System.out.println(i + " " + j + " " + status);
        if(((status>>j) & 1) == 0){
            boolean isRight = (j == M-1) || (((status>>(j+1))&1) == 1);
            boolean isDown = (i == N-1) || (((status>>(j+M))&1) == 1);
            //右边和下边都有占用
            if(isDown && isRight){
                sum = 0;
            }else if(isRight){
                //右边有占用
                sum = dp(N, M, i, j, status + (1<<j) + (1<<(j+M)), dp);
            }else if(isDown){
                //下边有占用
                sum = dp(N, M, i, j, status + (1<<j) + (1<<(j+1)), dp);
            }else{
                //都无占用
                sum = dp(N, M, i, j, status + (1<<j) + (1<<(j+1)), dp)
                        + dp(N, M, i, j, status + (1<<j) + (1<<(j+M)), dp);
            }
        }else{
            if(j == M - 1){
                sum = dp(N, M, i+1, 0, status>>M, dp);
            }else{
                sum = dp(N, M, i, j+1, status, dp);
            }
        }
        dp[i][j][status] = sum;
        return sum%MOD;
    }
}
