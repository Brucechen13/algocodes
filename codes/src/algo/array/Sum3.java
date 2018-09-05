package algo.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenc on 2017/8/3.
 */
public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
    public int threeSumClosest(int[] nums, int target) {
        if(nums.length <  3){
            return -1;
        }
        Arrays.sort(nums);
        int closest = nums[0]+nums[1]+nums[2];
        for(int i = 0; i < nums.length-2; i ++){
            int start = i+1, end = nums.length-1;
            while (start < end){
                //System.out.println(nums[i] + " " + nums[start] + " " + " " + nums[end]);
                int curSum = nums[i] + nums[start] + nums[end];
                closest = Math.abs(closest-target)<Math.abs(curSum-target)?closest:curSum;
                if(curSum == target){
                    return target;
                }else if(curSum < target){
                    start++;
                }else{
                    end--;
                }
            }

        }
        return closest;
    }
}
