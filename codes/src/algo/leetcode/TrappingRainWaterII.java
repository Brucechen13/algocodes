package algo.leetcode;

import java.util.*;

public class TrappingRainWaterII {
    Map<Integer, Integer> visited = new HashMap<>();
    public int trapRainWater(int[][] heightMap) {
        if(heightMap==null || heightMap.length < 2)return 0;
        int res = 0;
        for(int i = 1; i < heightMap.length-1; i ++){
            for(int j = 1; j < heightMap[0].length-1; j ++){
                int index = i*heightMap[0].length + j;
                if(visited.keySet().contains(index))continue;
                res += dfs(heightMap, i, j);
            }
        }
        return res;
    }

    public int dfs(int[][] heightMap, int i, int j){
        if(i == heightMap.length-1 || i == 0 || j == 0 || j == heightMap[0].length-1)return Integer.MIN_VALUE;
        int[][] dirs = {{0,-1}, {0,1}, {1, 0}, {-1, 0}};
        List<int[]> leftDirs = new LinkedList<>();
        int max = Integer.MAX_VALUE;
        for (int[] dir:
             dirs) {
            if(heightMap[i+dir[0]][j+dir[1]] > heightMap[i][j]){
                max = Math.min(max, heightMap[i+dir[0]][j+dir[1]] - heightMap[i][j]);
            }else if(!visited.keySet().contains((i + dir[0])*heightMap[0].length + j + dir[1]) || visited.get((i + dir[0])*heightMap[0].length + j + dir[1]) == heightMap[i][j]){
                leftDirs.add(dir);
            }
        }
        heightMap[i][j] += max;
        int index = i*heightMap[0].length + j;
        visited.put(index, 1);
        int res = 0;
        boolean flag = true;
        for (int[] dir:
                leftDirs) {
            int ans = dfs(heightMap, i + dir[0], j + dir[1]);
            if (ans < 0) {
                flag = false;
                ans = 0;
            }
            res += ans;
        }
        if(flag)res+=max;
        visited.put(index, heightMap[i][j]);
        return res;
    }


    public int trapRainWater2(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 1 || heightMap[0].length <= 1) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                return c1[2] - c2[2];
            }
        });
        int rows = heightMap.length, cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    visited[i][j] = true;
                    pq.add(new int[]{i, j, heightMap[i][j]});
                }
            }
        }

        int waterTrapped = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            for (int[] dir : dirs) {
                int nx = cell[0] + dir[0];
                int ny = cell[1] + dir[1];
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    waterTrapped += Math.max(0, cell[2] - heightMap[nx][ny]);
                    pq.add(new int[]{nx, ny, Math.max(heightMap[nx][ny], cell[2])});
                }
            }
        }

        return waterTrapped;
    }

    public static void main(String[] args){
        int[][] heis = {{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        new TrappingRainWaterII().trapRainWater(heis);
    }
}
