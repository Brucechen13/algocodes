package algo.hiho;

import java.util.*;

public class Hiho23Dijstra {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt()-1;
        int e = scanner.nextInt()-1;
        int[][] dis = new int[n][n];
        for(int i = 0; i < m; i ++){
            int x = scanner.nextInt()-1;
            int y = scanner.nextInt()-1;
            int len = scanner.nextInt();
            dis[x][y] = dis[x][y] == 0 ? len : Math.min(dis[x][y], len);
            dis[y][x] = dis[x][y] == 0 ? len : Math.min(dis[x][y], len);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Set<Integer> visited = new HashSet<>();

        queue.add(new int[]{s, 0});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == e){
                System.out.println(cur[1]);
                break;
            }
            visited.add(cur[0]);
            for(int i = 0; i < n; i ++){
                if(visited.contains(i) || dis[cur[0]][i] == 0)continue;
                int[] next = new int[2];
                next[0] = i;
                int nextDis = cur[1]+dis[cur[0]][i];
                next[1] = dis[s][i]==0?nextDis:Math.min(dis[s][i], nextDis);
                queue.add(next);
            }
        }
    }
}
