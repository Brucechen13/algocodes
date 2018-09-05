package algo.array;

/**
 * Created by chenc on 2017/8/10.
 */
public class SearchRotated {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    public boolean search2(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while (start <= end){
            int mid = (start+end)/2;
            if(nums[mid] == target){
                return true;
            }
            if( (nums[start] == nums[mid]) && (nums[end] == nums[mid]) ) {
                ++start; --end;}
            else if(nums[mid] >= nums[start]){
                if(nums[mid] > target && nums[start] <= target) {
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }else{
                if(nums[mid] < target && nums[end] >= target){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }
        }
        return false;
    }
}
