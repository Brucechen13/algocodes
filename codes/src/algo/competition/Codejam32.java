package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam32 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();

            int[] edegs = new int[n];
            int[][] values = new int[n][n];
            for (int j = 0; j < n; j++) {
                int status = 0;
                for(int k = 0; k < n; k ++){
                    int val = in.nextInt();
                    if(val > 0){
                        status += (1 << k);
                    }
                    values[j][k] = val;
                }
                edegs[j] = status;
            }

            int res = dfs(new ArrayList<>(), n, edegs, 0, values);

            System.out.println("Case #" + i + ": " + res);
        }
    }

    public static int dfs(List<Integer> cached, int n, int[] status, int cur, int[][] values){
        if(cur >= n){
            if(cached.size() >= 3){
                int max = Integer.MIN_VALUE;
                int sum = 0;
                for (int val:
                        cached) {
                    sum += val;
                    max = Math.max(max, val);
                }
                if(sum > max*2){
                    return 1;
                }
            }
            return 0;
        }

        int size = 0;

        for(int i = cur+1; i < n; i ++){
            if(((status[cur] & (1 << i)) != 0)
                && ((status[i] & (1 << cur)) != 0)){
                cached.add(values[cur][i]);
                if(cached.size()==2 && cached.get(0) == 4 && cached.get(1) == 15){
                    int a = 0;
                }
                int preStatus = status[i];
                status[i] = 0;
                size += dfs(cached, n, status, cur+1, values);
                status[i] = preStatus;
                cached.remove((Integer)values[cur][i]);
            }
        }
        size += dfs(cached, n, status, cur+1, values);

        return size;
    }
}
