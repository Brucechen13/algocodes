package algo.depthfirst;

public class GraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] status = new int[graph.length];
        for(int i = 0 ; i < graph.length; i ++){
            if(status[i] != 0)continue;
            if(!dfs(graph, i, status, 1))return false;
        }
        return true;
    }

    public boolean dfs(int[][] graph, int index, int[] status, int curSta){
        status[index] = curSta;
        int nextSta = (curSta==1)?2:1;
        for(int i = 0; i < graph[index].length; i ++){
            if(status[graph[index][i]] == curSta)return false;
            if(status[graph[index][i]] == nextSta)continue;
            if(!dfs(graph, graph[index][i], status, nextSta))return false;
        }
        return true;
    }
}
