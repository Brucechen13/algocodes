package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Codejam42 {
    static class Posi{
        int x;
        int y;
        int type;

        public Posi(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static Posi update(Posi p1, Posi p2){
        if(p1.y >= p2.y){
            return p1;
        }
        if((p2.x-p1.x) >= (p2.y-p1.y)){
            return p1;
        }
        return p2;
    }

    static boolean cover(Posi p1, Posi p2){
        if((p2.x-p1.x) >= (p2.y-p1.y)){
            return false;
        }
        return true;
    }

    static int findLeftMax(Posi p1, Posi p2, Posi[] vals, int index){
        for(int i = index; i >= 0; i --){
            if(vals[i].type == 1)continue;

            if(cover(p1, vals[i])){
                p2.x = vals[i].x;
                p2.y = vals[i].y;
                return index-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int N = in.nextInt();
            int K = in.nextInt();
//            p1 p2 A1 B1 C1 M1
//            h1 h2 A2 B2 C2 M2
//            x1 x2 A3 B3 C3 M3
//            y1 y2 A4 B4 C4 M4
//            pi = (A1 × pi - 1 + B1 × pi - 2 + C1) modulo M1 + 1, for i = 3 to N.
//            hi = (A2 × hi - 1 + B2 × hi - 2 + C2) modulo M2 + 1, for i = 3 to N.
//            xi = (A3 × xi - 1 + B3 × xi - 2 + C3) modulo M3 + 1, for i = 3 to K.
//            yi = (A4 × yi - 1 + B4 × yi - 2 + C4) modulo M4 + 1, for i = 3 to K.
            int[][] augs = new int[4][6];
            for(int ai1 = 0; ai1 < 4; ai1 ++){
                for(int ai2 = 0; ai2 < 6; ai2 ++){
                    augs[ai1][ai2] = in.nextInt();
                }
            }
            Posi[] vals = new Posi[N+K];
            for(int i = 0; i < N; i ++){
                if(i < 2){
                    if(i == 0){
                        vals[i] = new Posi(augs[0][0], augs[0][1], 0);
                    }else{
                        vals[i] = new Posi(augs[1][0], augs[1][1], 0);
                    }
                    continue;
                }
                int pi = (int)(((long)augs[0][2] * augs[0][1] +
                        (long)augs[0][3]*augs[0][0] + augs[0][4]) % augs[0][5]);
                augs[0][0] = augs[0][1];
                augs[0][1] = pi;

                int hi = (int)(((long)augs[1][2] * augs[1][1] +
                        (long)augs[1][3]*augs[1][0] + augs[1][4]) % augs[1][5]);
                augs[1][0] = augs[1][1];
                augs[1][1] = pi;
                vals[i] = new Posi(pi, hi, 0);
            }


            for(int i = 0; i < K; i ++){
                if(i < 2){
                    if(i == 0){
                        vals[i+N] = new Posi(augs[2][0], augs[2][1], 1);
                    }else{
                        vals[i+N] = new Posi(augs[3][0], augs[3][1], 1);
                    }
                    continue;
                }
                int xi = (int)(((long)augs[2][2] * augs[2][1] +
                        (long)augs[2][3]*augs[2][0] + augs[2][4]) % augs[2][5]);
                augs[2][0] = augs[2][1];
                augs[2][1] = xi;

                int yi = (int)(((long)augs[3][2] * augs[3][1] +
                        (long)augs[3][3]*augs[3][0] + augs[3][4]) % augs[3][5]);
                augs[3][0] = augs[3][1];
                augs[3][1] = yi;
                vals[i+N] = new Posi(xi, yi, 1);
            }

            Arrays.sort(vals, new Comparator<Posi>() {
                @Override
                public int compare(Posi t, Posi t1) {
                    return t.x == t1.x ?(t.y - t1.y) : t.x - t1.x;
                }
            });

            int res = 0;
            Posi leftMax = null;
            Posi rightMax = null;
            int leftIndex = vals.length;
            for(int i = vals.length-1; i >= 0; i --){
                Posi cur = vals[i];
                if(cur.type == 0){
                    if(rightMax == null)rightMax = cur;
                    else rightMax = update(cur, rightMax);
                }else{
                    if((rightMax!=null && cover(cur, rightMax)) ||
                            (leftMax!=null && cover(cur, leftMax))){
                        res++;
                    }else if(leftIndex != -1){
                        if(leftMax == null){
                            leftMax = new Posi(0, 0, 0);
                        }
                        leftIndex = findLeftMax(cur, leftMax, vals, Math.min(leftIndex, i-1));

                        if(cover(cur, leftMax)){
                            res ++;
                        }
                    }
                }
            }

            System.out.println("Case #" + ii + ": " + res);
        }
    }
}
