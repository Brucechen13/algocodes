package algo.array;

/**
 * Created by chenc on 2017/8/2.
 */
public class ContainerMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length-1;
        int curLen = 1;
        while (left < right){
            while(left < height.length && height[left] < curLen){
                left++;
            }
            while(right > 0 && height[right] < curLen){
                right--;
            }
            if(left < right) {
                max = Math.max(max, curLen * (right - left));
                //System.out.println(curLen + " " + left + " " + right);
                curLen++;
            }
        }
        return max;
    }
    public int maxArea2(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right])
                    * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
}
