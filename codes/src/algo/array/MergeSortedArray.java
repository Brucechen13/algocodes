package algo.array;

import java.util.Arrays;

/**
 * Created by chenc on 2017/6/23.
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num3 = Arrays.copyOf(nums1, m);
        int i = 0, j = 0,start = 0;
        while(i < num3.length || j < nums2.length){
            if(j >= nums2.length || num3[i] < nums2[j]){
                nums1[start++] = num3[i];
                i++;
            }else{
                nums1[start++] = nums2[j];
                j++;
            }
        }
    }
}
