package algo.array;

/**
 * Created by chenc on 2017/6/23.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = len*(len+1)/2;
        int cur = 0;
        for(int i = 0; i < nums.length; i ++){
            cur += nums[i];
        }
        return sum-cur;
    }
}
