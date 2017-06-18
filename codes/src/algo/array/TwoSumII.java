package algo.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenc on 2017/6/18.
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i ++){
            if(indexMap.containsKey(target-numbers[i])){
                ans[0] = indexMap.get(target-numbers[i])+1;
                ans[1] = i+1;
                return ans;
            }else{
                indexMap.put(numbers[i], i);
            }
        }
        return ans;
    }
}
