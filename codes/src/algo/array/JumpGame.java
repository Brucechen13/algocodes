package algo.array;

/**
 * Created by chenc on 2017/10/2.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int index = nums.length-1;
        for(int i = nums.length-2; i >= 0; i --){
            if(i + nums[i] >= index){
                index = i;
            }
        }
        return index == 0;
    }
}
