package algo.array;

/**
 * 287. Find the Duplicate Number
 * Created by chenc on 2017/5/25.
 */
public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i ++){
            int index = nums[i];
            nums[i] = -2;
            while(index != -2){
                if(nums[index] == -1){
                    return index;
                }
                int temp = nums[index];
                nums[index] = -1;
                index = temp;
            }
        }
        return -1;
    }

    public int findDuplicate2(int[] nums) {
        if (nums.length > 1)
        {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast)
            {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            fast = 0;
            while (fast != slow)
            {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }
}
