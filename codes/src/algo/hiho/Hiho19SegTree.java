package algo.hiho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hiho19SegTree {

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

    private static int[] ans = new int[4*1000005];
    private static int[] a;

    private static void build(int l, int r, int rt){
        if(l == r){
            ans[rt] = a[l];
            return;
        }
        int mid = (l+r)>>1;
        build(l, mid, rt<<1);
        build(mid+1, r, rt<<1|1);
        pushUp(rt);
    }

    static void pushUp(int rt){
        ans[rt] = Math.min(ans[rt<<1], ans[rt<<1|1]);
    }

    static void add(int l, int r, int rt, int index, int C){
        if(l == r){
            ans[rt] = C;
            return;
        }
        int mid=(l+r)>>1;
        if (index<=mid)
            add(l,mid,rt<<1, index,C);
        else
            add(mid+1,r,rt<<1|1, index, C);
        pushUp(rt);
    }

    static int query(int l, int r, int rt, int L, int R){
        if (L<=l&&r<=R)
            return ans[rt];
        int mid=(l+r)>>1;
        int ANS=Integer.MAX_VALUE;
        if (L<=mid) ANS = Math.min(ANS, query(l,mid,rt<<1, L,R));
        if (R>mid) ANS = Math.min(ANS, query(mid+1,r,rt<<1|1, L,R));
        return ANS;
    }

    public static void main(String[] args) throws IOException{
        Reader scanner = new Reader();
        scanner.init(System.in);
        int n = scanner.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i ++)a[i] = scanner.nextInt();
        build(0, n-1, 1);
        int m = scanner.nextInt();
        for(int i = 0; i < m; i ++){
            int op = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(op == 0){
                System.out.println(query(0, n-1, 1, x-1, y-1));
            }else{
                add(0, n-1, 1, x-1, y);
            }
        }
    }

}
