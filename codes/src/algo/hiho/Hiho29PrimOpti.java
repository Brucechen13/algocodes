package algo.hiho;


import java.util.*;

public class Hiho29PrimOpti {
    private static int prim(List[] edges, List[] weights, int index){
        Set<Integer> visited = new HashSet<>();
        int sum = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        queue.add(new int[]{index, 0});
        while (!queue.isEmpty()){
            int[] val = queue.poll();
            int node = val[0];
            if(visited.contains(node))continue;
            sum += val[1];
            visited.add(node);
            if(visited.size() == edges.length)return sum;
            for (int i = 0; i < edges[node].size(); i ++){
                int next = (int)edges[node].get(i);
                int w = (int)weights[node].get(i);
                if(!visited.contains(next)) {
                    queue.add(new int[]{next, w});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List[] edges = new List[n];
        List[] weights = new List[n];
        for(int i = 0; i < n; i ++){
            edges[i] = new ArrayList();
            weights[i] = new ArrayList();
        }
        for(int i = 0; i < m; i ++){
            int x = scanner.nextInt()-1;
            int y = scanner.nextInt()-1;
            int w = scanner.nextInt();

            edges[x].add(y);
            weights[x].add(w);
            edges[y].add(x);
            weights[y].add(w);
        }

        System.out.println(prim(edges, weights, 0));

    }
}
