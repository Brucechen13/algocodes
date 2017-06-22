package algo.array;

/**
 * Created by chenc on 2017/6/22.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] >= target)
                return i;
        }
        return nums.length;
    }
}
