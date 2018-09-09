package algo.competition;

public class LeetRLEIterator {
    class RLEIterator {

        private int[] nums;
        private int index;
        private int count;

        public RLEIterator(int[] A) {
            this.nums = A;
            this.index = 0;
            this.count = 0;
        }

        public int next(int n) {
            System.out.println(index + " " + count + " " + n);
            if(index == nums.length-2 && nums[index]-count < n){
                return -1;
            }
            if(nums[index]-count >= n){
                count += n;
                return nums[index+1];
            }else{
                if(index == nums.length - 2){
                    count = nums[index]+10;
                    return -1;
                }
                n -= (nums[index] - count);
                index += 2;
                while (n > nums[index] && index < nums.length-2){
                    n -= nums[index];
                    index += 2;
                }
                count = n;
                if(index == nums.length-2 && nums[index] < count){
                    return -1;
                }else{
                    return nums[index+1];
                }
            }
        }
    }
}
