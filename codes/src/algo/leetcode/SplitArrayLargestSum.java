package algo.leetcode;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        if(nums==null || nums.length < 1)return -1;
        int left = nums[0];
        int right = Integer.MAX_VALUE;
        while (left < right){
            int target = left + (right-left)/2;
            int res = searchArray(nums, target, m);
            if(res > 0){
                right = target;
            }else{
                left = target+1;
            }
        }
        return left;
    }

    public int searchArray(int[] nums, int target, int m){
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] > target - sum){
                sum = 0;
                m --;
            }
            sum += nums[i];
            if(sum > target || sum < nums[i])return -1;
        }
        return m;
    }
}
