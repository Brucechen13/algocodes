package algo.leetcode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SlidingPuzzle {

    public int getBoardFlag(int[][] board){
        int index = 1;
        int res = 0;
        for(int i = 0; i < 6; i ++){
            int j0 = i / 3;
            int j1 = i % 3;
            res += board[j0][j1] * index;
            index *= 6;
        }
        return res;
    }

    public int slidingPuzzle(int[][] board) {
        Queue<int[]> cached = new LinkedBlockingQueue<>();
        List<Integer> visited = new ArrayList<>();

        int zeroIndex = 0;

        for(int i = 0; i < 6; i ++){
            int j0 = i / 3;
            int j1 = i % 3;
            if(board[j0][j1] == 0){
                zeroIndex=i;
                break;
            }
        }

        cached.add(new int[]{getBoardFlag(board), zeroIndex});

        visited.add(getBoardFlag(board));
        int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

        int[][] targ = {{1,2,3}, {4,5,0}};
        int target = getBoardFlag(targ);

        int step = 0;

        while (!cached.isEmpty()){
            Queue<int[]> newCached = new LinkedBlockingQueue<>();

            while (!cached.isEmpty()){
                int[] cur = cached.poll();
                if(target == cur[0])return step;
                for(int[] dir : dirs){
                    int newI = cur[1]/3 + dir[0];
                    int newJ = cur[1]%3 + dir[1];
                    if(newI >= 0 && newI < 2 && newJ >= 0 && newJ < 3){
                        int newIndex = newI*3 + newJ;
                        int pre0 = (int)Math.pow(6, cur[1]);
                        int pre1 = (int)Math.pow(6, newIndex);
                        int swap1 = cur[0] / pre1 % 6;
                        int newVal = cur[0] + swap1 * pre0  - swap1*pre1;
                        if(!visited.contains(newVal)) {
                            visited.add(newVal);
                            newCached.add(new int[]{newVal, newIndex});
                        }
                    }
                }
            }
            step++;
            cached = newCached;
        }
        return -1;
    }

    public static void main(String[] args){
        int[][] board =  {{3,2,4}, {1,5,0}};
        System.out.print(new SlidingPuzzle().slidingPuzzle(board));
    }


    public int slidingPuzzle2(int[][] board) {
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int m = board.length;
        int n = board[0].length;
        String s = "";
        String target = "123450";
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s += board[i][j];
            }
        }

        if (s.equals(target)) return 0;
        visited.add(s);
        queue.offer(s);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] t = queue.poll().toCharArray();
                int idx = 0;
                while (idx < 6 && t[idx] != '0') idx++;
                char c = t[idx];
                for (int cc : dirs[idx]) {
                    swap(t, cc, idx);
                    String ns = new String(t);
                    if (ns.equals(target)) return level;
                    if (!visited.contains(ns)) {
                        visited.add(ns);
                        queue.offer(ns);
                    }
                    swap(t, cc, idx);
                }
            }
        }
        return -1;
    }

    public void swap(char[] arr, int m, int n) {
        char c = arr[m];
        arr[m] = arr[n];
        arr[n] = c;
    }
}
