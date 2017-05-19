package algo.array;

import java.util.Arrays;

/**
 * Created by chenc on 2017/5/19.
 */
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i +=2){
            sum += nums[i];
        }
        return sum;
    }

    public void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid-1);
        quickSort(nums, mid+1, end);
    }

    public int partition(int[] nums, int start, int end){
        int i = start, j = end+1;
        int v = nums[start];
        while (true){
            while (nums[++i] < v){
                if(i == end)
                    break;
            }
            while ((nums[--j] > v)){
                if(j == start)
                    break;
            }
            if(i >= j){
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        nums[j] = v;
        return j;
    }
}
