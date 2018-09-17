package algo.other;

import javax.swing.undo.CannotUndoException;
import java.util.*;

public class CutOffTrees {
    private class Tree {
        int row;
        int col;
        int height;
        int distance;
        public Tree(int row, int col, int height, int distance){
            this.row = row;
            this.col = col;
            this.height = height;
            this.distance = distance;
        }
    }
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0){
            return 0;
        }
        List<Tree> trees = new ArrayList<>();
        int maxWidth = 0;
        for (int r = 0; r < forest.size(); r++){
            maxWidth = Math.max(maxWidth, forest.get(r).size());
            for (int c = 0; c < forest.get(r).size(); c++){
                if (forest.get(r).get(c) > 1){
                    trees.add(new Tree(r, c, forest.get(r).get(c), 0));
                }
            }
        }
        Collections.sort(trees, new Comparator<Tree>(){
            @Override
            public int compare(Tree t1, Tree t2){
                if (t1.height == t2.height){
                    return 0;
                }
                return t1.height < t2.height ? -1 : 1;
            }
        });
        Tree preTree = new Tree(0, 0, 0, 0);
        int steps = 0;
        for (Tree currTree : trees){
            int step = getDistance(preTree, currTree, forest, maxWidth);
            if (step == -1){
                return -1;
            }
            steps += step;
            preTree = currTree;
        }
        return steps;
    }
    private int getDistance(Tree t1, Tree t2, List<List<Integer>> forest, int maxWidth){
        boolean[][] visited = new boolean[forest.size()][maxWidth];
        Queue<Tree> queue = new ArrayDeque<>();
        int[][] directions = new int[][]{{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(t1);
        visited[t1.row][t1.col] = true;
        while(!queue.isEmpty()){
            Tree curr = queue.poll();
            if (curr.row == t2.row && curr.col == t2.col){
                return curr.distance;
            }
            for (int i = 0; i < directions.length; i++){
                int row = curr.row + directions[i][0];
                int col = curr.col + directions[i][1];
                if (row < 0 || row >= forest.size() || col < 0 || col >= forest.get(row).size()
                        || visited[row][col] || forest.get(row).get(col) == 0){
                    continue;
                }
                queue.offer(new Tree(row, col, 0, curr.distance + 1));
                visited[row][col] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[][] forest = {{1,2,3},{0,0,4},{7,6,5}};
        List<List<Integer>> vals = new ArrayList<>();
        for(int i = 0; i < forest.length; i ++){
            List<Integer> curVals = new ArrayList<>();
            for(int j = 0; j < forest.length; j ++){
                curVals.add(forest[i][j]);
            }
            vals.add(curVals);
        }
        System.out.println(new CutOffTrees().cutOffTree(vals));
    }
}
