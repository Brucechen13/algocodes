package algo.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumNumberRefuelingStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(startFuel >= target)return 0;
        if(stations.length < 1 || startFuel < stations[0][0])return -1;
        int len = stations.length;
        long[] dp = new long[len+1];
        dp[0] = startFuel;
        for(int i = 0; i < len; i ++){
            for(int j = i; j >= 0 && dp[j] >= stations[i][0]; j --){
                dp[j+1] = Math.max(dp[j+1], dp[j] + stations[i][1]);
            }
        }

        for(int i = 0; i <= len; i ++){
            if(dp[i] >= target)return i;
        }
        return -1;
    }

    public int minRefuelStops2(int target, int cur, int[][] s) {
        Queue<Integer> pq = new PriorityQueue<>();
        int i = 0, res;
        for (res = 0; cur < target; res++) {
            while (i < s.length && s[i][0] <= cur)
                pq.offer(-s[i++][1]);
            if (pq.isEmpty()) return -1;
            cur += -pq.poll();
        }
        return res;
    }

}
