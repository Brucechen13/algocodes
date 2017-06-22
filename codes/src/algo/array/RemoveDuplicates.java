package algo.array;

/**
 * Created by chenc on 2017/6/22.
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if(nums==null || nums.length <1){
            return 0;
        }
        int len = 0;
        int pre = nums[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] != pre){
                pre = nums[i];
                len++;
                nums[len] = nums[i];
            }
        }
        return len;
    }
}
