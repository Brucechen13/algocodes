package algo.array;

public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;

    }
    public int maximumSwap2(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] maxPosi = new int[digits.length];
        int curMax = digits.length - 1;
        for(int i = digits.length-1; i >= 0; i --){
            if((digits[i] - '0') > (digits[curMax] - '0')){
                curMax = i;
            }
            maxPosi[i] = curMax;
        }
        for(int i = 0; i < digits.length; i ++){
            if((digits[i] - '0') != (digits[maxPosi[i]] - '0')){
                char tmp = digits[i];
                digits[i] = digits[maxPosi[i]];
                digits[maxPosi[i]] = tmp;
                return Integer.valueOf(new String(digits));
            }
        }
        return num;

    }
}
