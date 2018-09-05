package algo.other;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SwimRisingWater {
    public int swimInWater(int[][] grid) {
        if(grid == null || grid.length < 1)return 0;
        int N = grid.length, res = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->(a[0] - b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        int[][] neibor = {{-1,0},{1,0},{0,-1},{0,1}};
        while (true) {
            int[] temp = pq.poll();
            res = Math.max(res,temp[0]);
            if (temp[1] == N-1 && temp[2] == N-1) return res;
            for (int i = 0; i < 4; i++){
                int row = neibor[i][0] + temp[1];
                int col = neibor[i][1] + temp[2];
                if (row < N && col < N && row >=0 && col >=0 && !visited[row][col]) {
                    visited[row][col] = true;
                    pq.offer(new int[]{grid[row][col], row, col});
                }
            }
        }
    }


}
