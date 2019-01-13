package algo.array;

public class HouseRobberII {
    public int rob(int[] nums) {
        int search0 = searchMax(nums, 0);
        int search1 = searchMax(nums, 1);
        return Math.max(search0, search1);
    }

    public int searchMax(int[] nums, int index){
        int pre1 = 0;
        int pre2 = 0;

        int res = 0;
        for(int i = index; i < nums.length; i ++){
            res += Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = res;
        }
        return Math.max(pre1, pre2);
    }
}
