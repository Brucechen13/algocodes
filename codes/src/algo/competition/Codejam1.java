package algo.competition;

import java.io.*;
import java.util.Scanner;

public class Codejam1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            String val = in.next();
            long sum = 0;
            for(int j = 0; j < val.length()-1; j ++){
                long cur = Long.parseLong(val.substring(j, j+1));
                if((cur&1) == 0){
                    continue;
                }else{
                    long left  = Long.parseLong(val.substring(j));
                    long min = (long)Math.pow(10,(val.length()-j-1))*(cur-1);
                    StringBuilder sb = new StringBuilder();
                    for(int k = j+1; k < val.length(); k ++){
                        sb.append("8");
                    }
                    min += Long.parseLong(sb.toString());
                    if(cur == 9){
                        sum = left-min;
                    }else{
                        long max = (long)Math.pow(10,(val.length()-j-1))*(cur+1);
                        sum = Math.min((max - left), (left-min));
                    }
                    break;
                }
            }
            if(sum == 0 && (Integer.parseInt(val.substring(val.length()-1))&1) == 1){
                sum = 1;
            }
            System.out.println("Case #" + i + ": " + sum);
        }
    }
}
