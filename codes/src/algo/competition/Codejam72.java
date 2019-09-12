package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam72 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int N = in.nextInt();
            int G = in.nextInt();
            int M = in.nextInt();
            int[] time = new int[N];
            List<Integer>[] visit = new List[N];
            for(int i = 0; i < N; i ++){
                visit[i] = new ArrayList<Integer>();
            }
            int[] posi = new int[G];
            boolean[] clock = new boolean[G];
            for(int i = 0; i < G; i ++){
                posi[i] = in.nextInt() - 1;
                visit[posi[i]].add(i);
                clock[i] = in.next().equals("C");
            }
            int curTime = 0;
            M %= N;
            while (++curTime <= M){
                for(int i = 0; i < G; i ++){
                    posi[i] = (posi[i] + (clock[i]?1:-1) + N) % N;
                    if(time[posi[i]] < curTime){
                        visit[posi[i]].clear();
                        time[posi[i]] = curTime;
                    }
                    if(time[posi[i]] == curTime){
                        visit[posi[i]].add(i);
                    }
                }
            }
            int[] res = new int[G];
            for(List<Integer> vis : visit){
                for(int i : vis){
                    res[i] ++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < G; i ++){
                sb.append(res[i] + (i == G-1 ? "":" "));
            }
            System.out.println("Case #" + ii + ": " + sb.toString());
        }
    }
}
