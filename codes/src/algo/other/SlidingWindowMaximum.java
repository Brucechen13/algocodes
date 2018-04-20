package algo.other;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int begin = 0;
        int last = 0;
        int[] queue = new int[nums.length];
        int[] res = new int[nums.length-k+1];
        for(int i = 0; i < nums.length; i ++){
            if(queue[begin] + k < i + 1){
                begin++;
            }
            if(last == 0 || nums[queue[last-1]] >+ nums[i]){
                queue[last++] = i;
            }else{
                while (last>begin && last>0 && nums[queue[last-1]] < nums[i]){
                    last--;
                }
                queue[last++] = i;
            }
            //i == k-1
            if(i+1 >= k){
                res[i+1-k] = nums[queue[begin]];
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] data = {1,3,-1,-3,5,3,6,7};
        new SlidingWindowMaximum().maxSlidingWindow(data, 3);
    }
}
