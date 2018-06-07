package algo.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    Map<Integer, Integer> parents = new HashMap<>();
    Map<Integer, Integer> parSize = new HashMap<>();
    public int longestConsecutive(int[] nums) {
        for(int i = 0; i < nums.length; i ++){
            findParent(nums[i]);
        }
        int max = 0;
        for (Integer key:
             parents.keySet()) {
            if(parSize.get(key) == -1)continue;
            int cur = 0;
            while (parents.get(key) != key){
                key = parents.get(key);
                cur ++;
            }
            max = Math.max(max, cur);
        }
        return 0;
    }

    public void findParent(int val){
        int pre = val-1;
        if(parents.containsKey(pre)){
            parents.put(pre, val);
        }
        int after = val+1;
        if(parents.containsKey(after)){
            parents.put(val, after);
        }else{
            parents.put(val, val);
        }
    }



    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        int localMax = 1;
        int max = 1;
        Arrays.sort(nums);

        for (int i=1;i<nums.length;i++) {
            if (nums[i] == nums[i-1]) {
                // do nothing
            } else if (Math.abs(nums[i]-nums[i-1]) !=1) {
                max = Math.max(max, localMax);
                localMax = 1;
            } else {
                localMax++;
            }
        }
        max = Math.max(max, localMax);
        return max;
    }
}
