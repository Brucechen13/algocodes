package algo.array;

/**
 * Created by chenc on 2017/7/2.
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        long sum = 1;
        boolean isZero = false;
        boolean isDoubleZero = false;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0){
                if(isZero){
                    isDoubleZero = true;
                }else{
                    isZero = true;
                    res[i] = 1;
                }
            }else {
                sum *= nums[i];
            }
        }
        for(int i = 0; i < nums.length; i ++){
            if(isDoubleZero){
                res[i] = 0;
            }else if(isZero){
                res[i] = res[i]==1?(int) (sum / nums[i]):0;
            }else {
                res[i] = (int) (sum / nums[i]);
            }
        }
        return res;
    }

    //method2
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
