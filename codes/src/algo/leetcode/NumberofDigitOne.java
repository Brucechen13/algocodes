package algo.leetcode;

public class NumberofDigitOne {
    private int[][][] dp = new int[32][32][2];
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        char[] ns = String.valueOf(n).toCharArray();

        return helper(ns, 0, 0, 0);
    }

    public int helper(char[] ns, int index, int temp, int bound){
        if (index == ns.length) return temp;
        if(dp[index][temp][bound] != 0)return dp[index][temp][bound];

        int ret = 0;
        if(bound == 1){
            ret = helper(ns, index + 1, temp + 1, bound) +
                    9 * helper(ns, index + 1, temp, bound);
        }else {
            if (ns[index] == '0') {
                ret += helper(ns, index + 1, temp, bound);
            } else if (ns[index] == '1') {
                ret += helper(ns, index + 1, temp, 1)
                        + helper(ns, index + 1, temp + 1, 0);
            } else {
                ret += helper(ns, index + 1, temp, 0)
                        + helper(ns, index + 1, temp + 1, 1)
                        + (ns[index] - '1') * helper(ns, index + 1, temp, 1);
            }
        }
        dp[index][temp][bound] = ret;
        return ret;
    }

    public int countDigitOne2(int n) {

        if (n <= 0) return 0;
        int q = n, x = 1, ans = 0;
        do {
            int digit = q % 10;
            q /= 10;
            ans += q * x;
            if (digit == 1) ans += n % x + 1;
            if (digit >  1) ans += x;
            x *= 10;
        } while (q > 0);
        return ans;

    }
}
