package algo.array;

public class FindMinimumRotatedSortedArrayII {
    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length;

        while(l < r){
            int mid = (l+r)/2;

            if(nums[mid] < nums[r]){
                r = mid;
            }else if(nums[mid] > nums[r]){
                l = mid + 1;
            }else{
                l++;
            }
        }
        return nums[l];
    }
}
