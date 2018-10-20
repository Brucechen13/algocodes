package algo.leetcode;

import java.util.*;

public class ReachableNodesGraph {
    class Node{
        public List<Integer> edges = new ArrayList<>();
        public List<Integer> weights = new ArrayList<>();
    }
    public int reachableNodes(int[][] edges, int M, int N) {
        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i ++)nodes[i] = new Node();
        for(int i = 0; i < edges.length; i ++){
            nodes[edges[i][0]].edges.add(edges[i][1]);
            nodes[edges[i][0]].weights.add(edges[i][2]);

            nodes[edges[i][1]].edges.add(edges[i][0]);
            nodes[edges[i][1]].weights.add(edges[i][2]);
        }
        int[] dis = new int[N];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1];
            }
        });
        queue.add(new int[]{0, 0});
        dis[0] = 0;
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(visited.contains(cur[0]))continue;
            visited.add(cur[0]);
            for (int i = 0; i < nodes[cur[0]].edges.size(); i++) {
                int neiNode = nodes[cur[0]].edges.get(i);
                dis[neiNode] = Math.min(dis[neiNode],
                        dis[cur[0]] + nodes[cur[0]].weights.get(i) + 1);
                if (!visited.contains(neiNode)) {
                    queue.add(new int[]{neiNode, dis[neiNode]});
                }
            }
        }
        int res = 0;
        for(int i = 0; i < N; i ++){
            if(dis[i] <= M){
                res ++;//添加点
                for(int iw = 0; iw < nodes[i].weights.size(); iw ++){
                    res += Math.min(M-dis[i], nodes[i].weights.get(iw));
                    int neiNode = nodes[i].edges.get(iw);
                    int neiEdg = 0;
                    for (int ie = 0; ie < nodes[neiNode].edges.size(); ie++){
                        if(nodes[neiNode].edges.get(ie) == i){
                            neiEdg = ie;
                            break;
                        }
                    }
                    nodes[neiNode].weights.set(neiEdg,
                            nodes[neiNode].weights.get(neiEdg) -
                                    Math.min(M-dis[i], nodes[i].weights.get(iw)));
                }
            }
        }
        return res;
    }

    public int reachableNodes2(int[][] edges, int M, int N) {
        boolean change = true;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = M;
        int res = 1, E = edges.length;
        int[][] visited = new int[E][2];
        while (change) {
            change = false;
            for (int i = 0; i < E; i++) {
                int[] e = edges[i];
                int v = e[0], w = e[1], l = e[2];
                if (dp[v] - l - 1 > dp[w]) {
                    change = true;
                    if (dp[w] < 0) res++;
                    dp[w] = dp[v] - l - 1;
                }
                else if (dp[w] - l - 1 > dp[v]) {
                    change = true;
                    if (dp[v] < 0) res++;
                    dp[v] = dp[w] - l - 1;
                }
                if (dp[v] > visited[i][0]) {
                    int d = Math.min(dp[v] - visited[i][0], l - visited[i][0] - visited[i][1]);
                    visited[i][0] += d;
                    res += d;
                }
                if (dp[w] > visited[i][1]) {
                    int d = Math.min(dp[w] - visited[i][1], l - visited[i][0] - visited[i][1]);
                    visited[i][1] += d;
                    res += d;
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[][] edges = {{0,2,9},{0,1,6},{3,4,7},{2,3,8},{1,2,0},{2,4,0},
                {0,4,9},{0,3,0},{1,4,2},{1,3,0}};
        System.out.println(new ReachableNodesGraph().reachableNodes(edges, 3, 5));
    }
}
