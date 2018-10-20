package algo.leetcode;


import java.util.Arrays;

public class SumSubsequenceWidths {

    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long c = 1, res = 0, mod = (long)1e9 + 7;
        for (int i = 0; i < A.length; ++i, c = (c << 1) % mod)
            res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;
        return (int)((res + mod) % mod);
    }

    public static void main(String[] args){
        int[] A = {2,1,3, 20000};
        System.out.print(new SumSubsequenceWidths().sumSubseqWidths(A));
    }
}
