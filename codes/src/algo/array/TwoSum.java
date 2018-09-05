package algo.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenc on 2017/6/23.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        res[0] = res[1]=0;
        for(int i = 0; i < nums.length; i ++){
            if(cache.containsKey(nums[i])){
                res[0] = cache.get(nums[i]);
                res[1] = i;
                return res;
            }
            cache.put(target-nums[i], i);
        }
        return res;
    }
}
