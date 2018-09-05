package algo.other;

import java.util.ArrayList;
import java.util.List;

public class SumDistancesTree {

    class Node{
        int father;
        List<Integer> childs = new ArrayList<>();
        int count;
        int sum;
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        Node[] nodes = new Node[N];
        for(int[] edge : edges){
            int f = edge[0];
            int s = edge[1];
            nodes[f].childs.add(s);
            nodes[s].father = f;
            nodes[f].sum = 0;
            nodes[s].count = 0;
        }
        int[] res = new int[N];
        for(int i = 0; i < N; i ++){
            res[i] = getDistance(i, nodes);
        }
        return res;
    }

    public int getDistance(int index, Node[] nodes){
        int sum = 0;
        if(nodes[index].sum != 0)return nodes[index].sum;
        for(int child : nodes[index].childs){
            sum += getDistance(child, nodes);
        }
        sum += getDistance(nodes[index].father, nodes);
        return sum;
    }


    public int[] sumOfDistancesInTree2(int N, int[][] edges) {
        if (N == 1) {
            return new int[N];
        }
        if (N == 2) {
            return new int[]{1, 1};
        }

        List<int[]>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // [0] = to  [1] = sum  [2] = num
            graph[edges[i][0]].add(new int[]{edges[i][1], 0, 0});
            graph[edges[i][1]].add(new int[]{edges[i][0], 0, 0});
        }

        int[] result = new int[N];
        boolean[] seen = new boolean[N];
        for (int i = 0; i < N; i++) {
            result[i] = dfs(graph, i, seen)[0];
        }
        return result;
    }

    private int[] dfs(List<int[]>[] graph, int i, boolean[] seen) {
        seen[i] = true;
        int sum = 0;
        int num = 1;
        for (int[] adj : graph[i]) {
            if (!seen[adj[0]]) {
                if (adj[1] == 0 && adj[2] == 0) {
                    int[] res = dfs(graph, adj[0], seen);
                    adj[1] = res[0];
                    adj[2] = res[1];
                }
                sum += (adj[1] + adj[2]);
                num += adj[2];
            }
        }
        seen[i] = false;
        return new int[]{sum, num};
    }


    int sum;
    int[] head;
    int[] e, next;
    int cnt;

    private void add(int u, int v) {
        next[cnt] = head[u];
        e[cnt] = v;
        head[u] = cnt++;
    }

    public int[] sumOfDistancesInTree3(int n, int[][] ed) {
        int[] sz = new int[n];
        int[] ans = new int[n];
        head = new int[n];
        next = new int[n << 1];
        e = new int[n << 1];
        cnt = 0;
        sum = 0;
        for (int i = 0; i < n; ++i) {
            head[i] = -1;
        }
        for (int i = 0; i + 1 < n; ++i) {
            add(ed[i][0], ed[i][1]);
            add(ed[i][1], ed[i][0]);
        }
        dfs(0, -1, sz, 0);
        ans[0] = sum;
        dfs(0, -1, ans, sz, n);
        return ans;
    }

    private void dfs(int u, int p, int[] ans, int[] sz, int n) {
        if (u == p) {
            return;
        }
        if (u != 0) {
            ans[u] = ans[p] - sz[u] + n - sz[u];
        }
        for (int i = head[u]; i != -1; i = next[i]) {
            if (e[i] != p) {
                dfs(e[i], u, ans, sz, n);
            }
        }
    }

    private void dfs(int u, int p, int[] sz, int dis) {
        if (u == p) {
            return;
        }
        sum += dis;
        sz[u] = 1;
        for (int i = head[u]; i != -1; i = next[i]) {
            if (e[i] != p) {
                dfs(e[i], u, sz, dis + 1);
                sz[u] += sz[e[i]];
            }
        }
    }
}
