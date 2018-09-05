package algo.array;

/**
 * 485. Max Consecutive Ones
 * Created by chenc on 2017/5/22.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = -1;
        int maxLen = 0;
        for(int i = 0;i < nums.length; i ++){
            if(nums[i] == 0 && start != -1) {
                maxLen = Math.max(maxLen, i - start);
                start = -1;
            }else if(nums[i] == 1 && start == -1){
                start = i;
            }
        }
        if(start != -1){
            maxLen = Math.max(maxLen, nums.length - start);
        }
        return maxLen;
    }
}
