package algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenc on 2017/7/3.
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if(nums==null || nums.length < 1){
            return res;
        }
        int start = nums[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i]-nums[i-1] != 1 || i == nums.length-1){
                if(start != nums[i-1]){
                    res.add(""+start + "->" + nums[i-1]);
                }else{
                    res.add(String.valueOf(start));
                }
                start = nums[i];
            }
        }
        if(start != nums[i-1]){
            res.add(""+start + "->" + nums[i-1]);
        }else{
            res.add(String.valueOf(start));
        }
        return res;
    }
}
