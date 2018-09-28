package algo.hiho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hiho12TreeWeights {

    static class Node{
        List<Integer> nears = new ArrayList<>();
        int weight;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Node[] nodes = new Node[n];
        int[][] dp = new int[n][m+1];
        for(int i = 0; i < n; i ++){
            nodes[i] = new Node();
            nodes[i].weight = scanner.nextInt();
            dp[i][1] = nodes[i].weight;
        }
        for(int i = 0; i <  n-1; i ++){
            int e1 = scanner.nextInt();
            int e2 = scanner.nextInt();
            nodes[e1-1].nears.add(e2-1);
            nodes[e2-1].nears.add(e1-1);
        }
        calNodes(nodes, 0, -1, m, dp);
        System.out.println(dp[0][m]);
    }

    public static void calNodes(Node[] nodes, int index, int father, int m, int[][] dp){
        if(dp[index][m] != 0)return ;
        for (int idx:
             nodes[index].nears) {
            if(idx == father)continue;
            calNodes(nodes, idx, index, m-1, dp);
            for(int i = 2; i <= m; i ++){
                for(int k = 1; k < i; k ++){
                    dp[index][i] = Math.max(dp[index][i],
                            dp[index][i - k] + dp[idx][k]);
                }
            }
        }
    }
}
