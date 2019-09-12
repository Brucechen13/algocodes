package algo.array;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        Integer min1 = null, min2 = null, premin = null;
        for(int i = 0; i < nums.length; i ++){
            if(min1==null){
                min1 = nums[i];
            }else if(nums[i] < min1){
                if(min2 == null){
                    min1 = nums[i];
                }else if(premin == null || premin > nums[i]){
                    premin = nums[i];
                }else{
                    min1 = premin;
                    min2 = nums[i];
                    premin = null;
                }
            }else if(min2 == null || min2 > nums[i]) {
                if(nums[i] > min1) {
                    min2 = nums[i];
                }
            }else if(nums[i] > min2){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[] a = new int[]{1,1,-2, 6};
        System.out.println(new IncreasingTripletSubsequence().increasingTriplet(a));
    }
}
