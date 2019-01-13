package algo.array;


public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1==0&&len2==0)return -1;

        if(len1 > len2){
            return findMedianSortedArrays(nums2, nums1);
        }

        if(len1 == 0){
            if(len2 % 2 == 0){
                return (1.0*nums2[len2/2 - 1] + 1.0*nums2[len2/2])*0.5;
            }else{
                return nums2[len2/2];
            }
        }


        int k = (len1+len2+1)/2;

        int low = 0;
        int high = len1;

        while (low <= high){
            int m1 = (low+high)/2;
            int m2 = k - m1;

            int leftMax = m1 == nums1.length?Integer.MAX_VALUE:nums1[m1];
            int leftMin = m1 == 0?Integer.MIN_VALUE:nums1[m1-1];
            int rightMax = m2==nums2.length?Integer.MAX_VALUE:nums2[m2];
            int rightMin = m2 == 0?Integer.MIN_VALUE:nums2[m2-1];

            if(leftMax >= rightMin && rightMax >= leftMin){
                if((len1 + len2) % 2 == 0){
                    return (Math.max(1.0*leftMin, 1.0*rightMin) +
                            Math.min(1.0*leftMax, 1.0*rightMax)) / 2;
                }else{
                    return Math.max(1.0*leftMin, 1.0*rightMin);
                }
            }else if(leftMax < rightMin){
                low = m1+1;
            }else{
                high = m1-1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] a = {2};
        int[] b = {};
        System.out.println(new MedianTwoSortedArrays().findMedianSortedArrays(a, b));
    }
}