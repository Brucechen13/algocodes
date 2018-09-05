package algo.other;


import java.util.ArrayList;
import java.util.List;

public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;
        int[][] dp = new int[len][10000];
        int[][] dis = new int[len][len];
        List<Integer>[] nodes = new List[len];
        int target = 0;
        for(int i = 0; i < len; i ++){
            nodes[i] = new ArrayList<>();
            for(int j = 0; j < graph[i].length; j ++){
                nodes[i].add(graph[i][j]);
            }
            target += (1 << i);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < len; i ++){
            int step = dp(nodes, i, 1<<i, dp, target);
            min= Math.min(min, step);
        }

        return min;
    }
    public int dp(List<Integer>[] nodes, int cur, int visited, int[][] dp, int target){
        if(dp[cur][visited] != 0 && dp[cur][visited] != Integer.MAX_VALUE)return dp[cur][visited];
        if(visited == target)return 0;
        int min = Integer.MAX_VALUE;
        for(int i = nodes[cur].size()-1; i >= 0; i --){
            int next = nodes[cur].get(i);
            //visited next
            nodes[cur].remove((Integer)next);
            if(((visited >> next) & 1) == 1){
                int left = dp(nodes, next, visited, dp, target);
                if(left != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + left);
                }
            }else {
                visited += (1 << next);
                int left = dp(nodes, next, visited, dp, target);
                if(left != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + left);
                }
                visited -= (1 << next);
            }
            nodes[cur].add((Integer)next);
            //unvisited
        }
        dp[cur][visited] = min;
        return min;
    }

    public int shortestPathLength2(int[][] graph) {
        if (graph == null || graph.length < 2) return 0;
        int top = 1 << graph.length, mask = top-1;
        boolean[] seen = new boolean[top*graph.length + mask];
        MyCircularQueue queue = new MyCircularQueue(1000);
        for (int i = 0; i < graph.length; i++) {
            int state = top*i + (1<<i);
            queue.enQueue(state);
            seen[state] = true;
        }
        int steps = 0, frontier = queue.size();
        while (!queue.isEmpty()) {
            frontier--;
            int current = queue.deQueue();
            int node = current/top, visited = current & mask;
            if (visited == mask) return steps;
            for (int adj : graph[node]) {
                int newState = top*adj + visited | (1<<adj);
                if (seen[newState]) continue;
                seen[newState] = true;
                queue.enQueue(newState);
            }
            if (frontier == 0) {
                frontier = queue.size();
                steps++;
            }
        }
        return 0;
    }

    class MyCircularQueue {
        int[] values;
        int head = 0, tail = 1, size = 0, cap;
        public MyCircularQueue(int k) {
            if (k == 1) tail = 0;
            cap = k;
            values = new int[k];
        }
        public boolean enQueue(int value) {
            if (size == cap) return false;
            values[head = (head + 1) % cap] = value;
            size++;
            return true;
        }
        public int deQueue() {
            int val = values[tail];
            tail = (tail + 1) % cap;
            size--;
            return val;
        }
        public int size() {return size;}
        public boolean isEmpty() {
            return size == 0;
        }

    }

    public static void main(String[] args){
        int[][] gp = {{1}, {0,2,4}, {1,3}, {2}, {1,5}, {4}}; //{{1,2,3}, {0}, {0}, {0}};
        System.out.print(new ShortestPathVisitingAllNodes().shortestPathLength(gp));
    }
}
