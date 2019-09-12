package algo.interesting;

/**
 * https://leetcode.com/problems/integer-replacement/
 */

public class IntegerReplacement {
    public int integerReplacement(int n) {
        if(n == 1){return 0;}
        if(n == Integer.MAX_VALUE){
            n--;
            return integerReplacement(n);
        }
        if(n%2 == 0){
            n = n/2;
            return 1+integerReplacement(n);
        }
        if(n%2 != 0){
            return 1+Math.min(integerReplacement(n+1), integerReplacement(n-1));
        }
        return 1;
    }

    public int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE) return 32;
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                if ( (n + 1) % 4 == 0 && (n - 1 != 2)) {
                    n++;
                } else n--;
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new IntegerReplacement().integerReplacement(7));
    }
}
