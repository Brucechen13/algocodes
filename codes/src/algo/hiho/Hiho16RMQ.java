package algo.hiho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hiho16RMQ {
    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /** call thReader method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        /** get next word */
        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                        reader.readLine() );
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

    public static void main(String[] args) throws IOException {
        Reader scanner = new Reader();
        scanner.init(System.in);
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[][] dp = new int[n][20];
        for(int i = 0; i < n; i ++){
            weights[i] = scanner.nextInt();
            dp[i][0] = weights[i];
        }
        int k = (int)(Math.log(n)/Math.log(2));
        for(int j = 1; j <= k; j ++){
            for(int i = 0; i + (1<<j) -1 < n; i ++){
                dp[i][j] = Math.min(dp[i][j-1], dp[Math.min(n-1, i + (1<<(j-1)))][j-1]);
            }
        }
        int m = scanner.nextInt();
        for(int i = 0; i < m; i ++){
            int l = scanner.nextInt()-1;
            int r = scanner.nextInt()-1;
            int T = (int)(Math.log(r-l+1)/Math.log(2));
            int res = Math.min(dp[l][T], dp[r - (1<<T) + 1][T]);
            System.out.println(res);
        }

    }
}
