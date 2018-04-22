package algo.competition;

import java.awt.desktop.SystemSleepEvent;
import java.util.Arrays;

public class Solution {
    int[][] f = new int[30][20];
    public int dfs(int pos, int right, boolean bound, int[] limits){
        if(pos == 0){
            if(right != 0){
                return 1;
            }return 0;
        }
        if(f[pos][right] != -1 && !bound)return f[pos][right];
        int cur_lim = bound?limits[pos-1]:9;
        int ret = 0;
        for(int i = 0; i <= cur_lim; i ++){
            if(i == 9)continue;
            ret += dfs(pos-1, (right+i)%9, bound&&(i==cur_lim), limits);
        }
        if(!bound)f[pos][right] = ret;
        return ret;
    }

    public int solve(int N){
        if(N <= 0)return 0;
        String res = String.valueOf(N);
        res = new StringBuilder(res).reverse().toString();
        int[] limits = new int[res.length()];
        for(int i = 0; i < res.length(); i ++)limits[i] = res.charAt(i)-'0';
        for (int[] ff:
             f) {
            Arrays.fill(ff, -1);
        }
        int ans = dfs(limits.length, 0, true, limits);
        return ans;
    }

    public static void main(String[] args){
        System.out.print(new Solution().solve(21));
    }
}
