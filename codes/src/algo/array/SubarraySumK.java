package algo.array;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K
 * Created by chenc on 2017/5/22.
 */
public class SubarraySumK {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i ++){
            sum = 0;
            for(int j = i; j < nums.length;j ++){
                sum += nums[j];
                if(sum == k)count++;
            }
        }
        return count;
    }

    /**
     * another solution, use hashmap to store the count of special num
     * @param a
     * @param k
     * @return
     */
    public int subarraySum2(int[] a, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum-k);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        return count;
    }
}
