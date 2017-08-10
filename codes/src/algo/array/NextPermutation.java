package algo.array;

/**
 * Created by chenc on 2017/8/7.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int index = nums.length-1;
        while (index > 0 && nums[index-1] > nums[index]){
            index--;
        }
        reverse(nums, Math.max(0, index-1), nums.length-1);
    }

    public void reverse(int[]nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;end--;
        }
    }
}

/1-2-3-4 1-2-4-3
/4-3-2-1
/4-1-3-2 4-2-1-3

 3 5 4 2 1
 4 1 2 3 5