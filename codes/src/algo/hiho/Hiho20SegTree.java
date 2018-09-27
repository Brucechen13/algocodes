package algo.hiho;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hiho20SegTree {
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

    private static int[] ans = new int[500010];
    private static int[] lazy = new int[500010];
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

    private static void pushUp(int rt){
        ans[rt] = ans[rt<<1] + ans[rt<<1|1];
    }

    private static void update(int l, int r, int rt, int L, int R, int C){
        if(l > R || r < L)return;
        if(l >= L && r <= R){
            ans[rt] = C * (r- l + 1);
            lazy[rt] = C;
            return;
        }
        int mid = (l+r)>>1;
        pushDown(rt, l, r);
        if(mid >= L)update(l, mid, rt<<1, L, R, C);
        if(mid < R)update(mid+1, r, rt<<1|1, L, R, C);
        pushUp(rt);
    }

    private static void pushDown(int rt, int l, int r){
        if (lazy[rt] != 0)
        {
            int mid = (l+r)>>1;
            lazy[rt<<1] = lazy[rt];
            lazy[rt<<1|1] = lazy[rt];
            ans[rt<<1] = lazy[rt] * (mid-l+1);
            ans[rt<<1|1] = lazy[rt] * (r - mid);
            lazy[rt]=0;
        }
    }

    private static int query(int l, int r, int L, int R, int rt){
        if(l > R || r < L)return 0;
        if(l == r){
            return ans[rt];
        }
        pushDown(rt, l, r);
        //pushUp(rt);
        int mid = (l+r)>>1;
        int ANS = 0;
        if(mid >= L)ANS += query(l, mid, L, R, rt<<1);
        if(mid < R) ANS += query(mid+1, r, L, R, rt<<1|1);
        return ANS;
    }

    public static void main(String[] args) throws IOException{
//        Reader scanner = new Reader();
//        scanner.init(System.in);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i ++)a[i] = scanner.nextInt();
        build(0, n-1, 1);
        int m = scanner.nextInt();
        for(int i = 0; i < m; i ++){
            int op = scanner.nextInt();
            if(op == 0){
                int l = scanner.nextInt()-1;
                int r = scanner.nextInt()-1;
                System.out.println(query(0, n-1, l, r, 1));
            }else{
                int l = scanner.nextInt()-1;
                int r = scanner.nextInt()-1;
                int w = scanner.nextInt();
                update(0, n-1, 1, l, r, w);
            }
        }
    }
}
