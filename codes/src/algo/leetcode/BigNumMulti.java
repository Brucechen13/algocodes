package algo.leetcode;

/**
 * 大数相乘
 */
public class BigNumMulti {

    /**
     * 大数相乘
     * 阿拉伯乘法
     * @param num1
     * @param num2
     * @return
     */
    public static String multiplication(String num1, String num2){
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        int len1 = s1.length;
        int len2 = s2.length;
        int[][] vals = new int[len2*2][len1];
        for(int i = 0; i < len1; i ++){
            for(int j = 0; j < len2*2; j +=2){
                int val = (s1[i]-'0')*(s2[j/2]-'0');
                vals[j][i] = val/10;
                vals[j+1][i] = val%10;
            }
        }
        //sum left
        int[] res1 = new int[len2];
        for(int i = 0; i < len2*2; i +=2){
            int sum = 0;
            int i1 = i, i2 = 0;
            while (i1 >= 0 && i2 < len1){
                sum += vals[i1][i2];
                if(i1%2 == 1){
                    i2 ++;
                }
                i1 --;
            }
            res1[i/2] = sum;
        }
        //sum botton
        int[] res2 = new int[len1];
        for(int i = 0; i < len1; i ++){
            int sum = 0;
            int i1 = len2*2-1, i2 = i;
            while (i1 >= 0 && i2 < len1){
                sum += vals[i1][i2];
                if(i1%2 == 1){
                    i2 ++;
                }
                i1 --;
            }
            res2[i] = sum;
        }
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        for(int i = res2.length-1; i >= 0; i --){
            int cur = res2[i]+pre;
            pre = cur/10;
            sb.append(cur%10);
        }
        for(int i = res1.length-1; i >= 0; i --){
            int cur = res1[i]+pre;
            pre = cur/10;
            sb.append(cur%10);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args){
        System.out.println(multiplication("469", "37"));
    }
}
