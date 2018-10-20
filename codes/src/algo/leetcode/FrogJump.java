package algo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if(stones==null||stones.length<2||stones[1]-stones[0]!=1)return false;
        int[][] dp = new int[stones.length][stones.length+1];
        dp[1][1] = 1;
        for(int i = 1; i < stones.length-1; i ++){
            boolean flag = false;
            for (int j = 1; j < stones.length; j ++){
                if(dp[i][j] != 0){
                    int k = j;
                    for(int step=k-1; step<k+2; step++){
                        for(int nextI = i+1; nextI < stones.length; nextI++){
                            if(stones[nextI]-stones[i] > step)
                                break;
                            if((stones[nextI]-stones[i]==step)){
                                dp[nextI][step]=1;
                                flag = true;
                                if(nextI == stones.length-1)return true;
                            }
                        }
                    }
                }
            }
            if(!flag)return false;
        }
        return true;
    }
    //[0,1,3,6,10,13,14]


    public boolean canCross2(int[] stones) {
        Set<Integer> set= new HashSet<>();
        for(int stone : stones) set.add(stone);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        return dfs(stones[stones.length-1],0,1, set, map);
    }
    private boolean dfs(int des, int cur, int step, Set<Integer>set , Map<Integer, Set<Integer>> map){
        if(cur==des)return true;
        if(!set.contains(cur) || step==0 ||  (map.get(cur)!=null && map.get(cur).contains(step)) ) return false;
        if(dfs(des, cur+step, step-1,set,map) || dfs(des, cur+step, step,set,map) || dfs(des, cur+step, step+1,set,map)) return true;
        if (map.get(cur)==null){
            map.put(cur, new HashSet<>());
        }
        map.get(cur).add(step);
        return false;
    }
}
