package algo.other;

import java.util.Arrays;

public class CherryPickup {
    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0][0] == -1) return 0;
        int w = grid[0].length;
        int h = grid.length;
        int[][] dp = new int[h][w];
        int max = maxVal(grid, 0, 0, dp);
        clearGrid(grid, dp);
        dp = new int[h][w];
        max += maxVal(grid, 0, 0, dp);
        return max;
    }

    public int maxVal(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        int w = grid[0].length;
        int h = grid.length;
        int max = 0;
        if (i != h - 1 || j != w - 1) {
            if (i + 1 < h && grid[i + 1][j] != -1) {
                max = maxVal(grid, i + 1, j, dp);
            }
            if (j + 1 < w && grid[i][j + 1] != -1) {
                max = Math.max(max, maxVal(grid, i, j + 1, dp));
            }
        }
        if (grid[i][j] == 1) max += 1;
        dp[i][j] = max;
        return max;
    }

    public void clearGrid(int[][] grid, int[][] dp) {
        int i = 0, j = 0;
        int w = grid[0].length;
        int h = grid.length;
        while (i < w && j < h) {
            if (grid[i][j] == 1) {
                grid[i][j] = 0;
                dp[i][j] -= 1;
            }
            if (i + 1 < w && dp[i][j] == dp[i + 1][j]) {
                i++;
            } else {
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid =
                {{1, 1, 1, 1, 0, 0, 0}
                        , {0, 0, 0, 1, 0, 0, 0}
                        , {0, 0, 0, 1, 0, 0, 1}
                        , {1, 0, 0, 1, 0, 0, 0}
                        , {0, 0, 0, 1, 0, 0, 0}
                        , {0, 0, 0, 1, 0, 0, 0}
                        , {0, 0, 0, 1, 1, 1, 1}};
        System.out.println(new CherryPickup().cherryPickup(grid));
    }


    public int cherryPickup2(int[][] grid) {
        int n = grid.length;
        return Math.max(0, helper(grid, n, 0, 0, 0, new Integer[n][n][n]));
    }

    public int helper(int[][] grid, int n, int r1, int c1, int c2, Integer[][][] memo) {
        int r2 = r1 + c1 - c2;
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }
        if (memo[r1][c1][c2] != null) {
            return memo[r1][c1][c2];
        }
        if (r1 == r2 && c1 == c2) {
            memo[r1][c1][c2] = grid[r1][c1];
        } else {
            memo[r1][c1][c2] = grid[r1][c1] + grid[r2][c2];
        }
        return memo[r1][c1][c2] += Math.max(Math.max(helper(grid, n, r1 + 1, c1, c2, memo), helper(grid, n, r1, c1 + 1, c2, memo)),
                Math.max(helper(grid, n, r1 + 1, c1, c2 + 1, memo), helper(grid, n, r1, c1 + 1, c2 + 1, memo)));
    }

    public int cherryPickup3(int[][] grid) {
        int n = grid.length;

        int[][] f = new int[n][n];

        for (int[] row : f) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        f[0][0] = grid[0][0];

        for (int t = 1; t <= 2 * (n - 1); t++) {   // 1 <= t == r1 + c1 == r2 + c2 <= 2 * (n - 1)
            int[][] f2 = new int[n][n];
            for (int[] row : f2) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            for (int i = Math.max(0, t - (n - 1)); i <= Math.min(n - 1, t); i++) {
                for (int j = Math.max(0, t - (n - 1)); j <= Math.min(n - 1, t); j++) {
                    if (grid[i][t - i] == -1 || grid[j][t - j] == -1) {
                        continue;
                    }
                    int val = grid[i][t - i];
                    if (i != j) {
                        val += grid[j][t - j];
                    }
                    for (int p = i - 1; p <= i; p++) {
                        for (int q = j - 1; q <= j; q++) {
                            if (p >= 0 && q >= 0) {
                                f2[i][j] = Math.max(f2[i][j], f[p][q] + val);
                            }
                        }
                    }
                }
            }
            f = f2;
        }
        return Math.max(0, f[n - 1][n - 1]);
    }
}
