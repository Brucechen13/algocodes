package algo.hiho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hiho22SegTree {

    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /**
         * call thReader method to initialize reader for InputStream
         */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input));
            tokenizer = new StringTokenizer("");
        }

        /**
         * get next word
         */
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                        reader.readLine());
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
    private static int[] ans = new int[4000100];
    private static int[] lazy_cover = new int[4000100];
    private static int[] lazy_add = new int[4000100];
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

    private static void update(int l, int r, int rt, int L, int R, int C, boolean cover){
        if (L<=l&&r<=R){
            if(cover) {
                ans[rt] = C * (r - l + 1);
                lazy_cover[rt] = C;
                lazy_add[rt] = 0;
            }else{
                ans[rt] += C * (r - l + 1);
                lazy_add[rt] += C;
            }
            return;
        }
        int mid = (l+r)>>1;
        pushDown(rt, l, r);
        if (L<=mid) update(l, mid, rt<<1, L, R, C, cover);
        if (R>mid) update(mid+1, r, rt<<1|1, L, R, C, cover);
        pushUp(rt);
    }

    private static void pushDown(int rt, int l, int r){
        int mid = (l+r)>>1;
        if(lazy_cover[rt] != 0){
            ans[rt<<1] = lazy_cover[rt]*(mid-l+1);
            ans[rt<<1|1] = lazy_cover[rt]*(r-mid);
            lazy_cover[rt<<1] = lazy_cover[rt];
            lazy_cover[rt<<1|1] = lazy_cover[rt];
            lazy_add[rt<<1] = 0;
            lazy_add[rt<<1|1] = 0;
            lazy_cover[rt] = 0;
        }
        if(lazy_add[rt] != 0){
            ans[rt<<1] += lazy_add[rt]*(mid-l+1);
            ans[rt<<1|1] += lazy_add[rt]*(r-mid);
            lazy_add[rt<<1] += lazy_add[rt];
            lazy_add[rt<<1|1] += lazy_add[rt];
            lazy_add[rt] = 0;
        }
    }

    private static int query(int l, int r, int rt, int L, int R){
        if(l == r){
            return ans[rt];
        }
        pushDown(rt, l, r);
        int mid = (l+r)>>1;
        int ANS = 0;
        if(mid >= L)ANS += query(l, mid, rt<<1, L, R);
        if(mid < R)ANS += query(mid+1, r, rt<<1|1, L, R);
        return ANS;
    }

    public static void main(String[] args) throws IOException{
        Reader scanner = new Reader();
        scanner.init(System.in);
        int n = scanner.nextInt()+1;
        int m = scanner.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i ++)a[i] = scanner.nextInt();
        build(0, n-1, 1);
        for(int i = 0; i < m; i ++){
            int op = scanner.nextInt();
            int L = scanner.nextInt();
            int R = scanner.nextInt();
            int C = scanner.nextInt();
            update(0, n-1, 1, L, R, C, op==1);
            System.out.println(ans[1]);
            //System.out.println(query(0, n-1, 1,0, n-1));
        }
    }
}
