package algo.array;

public class LargestBorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        if(grid == null || grid.length == 0)return 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] x = new int[n+1][m];
        int[][] y = new int[n][m+1];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                x[j+1][i] += x[j][i] + grid[j][i];
            }
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                y[i][j+1] += y[i][j] + grid[i][j];
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; i+k < n && j+k < m; k++) {
                    if(x[i+1+k][j]-x[i][j] == k+1 && x[i+1+k][j+k]-x[i][j+k] == k+1 &&
                            y[i][j+1+k]-y[i][j] == k+1 && y[i+k][j+1+k]-y[i+k][j] == k+1) {
                        ans = Math.max(ans, (k+1)*(k+1));
                    }
                }
            }
        }
        return ans;
    }
    public int largest1BorderedSquare2(int[][] grid) {
        int area = 0, m = grid.length, n = m == 0 ? 0 : grid[0].length;
        int[][] horizontal = new int[m + 1][n + 1], vertical = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                horizontal[i + 1][j + 1] = grid[i][j] == 0 ? 0 : 1 + horizontal[i + 1][j];
                vertical[i + 1][j + 1] = grid[i][j] == 0 ? 0 : 1 + vertical[i][j + 1];
                for (int len = Math.min(horizontal[i + 1][j + 1], vertical[i + 1][j + 1]); area < len * len; --len)
                    if (Math.min(horizontal[i + 2 - len][j + 1], vertical[i + 1][j + 2 - len]) >= len)
                        area = len * len;
            }
        }
        return area;
    }
    public static void main(String[] args){
        int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println(new LargestBorderedSquare().largest1BorderedSquare(grid));
    }
}
