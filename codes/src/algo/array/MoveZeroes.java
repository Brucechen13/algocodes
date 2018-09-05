package algo.array;

/**
 * 283. Move Zeroes
 * Created by chenc on 2017/5/23.
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0 && zeroIndex == -1){
                zeroIndex = i;
            }else if(nums[i] != 0 && zeroIndex != -1){
                //前方有0的补位
                nums[zeroIndex++] = nums[i];
                nums[i] = 0;
                if(nums[zeroIndex] != 0){
                    zeroIndex = -1;
                }
            }
        }
    }
}
