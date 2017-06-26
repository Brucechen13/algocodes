package algo.array;

import java.util.Arrays;

/**
 * Created by chenc on 2017/6/26.
 */
public class ValidTriangle {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            for(int j = i+1; j < nums.length; j ++){
                for(int k = j+1; k < nums.length; k ++){
                    if(nums[i]+nums[j] < nums[k]){
                        break;
                    }
                    sum++;
                }
            }
        }
        return sum;
    }

    //better way
    public static int triangleNumber2(int[] A) {
        Arrays.sort(A);
        int count = 0, n = A.length;
        for (int i=n-1;i>=2;i--) {
            int l = 0, r = i-1;
            while (l < r) {
                if (A[l] + A[r] > A[i]) {
                    count += r-l;
                    r--;
                }
                else l++;
            }
        }
        return count;
    }
}
