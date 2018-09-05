package algo.array;

/**
 * 152. Maximum Product Subarray
 * hard to understand
 * Created by chenc on 2017/5/23.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxherepre = nums[0];
        int minherepre = nums[0];
        int maxsofar = nums[0];//保存取值为正的最值
        int maxhere, minhere;//保存取值为负的最值

        for (int i = 1; i < nums.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * nums[i], minherepre * nums[i]), nums[i]);
            minhere = Math.min(Math.min(maxherepre * nums[i], minherepre * nums[i]), nums[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        return maxsofar;
    }
}
