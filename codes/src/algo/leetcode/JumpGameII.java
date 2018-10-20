package algo.leetcode;

public class JumpGameII {
    public int jump(int[] nums) {
        int[] dists = new int[nums.length];
        for(int i = 0; i < nums.length; i ++){
            int len = Math.min(nums[i], nums.length-i-1);
            for(int j = len; j > 0; j --){
                if(dists[i+j] == 0 || (dists[i+j] > dists[i] + 1)){
                    dists[i+j] = dists[i] + 1;
                }else{
                    break;
                }
            }
        }
        return dists[nums.length-1];
    }

    public int jump2(int[] nums) {
        int start=0,end=0,step=0;
        while(end<nums.length-1){
            int i=start;
            int j=end;
            for(;i<=j;i++){
                end=Math.max(end, i+nums[i]);
            }
            start=j+1;
            step++;
        } return step;
    }

    public int jump3(int[] nums){
        int count = 1;
        int next = 0;
        int index = 0;
        while (next < nums.length){
            count ++;
            int preNext = next+1;
            for(int i = index; i <= next && i < nums.length; i ++){
                next = Math.max(next, i + nums[i]);
            }
            index = preNext;
        }
        return count;
    }
}
