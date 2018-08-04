package algo.other;

import java.util.HashMap;
import java.util.Map;

public class PreimageSizeFactorialZeroes {
    public int preimageSizeFZF(int K) {
        long low = 0, high = (long)K * 5;
        while (low <= high){
            System.out.println(low + " " + high);
            long mid = low + (high - low)/2;
            int res = getZeroSize(mid);
            if(res < K){
                low = mid+1;
            }else if(res > K){
                high = mid - 1;
            }else{
                return 5;
            }
        }
        return 0;
    }

    public int getZeroSize(long val){
        int res = 0;
        while (0 < val){
            res += val / 5;
            val /= 5;
        }
        return res;
    }
}
