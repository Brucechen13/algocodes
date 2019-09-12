package algo.array;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, k, n, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int index, int left, int N, List<Integer> cur, List<List<Integer>> res){
        if(left <= 0){
            List<Integer> tmp = new ArrayList<>(cur);
            res.add(tmp);
            return;
        }
        for(int i = index; i <= N; i ++){
            cur.add(i);
            dfs(i+1, left-1, N, cur, res);
            cur.remove(cur.size()-1);
        }
    }



    public String alphabetBoardPath(String target) {
        char start = 'a';
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < target.length(); i ++){
            char c = target.charAt(i);
            int sr = (start - 'a') / 5;
            int sc = (start - 'a') % 5;
            int cr = (c - 'a') / 5;
            int cc = (c - 'a') % 5;
            while (true){
                if(sr == cr && sc == cc){
                    res.append("!");
                    break;
                }
                if(sc != cc && sr != 5){
                    res.append(sc > cc ? "L" : "R");
                    sc += sc > cc ? -1 : 1;
                }else{
                    res.append(sr > cr ? "U" : "D");
                    sr += sr > cr ? -1 : 1;
                }
            }
            start = c;
        }
        return res.toString();
    }


    public int stoneGameII(int[] piles) {
        int[] sum = new int[piles.length];
        int s = 0;
        for(int i = piles.length-1; i >= 0; i --){
            s += piles[i];
            sum[i] = s;
        }
        return dfs(sum, 0, 1, new int[piles.length][piles.length]);
    }


    public int dfs(int[] sum, int index, int M, int[][] dp){
        if(index == sum.length)return 0;
        if(dp[index][Math.min(M, sum.length)] != 0)return dp[index][Math.min(M, sum.length)];
        int max = 0;
        for(int i = 1; i <= 2*M; i ++){
            if(index + i > sum.length)break;
            max = Math.max(max, sum[index] - dfs(sum, index+i, Math.max(i, M), dp));
        }
        dp[index][Math.min(M, sum.length)] = max;
        return max;
    }

    public static void main(String[] args){
        System.out.println(new Combinations().alphabetBoardPath("leet"));
    }
}
