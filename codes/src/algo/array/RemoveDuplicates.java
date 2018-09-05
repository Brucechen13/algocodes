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
    public int removeDuplicates2(int[] nums) {
        if(nums==null || nums.length < 1){
            return 0;
        }
        int sum = 0;
        int pre = nums[0], len = 1;
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] != pre || len < 2){
                sum ++;
                if(nums[i] != pre) {
                    pre = nums[i];
                    len = 1;
                }else{
                    len++;
                }
                nums[sum] = nums[i];
            }
        }
        return sum;
    }
}
