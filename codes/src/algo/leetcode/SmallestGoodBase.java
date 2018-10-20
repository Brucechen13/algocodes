package algo.leetcode;

public class SmallestGoodBase {
    public String smallestGoodBase(String n) {
        long left = 2;
        long num = Long.parseLong(n);
        long right = num;
        while (left < right){
            long mid = left + (right-left)/2;
            if(isFit(num, mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return String.valueOf(left);
    }

    public boolean isFit(long n, long k){
        long res = 1;
        while (res < n){
            res += 1*k;
        }
        return res == n;
    }

    public String smallestGoodBase2(String n) {
        long num = Long.valueOf(n);

        for (int m = (int)(Math.log(num + 1) / Math.log(2)); m >= 2; m--) {
            long l = (long)(Math.pow(num + 1, 1.0 / m));
            long r = (long)(Math.pow(num, 1.0 / (m - 1)));

            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long f = 0L;
                for (int i = 0; i < m; i++, f = f * k + 1);

                if (num == f) {
                    return String.valueOf(k);
                } else if (num < f) {
                    r = k - 1;
                } else {
                    l = k + 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }
}
