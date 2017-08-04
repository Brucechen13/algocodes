package algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenc on 2017/7/11.
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> pre = new ArrayList<Integer>();
        res.add(pre);
        for(int i = 0; i < nums.length; i ++){
            List<Integer> cur = new ArrayList<Integer>();
            addLast(nums, i, res, cur);
        }

        return res;
    }

    public void addLast(int[] nums, int index, List<List<Integer>> res, List<Integer> cur){
        for(int i = index+1; i < nums.length; i ++){
            cur.add(nums[i]);
            List<Integer> addL = new ArrayList<Integer>();
            addL.addAll(cur);
            res.add(addL);
            if(i != index+1 && nums[i] == nums[i-1]){
                continue;
            }
            addLast(nums, i+1, res, cur);
        }
    }
}
