package algo.leetcode;

public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int max = 0;
        while (left < right){
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if(leftMax < rightMax){
                max += (leftMax - height[left]);
                left++;
            }else{
                max += (rightMax - height[right]);
                right--;
            }
        }
        return max;
    }
}
