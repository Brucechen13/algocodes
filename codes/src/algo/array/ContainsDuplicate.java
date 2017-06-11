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
    //Contains Duplicate II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
}
