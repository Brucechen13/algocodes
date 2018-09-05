package algo.array;

/**
 * Created by chenc on 2017/6/18.
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int major = -1, count = 0;
        for(int i = 0; i < nums.length; i ++){
            if(count == 0){
                major = nums[i];
                count++;
            }else if(major == nums[i]){
                count++;
            }else{
                count--;
            }
        }
        return major;
    }
}
