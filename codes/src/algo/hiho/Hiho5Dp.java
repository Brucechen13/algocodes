package algo.hiho;

import java.util.Scanner;

public class Hiho5Dp {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n];
        int pre = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j <= i; j ++){
                int tmp = dp[j];
                int val = scanner.nextInt();
                if(j == 0){
                    dp[j] += val;
                }else{
                    dp[j] = Math.max(pre, dp[j]) + val;
                }
                pre = tmp;
                if(j == i)pre=0;
            }
        }
        int max = 0;
        for(int i = 0; i < n; i ++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
