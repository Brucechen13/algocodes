package algo.array;

import java.util.Arrays;

/**
 * Created by chenc on 2017/6/26.
 */
public class MaximumProduct {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        if(nums[0] < 0 && nums[1] < 0 &&
                nums[0]*nums[1] > nums[nums.length-1]*nums[nums.length-2]){
            return nums[0]*nums[1]*nums[nums.length-3];
        }else{
            return nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
        }
    }
}
