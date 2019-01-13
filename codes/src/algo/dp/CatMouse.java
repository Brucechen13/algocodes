package algo.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CatMouse {
    public int catMouseGame(int[][] graph) {
        int[][] dp = new int[graph.length][graph.length];
        for(int i = 0; i < graph.length; i ++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < graph.length; ++i) {
            dp[0][i] = 1;   // mouse reached home, m win
            dp[i][i] = 2;   // cat met mouse, cat win
        }

        int res = dfs(graph, 1, 2, dp);
        return res;
    }

    public int dfs(int[][] graph, int m, int c, int[][] dp){
        if(dp[m][c] != -1)return dp[m][c];

        dp[m][c] = 0;
        int mouseDef = 2;

        for (int mn:
             graph[m]) {
            if(mn == c)continue;

            int catDef = 1;
            for (int cn:
                 graph[c]) {
                if(cn == 0)continue;

                int res = dfs(graph, mn, cn, dp);

                if(res == 2){
                    catDef = 2;
                    break;
                }
                if(res == 0){
                    catDef = 0;
                }
            }

            if(catDef == 1){
                // cat is lose
                mouseDef = 1;
                break;
            }
            if(catDef == 0){
                mouseDef = 0;
            }
        }

        dp[m][c] = mouseDef;
        return mouseDef;
    }

    public static void main(String[] args){
        // int[][] graph = {
        //        {2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};

        // int[][] graph = {{2,3},{3,4},{0,4},{0,1},{1,2}};// {{1,3},{0},{3},{0,2}};
        
        int[][] graph = {{6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}};
        System.out.println(new CatMouse().catMouseGame(graph));
    }
}
