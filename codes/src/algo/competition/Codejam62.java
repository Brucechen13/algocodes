package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam62 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int N = in.nextInt();
            int Q = in.nextInt();
            int[][] xabcm = new int[3][6];
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 6; j ++){
                    xabcm[i][j] = in.nextInt();
                }
            }
            int[] x = new int[N];
            int[] y = new int[N];
            int[] z = new int[N];
            int[] L = new int[N];
            int[] R = new int[N];
            x[0] = xabcm[0][0];x[1] = xabcm[0][1];
            y[0] = xabcm[1][0];y[1] = xabcm[1][1];
            z[0] = xabcm[2][0];z[1] = xabcm[2][1];
            for(int i = 2; i < N; i ++){
                long val1 = (long)xabcm[0][2]*x[i-1] + (long)xabcm[0][3]*x[i-2] + xabcm[0][4];
                x[i] = (int)(val1 % xabcm[0][5]);
                val1 = (long)xabcm[1][2]*y[i-1] + (long)xabcm[1][3]*y[i-2] + xabcm[1][4];
                y[i] = (int)(val1 % xabcm[1][5]);
            }
            Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[0] - o1[0];
                }
            });
            for(int i = 0; i < N; i ++){
                L[i] = Math.min(x[i], y[i]) + 1;
                R[i] = Math.max(x[i], y[i]) + 1;
                queue.add(new int[]{R[i], i});
            }
            int k = z[0] + 1;
            while (k-- > 0){
                int[] val = queue.poll();

                if(val[0] != L[val[1]]){
                    queue.add(new int[]{val[0]-1, val[1]});
                }
            }
            int res = queue.size() > 0 ? queue.poll()[0] : 0;
//            X1 X2 A1 B1 C1 M1
//            Y1 Y2 A2 B2 C2 M2
//            Z1 Z2 A3 B3 C3 M3
//            Xi = (A1 × Xi - 1 + B1 × Xi - 2 + C1) modulo M1, for i = 3 to N.
//            Yi = (A2 × Yi - 1 + B2 × Yi - 2 + C2) modulo M2, for i = 3 to N.
//            Zi = (A3 × Zi - 1 + B3 × Zi - 2 + C3) modulo M3, for i = 3 to Q.
            System.out.println("Case #" + ii + ": " + String.format("%d",
                    res));
        }
    }
}
