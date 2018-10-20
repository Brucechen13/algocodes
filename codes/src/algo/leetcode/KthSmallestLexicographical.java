package algo.leetcode;

public class KthSmallestLexicographical {

    public int findKthNumber(int n, int k){
        int cur = 1;
        k -= 1;
        while (k > 0){
            int step = calSteps(n, cur, cur+1);
            if(step < k){
                k -= step;
                cur += 1;
            }else{
                k -= 1;
                cur *= 10;
            }
        }
        return cur;
    }

    public int calSteps(int n, long n1, long n2){
        int step = 0;
        while (n1 <= n){
            step += Math.min(n+1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return step;
    }
}
