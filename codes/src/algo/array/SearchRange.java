package algo.array;

/**
 * Created by chenc on 2017/8/7.
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if(nums==null || nums.length < 1){
            return res;
        }
        int start = 0, end = nums.length-1;
        while (start <= end){
            int mid = (start+end)/2;
            if(nums[mid] == target){
                res[0] = res[1] = mid;
                start = end = mid;
                while (start >= 0 && nums[start] == nums[mid]){
                    start--;
                }
                res[0] = start;
                while (end < nums.length && nums[end] == nums[mid]){
                    end++;
                }
                res[1] = end;
                return res;
            }else if(nums[mid] >target){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return res;
    }
}
