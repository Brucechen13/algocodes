package algo.leetcode;

/**
 * Created by chenc on 2017/9/10.
 */
public class MostRegion {
    public static int maxRegion(int[] values, int index, int curSum, int nums, int[] dp){
        if(curSum == 0){
            nums++;
        }
        int startIndex = index;
        while (index <= values.length){
            if(curSum == 0){
                curSum = 0;
                nums++;
                if(index< values.length) {
                    if(dp[index] != 0){
                        nums += dp[index];
                        break;
                    }
                    curSum = values[index];
                }
            }else{
                if(index< values.length) {
                    curSum ^= values[index];
                }
            }
            index++;
        }
        dp[startIndex-1] = nums;
        return nums;
    }
    public static int maxPart(int[] values){
        int[] dp = new int[values.length+1];
        int maxSum = 0;
        for(int i = values.length-1; i >= 0; i --){
            maxSum = Math.max(maxRegion(values, i+1, values[i], 0, dp), maxSum);
        }
        return maxSum;
    }
}
