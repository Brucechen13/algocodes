package algo.hiho;

import java.util.*;

public class Hiho26Prim {
    private static int prim(int[][] dis, int index){
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
            if(visited.size() == dis.length)return sum;
            for(int i = 0; i < dis.length; i ++){
                if(i != node && !visited.contains(i)) {
                    queue.add(new int[]{i, dis[node][i]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dis = new int[n][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                dis[i][j] = scanner.nextInt();
            }
        }

        System.out.println(prim(dis, 0));

    }
}
