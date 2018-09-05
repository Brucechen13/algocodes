package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Codejam22 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int K = in.nextInt();
            long P = in.nextLong();
            P = Math.min(P, N);
            int[] res = new int[N];
            Arrays.fill(res, -1);
            for(int j = 0; j < K; j ++){
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
                res[a-1] = c;
            }
            int left = (int)P - 1;
            StringBuilder sb = new StringBuilder();
            for(int j = N-1; j >= 0; j --){
                if(res[j] != -1)sb.append(""+res[j]);
                else if(left > 0){
                    sb.append(left % 2 == 0 ? "0" : "1");
                    left /= 2;
                }else{
                    sb.append("0");
                }
            }

            System.out.println("Case #" + i + ": " + sb.reverse().toString());
        }
    }
}
