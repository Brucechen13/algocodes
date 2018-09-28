package algo.hiho;

import java.util.*;

public class Hiho27Kruscal {

    private static Map<Integer, Integer> parents = new HashMap<>();
    private static int find(int node){
        if(parents.get(node) == node)return node;
        int parent = find(parents.get(node));
        parents.put(node, parent);
        return parent;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 1; i <= n; i ++){
            parents.put(i, i);
        }
        int m = scanner.nextInt();
        Queue<int[]> weights = new PriorityQueue<>((o1, o2) -> (o1[2]-o2[2]));
        for(int i = 0; i < m; i ++){
            weights.add(new int[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt()});
        }
        int sum = 0;
        int node = 1;
        while (!weights.isEmpty()){
            int[] edge = weights.poll();
            if(find(edge[0]) != find(edge[1])){
                sum += edge[2];
                parents.put(find(edge[1]), find(edge[0]));
                node ++;
                if(node == n)break;
            }
        }
        System.out.println(sum);
    }
}
