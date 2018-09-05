package algo.array;

/**
 * Created by chenc on 2017/7/9.
 */
public class FindPeak {
    public int findPeakElement(int[] nums) {
        boolean pre = true;
        for(int i = 1; i < nums.length; i ++){
            int sum = nums[i]-nums[i-1];
            if(pre && sum < 0){
                return i-1;
            }
            pre  = sum>0;
        }
        return pre?nums.length-1:-1;
    }
}
