package algo.array;

import java.util.*;

/**
 * Created by chenc on 2017/9/25.
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> cur = new ArrayList<Integer>();
        findSum(candidates, target, 0, 0, res, cur);
        Set<List<Integer>> res0 = new HashSet<List<Integer>>(res);

        return new ArrayList<List<Integer>>(res0);
    }
    public void findSum(int[] candidates, int target, int start, int curSum, List<List<Integer>> res, List<Integer> cur){
        for(int i = start; i < candidates.length; i ++){
            if(curSum + candidates[i] > target){
                return;
            }
            List<Integer> cur0 = new ArrayList<Integer>(cur);
            if(curSum+candidates[i] < target){
                cur0.add(candidates[i]);
                findSum(candidates, target, i+1, curSum + candidates[i], res, cur0);
            }else{
                //add
                cur0.add(candidates[i]);
                res.add(cur0);
            }
        }
    }
}
