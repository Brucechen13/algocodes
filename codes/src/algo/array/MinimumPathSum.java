package algo.array;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length < 1){
            return 0;
        }
        int[][] res = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i + 1 >= grid.length && j + 1 >= grid[0].length) {
                    res[i][j] = 0;
                } else if (j + 1 >= grid[0].length) {
                    res[i][j] = grid[i][j] + res[i + 1][j];
                } else if (i + 1 >= grid.length) {
                    res[i][j] = grid[i][j] + res[i][j + 1];
                } else {
                    res[i][j] = Math.min(grid[i][j] + res[i][j + 1],
                            grid[i][j] + res[i + 1][j]);
                }
            }
        }
        return res[0][0];
    }
}
