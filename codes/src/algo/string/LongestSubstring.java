package algo.string;

import java.util.*;

/**
 * Created by chenc on 2017/9/10.
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int[] exists = new int[26];
        int maxSub = 0;
        int curSub = 0;
        List<Character> cached = new ArrayList<Character>();
        char[] arrs = s.toCharArray();
        for(int i = 0; i < arrs.length; i ++){
            int index = (int)arrs[i];
            if(exists[index] != 0){
                maxSub = Math.max(maxSub, curSub);
                i = exists[index];
                exists = new int[26];
            }else{
                exists[index] = i;
                curSub ++;
            }
        }
        return maxSub;
    }

    public double findSmallestCost(int[][] costs, int k){
        int size = costs.length;
        double[] dp = new double[size];
        for(int i = 0; i < size; i ++){
            dp[i] = costs[0][i]/2.0;
        }
        Queue<Double> queue = new PriorityQueue<Double>();
        queue.add(dp[1]);
        while (k-- > 0){
            for(int i = 2; i < size; i ++){
                for(int j = 2; j < size; j ++){
                    dp[i] = Math.min(dp[i], dp[j] + costs[i][j]/2.0);
                }
                dp[1] = Math.min(dp[1], dp[i] + costs[i][1]);
                queue.add(dp[1]);
            }
        }
        return queue.poll();
    }
}
