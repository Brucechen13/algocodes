package algo.competition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Codejam2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        try {
            in = new Scanner(new BufferedReader(new FileReader("C:\\Users\\chenc\\Downloads\\B-large.in")));
        }catch (Exception e){

        }
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();int k = in.nextInt();
            int[] vals = new int[n];
            for(int j = 0; j < n; j ++){
                vals[j] = in.nextInt();
            }
            Arrays.sort(vals);

            System.out.println("Case #" + i + ": " + expectValue(vals, k));
        }
    }

    public static double expectValue(int[] vals, int k){
        int n = vals.length;
        double sum = 0;
        for(int i = 0; i < n; i ++){
            sum += 1.0*vals[i]/n;
        }
        for(int j = 1; j <= k; j ++){
            double preVal = 0;
            int i;
            for(i = n-1; i >= 0; i --){
                if(vals[i] < sum){
                    break;
                }
                preVal += 1.0*vals[i]/n;
            }
            sum = preVal + 1.0*(i+1)/n*sum;
        }
        return sum;
    }
}
