package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Codejam51 {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {

            int N = in.nextInt();
            int K = in.nextInt();
            int[] A = new int[N];
            for(int i = 0; i < N; i ++){
                A[i] = in.nextInt();
            }
            //if(ii > 5)continue;
            int res =consumeCups(A, N, K);
            System.out.println("Case #" + ii + ": " + res);
        }
    }

    public static int consumeCups(int[] A, int N, int K){
        Arrays.sort(A);

        int day = 0, res = 0;
        for(int i = 0; i < N;){
            while (i<N && A[i] <= day)i++;
            int num = 0;
            while (i<N && A[i] > day && num < K){
                i ++;
                num++;
                res++;
            }
            day++;
        }
        return res;
    }
}
