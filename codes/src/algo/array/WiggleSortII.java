package algo.array;

import java.util.Arrays;

public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int start = 1, end = nums.length % 2 == 0?nums.length-2:nums.length-1;
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start+=2;
            end-=2;
        }
    }

    public static void main(String[] args){
        int[] a = {1, 5, 1, 1, 6, 4};
        new WiggleSortII().wiggleSort(a);
        System.out.println(a);
    }
}
