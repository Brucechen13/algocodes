package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Codejam71 {

    public static int findOne(int val){
        int res = 0;
        while (val != 0){
            res += (val & 1);
            val >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int n = in.nextInt();
            int s = in.nextInt();
            int[] vals = new int[n];
            int sum = 0;
            for(int i = 0; i < n; i ++){
                vals[i] = in.nextInt();
                sum ^= vals[i];
            }
            StringBuilder sb = new StringBuilder();
            for(int i  = 0; i < s; i ++){
                int idx = in.nextInt();
                int val = in.nextInt();
                sum ^= vals[idx];
                vals[idx] = val;
                sum ^= vals[idx];
                int max = 0;
                for(int j = 0; j < n; j ++){
                    int cur = 0;
                    for(int jj = j; jj < n; jj ++){
                        cur ^= vals[jj];
                        if(max > (jj - j + 1))continue;
                        if(findOne(cur) % 2 == 0){
                            max = Math.max(max, jj - j + 1);
                        }
                    }
                }
                sb.append(max);
                sb.append(i == s-1 ? "":" ");
            }

            System.out.println("Case #" + ii + ": " + sb.toString());
        }
    }
}
