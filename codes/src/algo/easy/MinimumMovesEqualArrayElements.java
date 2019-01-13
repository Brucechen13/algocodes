package algo.easy;

import java.util.Arrays;

public class MinimumMovesEqualArrayElements {
    public int minMoves(int[] nums) {
        int step = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i ++){
            min = Math.min(min, nums[i]);
        }
        for(int i = 0; i < nums.length; i ++){
            step += nums[i] - min;
        }
        return step;
    }
}
