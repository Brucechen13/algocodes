package algo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenc on 2017/6/10.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i ++){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }
}
