package algo.leetcode;

import java.util.Arrays;

public class UniqueLetterString {
    public int uniqueLetterString(String S) {
        int len = S.length();
        final int MOD = (int)(1e9);
        int[] first = new int[26];
        int[] second = new int[26];
        int dp = 0, res = 0;
        for(int i = 0; i < len; i ++){
            int ci = S.charAt(i) - 'A';
            dp += (i+1 - first[ci]) - (first[ci] - second[ci]);
            res = (res+dp)%MOD;
            second[ci] = first[ci];
            first[ci] = i+1;
        }
        return res;
    }

    public int uniqueLetterString2(String S) {
        int len = S.length();
        final int MOD = (int)(1e9);
        int[] pre = new int[26];
        int[] next = new int[26];
        int[] dises = new int[len];//之前出现当前位置字符的距离
        Arrays.fill(next, len);
        Arrays.fill(pre, -1);
        for(int i = 0; i < len; i ++){
            dises[i] = i - pre[S.charAt(i) - 'A'];
            pre[S.charAt(i) - 'A'] = i;
        }
        int res = 0;
        for(int i = len-1; i >= 0; i --){
            int nlen = next[S.charAt(i) - 'A'] - i;//之后出现当前字符的距离
            res = (res + (dises[i] * nlen)) % MOD;//只统计该字符出现一次的字符串数
            next[S.charAt(i) - 'A'] = i;
        }
        return res;
    }

    public static void main(String[] args){
        System.out.print(new UniqueLetterString().uniqueLetterString2("ABC"));
    }
}
