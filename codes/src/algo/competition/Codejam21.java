package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Codejam21 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        long[] nums = new long[19];
        nums[0] = 1;
        for(int i = 1; i < nums.length; i ++){
            nums[i] = nums[i-1] + (nums[i-1] + 1)*8 + (long)Math.pow(10, i);
        }
        for (int i = 1; i <= t; ++i) {
            String val1 = in.next();
            String val2 = in.next();
            long res = 0l;
            if(val1.length() == val2.length()){
                res = searchSameLen(val1, val2, nums);
            }else{
                res = searchNotSame(val1, val2, nums);
            }
            //res = calDiff(val1, val2) - res+1;
            System.out.println("Case #" + i + ": " + res);
        }
    }

    public static long calDiff(String val1, String val2){
        return Long.parseLong(val2) - Long.parseLong(val1);
    }

    public static long searchSameLen(String val1, String val2, long[] nums){
        if(val1.length() == 1){
            return 0;
        }
        if(val1.charAt(0) == val2.charAt(0)){
            if(val1.length()==2){
                int sub0 = val1.charAt(0)-'0' + val1.charAt(1)-'0';
                int sub1 = val2.charAt(0) - '0' + val2.charAt(1) - '0';
                return (sub0<9 && sub1>9)?1:0;
            }
            return searchSameLen(val1.substring(1), val2.substring(1), nums);
        }else{
            int diff = val2.charAt(0) - val1.charAt(0);
            long moveStep = diff * (nums[val1.length()-2] + 1);
            return moveStep + searchSameLen(val1.substring(1), val2.substring(1), nums);
        }
    }

    public static long searchNotSame(String val1, String val2, long[] nums){
        if(val1.length() == val2.length()){
            return searchSameLen(val1, val2, nums);
        }
        long moveStep = nums[val2.length()-2] - searchNotSame("1", val1, nums);
        StringBuilder sb = new StringBuilder("1");
        for(int i = 0; i < val2.length()-1; i ++){
            sb.append("0");
        }
        return moveStep + searchSameLen(sb.toString(), val2, nums);
    }
}
