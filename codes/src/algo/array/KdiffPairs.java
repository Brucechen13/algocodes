package algo.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenc on 2017/5/22.
 */
public class KdiffPairs {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int lastIndex = Integer.MAX_VALUE;
        for(int i = 0 ; i < nums.length; i ++){
            if(nums[i] == lastIndex){
                continue;
            }
            for(int j = i+1; j < nums.length; j ++){
                if(j -i == k){
                    count++;
                    break;
                }else if(j-i > k){
                    break;
                }
            }
            lastIndex =nums[i];
        }
        return count;
    }

    /**
     * another solution
     * @param nums
     * @param k
     * @return
     */
    public int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)   return 0;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }
}
