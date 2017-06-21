package algo.array;

/**
 * Created by chenc on 2017/6/21.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int pre = 1;
        for(int i = digits.length-1; i >=0; i --){
            int temp = digits[i]+pre;
            digits[i] = temp%10;
            pre = temp/10;
            if(pre == 0){
                break;
            }
        }
        if(pre != 0){
            int[] temp = new int[digits.length+1];
            for(int i = 1; i < temp.length; i ++){
                temp[i] = digits[i-1];
            }
            temp[0] = pre;
            digits = temp;
        }
        return digits;
    }
}
