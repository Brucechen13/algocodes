package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Codejam41 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int N = in.nextInt();
            int O = in.nextInt();
            long D = in.nextLong();
            int[] sweets = new int[N];
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            int M = in.nextInt();
            int L = in.nextInt();
            for(int i = 0; i < N; i ++){
                if(i < 2){
                    sweets[i] = i==0?(x1 + L) : (x2 + L);
                    continue;
                }
                long xl = (((long)A) * x2) + (((long)B)*x1) + C;
                int x = (int)(xl % M);
                sweets[i] = x + L;
                x1 = x2;
                x2 = x;
            }
            long res = Long.MIN_VALUE;
            int start = 0;
            int end = 0;
            long sum = 0;
            int curO = O;
            while (start < N){
                for(; end < N; end++){
                    if(sweets[end] % 2 == 1 || sweets[end] % 2 == -1){
                        if(curO <= 0)break;
                        curO --;
                    }
                    sum += sweets[end];
                    if(sum > D){
                        break;
                    }
                    res = Math.max(res, sum);
                }
                if(end > start) {
                    sum -= sweets[start];
                    if (sweets[start] % 2 == 1 || sweets[start] % 2 == -1) curO++;
                }else{
                    end++;
                }
                start++;
                if(sum > D) {
                    if(end >= N)end = N-1;
                    for (; end >= start; end--) {
                        sum -= sweets[end];
                        if (sweets[end] % 2 == 1 || sweets[end] % 2 == -1) {
                            curO++;
                        }
                        if (sum <= D) {
                            break;
                        }
                    }
                }
                if(sum <= D && end > start) {
                    res = Math.max(res, sum);
                }
            }

            System.out.println("Case #" + ii + ": " + (res==Integer.MIN_VALUE?"IMPOSSIBLE":res));
        }
    }
}
