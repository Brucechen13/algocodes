package algo.array;

public class WiggleSubsequence {

    public int search(int[] nums, boolean seachBig){
        int res = 1;
        int start = nums[0];
        int cur = 1;
        while (cur < nums.length){
            int target = start;
            if(seachBig){
                while (cur < nums.length && nums[cur] >= target){
                    target = Math.max(target, nums[cur++]);
                }
            }else{
                while (cur < nums.length && nums[cur] <= target){
                    target = Math.min(target, nums[cur++]);
                }
            }
            //System.out.println(target);
            boolean find = seachBig?nums[cur-1]>start:nums[cur-1]<start;
            if(find) {
                res++;
                seachBig = !seachBig;
                start = nums[cur-1];
            }else{
                break;
            }
        }
        return res;
    }

    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        return Math.max(search(nums, true), search(nums, false));
    }

    public static void main(String[] args){
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(new WiggleSubsequence().wiggleMaxLength(nums));
    }
}
