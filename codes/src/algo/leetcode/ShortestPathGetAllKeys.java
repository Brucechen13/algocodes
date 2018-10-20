package algo.leetcode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ShortestPathGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int target = 0;
        int count = 0;
        char[][] grids = new char[n][m];
        int[] startPosi = new int[2];
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[i].length(); j ++){
                int d = grid[i].charAt(j) - 'A';
                if(d >= 0 && d <= 6){
                    if(((target>>d) & 1) == 0){
                        target |= (1<<d);
                        count++;
                    }
                }
                grids[i][j] = grid[i].charAt(j);
                if(grid[i].charAt(j) == '@'){
                    startPosi[0] = i;
                    startPosi[1] = j;
                }
            }
        }
        boolean[][][] visited = new boolean[n][m][1<<7];
        Queue<int[]> queue = new LinkedBlockingQueue<>();
        queue.add(new int[]{startPosi[0], startPosi[1], 0});
        visited[startPosi[0]][startPosi[1]][0] = true;
        final int[][] dirs = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i ++){
                int[] val = queue.poll();
                for(int[] dir : dirs){
                    int[] newVal = new int[3];
                    newVal[0] = val[0] + dir[0];
                    newVal[1] = val[1] + dir[1];
                    newVal[2] = val[2];
                    if(newVal[0] >= 0 && newVal[0] < n
                            && newVal[1] >= 0 && newVal[1] < m &&
                            grids[newVal[0]][newVal[1]] != '#'){
                        if(grids[newVal[0]][newVal[1]] >= 'a' &&
                                grids[newVal[0]][newVal[1]] <= 'f'){
                            int dis = grids[newVal[0]][newVal[1]] - 'a';
                            if(((newVal[2] >> dis)&1) == 0){
                                newVal[2] = newVal[2] | (1<<dis);
                                if(target == newVal[2]){
                                    return step;
                                }
                            }
                        }else if(grids[newVal[0]][newVal[1]] >= 'A' &&
                                grids[newVal[0]][newVal[1]] <= 'F'){
                            int dis = grids[newVal[0]][newVal[1]] - 'A';
                            if(((newVal[2] >> dis)&1) == 0){
                                continue;
                            }
                        }
                        if(!visited[newVal[0]][newVal[1]][newVal[2]]){
                            queue.add(newVal);
                            visited[val[0]][val[1]][val[2]] = true;
                        }
                    }
                }
            }
            step += 1;
        }
        return -1;
    }



    class State {
        int keys, i, j;
        State(int keys, int i, int j) {
            this.keys = keys;
            this.i = i;
            this.j = j;
        }
    }
    public int shortestPathAllKeys2(String[] grid) {
        int x = -1, y = -1, m = grid.length, n = grid[0].length(), max = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    x = i;
                    y = j;
                }
                if (c >= 'a' && c <= 'f') {
                    max = Math.max(c - 'a' + 1, max);
                }
            }
        }
        State start = new State(0, x, y);
        Queue<State> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + x + " " + y);
        q.offer(start);
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                State cur = q.poll();
                if (cur.keys == (1 << max) - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int i = cur.i + dir[0];
                    int j = cur.j + dir[1];
                    int keys = cur.keys;
                    if (i >= 0 && i < m && j >= 0 && j < n) {
                        char c = grid[i].charAt(j);
                        if (c == '#') {
                            continue;
                        }
                        if (c >= 'a' && c <= 'f') {
                            keys |= 1 << (c - 'a');
                        }
                        if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {
                            continue;
                        }
                        if (!visited.contains(keys + " " + i + " " + j)) {
                            visited.add(keys + " " + i + " " + j);
                            q.offer(new State(keys, i, j));
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args){
        String[] grid = {"@...a",".###A","b.BCc"}; //{"@.a.#","###.#","b.A.B"};
        System.out.print(new ShortestPathGetAllKeys().shortestPathAllKeys(grid));
    }
}
