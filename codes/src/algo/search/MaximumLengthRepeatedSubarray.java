package algo.search;


import java.util.*;

public class MaximumLengthRepeatedSubarray {
    public int findLength(int[] A, int[] B) {
        int len = 0;
        int[][] dp = new int[A.length+1][B.length+1];
        int[][] dp0 = new int[A.length+1][B.length+1];
        for(int i = 0; i < A.length; i ++){
            if(A[i] == B[0]){
                dp0[i][0] = 1;
                dp[i][0] = 1;
            }
        }
        for(int i = 0; i < B.length; i ++){
            if(A[0] == B[i]){
                dp0[0][i] = 1;
                dp[0][i] = 1;
            }
        }
        for(int i = 1 ; i < A.length; i ++){
            for(int j = 1 ;j < B.length; j ++){
                if(A[i] == B[j]){
                    dp0[i][j] = dp0[i-1][j-1]+1;
                }else{
                    dp0[i][j] = 0;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.max(dp[i][j], dp0[i][j]);
            }
        }
        return dp[A.length-1][B.length-1];
    }
    public static void main(String[] args){
        System.out.println(new MaximumLengthRepeatedSubarray().findLength(
                new int[]{0,0,0,0,0,0,0,0,0}, new int[]{0,0,0,0,0,0}
        ));
    }
}
