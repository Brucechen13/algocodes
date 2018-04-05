package algo.depthfirst;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        boolean[] visited = new boolean[10000];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            res.add(i);
        }
        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) continue;
            List<Integer> cached = new ArrayList<>();
            searchPath(graph, i, visited, cached, res);
        }
        return res;
    }

    public void searchPath(int[][] graph, int index, boolean[] visited, List<Integer> cached, List<Integer> res) {
        cached.add(index);
        for (int i = 0; i < graph[index].length; i++) {
            if (visited[graph[index][i]]) {
                if (!res.contains(graph[index][i])) {
                    for (int j = 0; j < cached.size(); j++) {
                        if (res.contains(cached.get(j))) {
                            res.remove(cached.get(j));
                        }
                    }
                }
                continue;
            }
            if (cached.contains(graph[index][i])) {
                for (int j = 0; j < cached.size(); j++) {
                    if (res.contains(cached.get(j))) {
                        res.remove((Integer) cached.get(j));
                    }
                }
                continue;
            }
            searchPath(graph, index, visited, cached, res);
        }
        visited[index] = true;
        cached.remove((Integer) index);
    }

    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> res = new ArrayList<>();

        int[] colored = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (dfs(graph, i, colored)) res.add(i);
        }
        return res;
    }

    public boolean dfs(int[][] graph, int index, int[] colored) {
        if (colored[index] != 0) return colored[index] == 1;

        colored[index] = 2;
        for (int i = 0; i < graph[index].length; i++) {
            if (!dfs(graph, graph[index][i], colored)) return false;
        }
        colored[index] = 1;
        return true;
    }
}
