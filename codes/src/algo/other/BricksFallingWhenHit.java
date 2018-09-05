package algo.other;

public class BricksFallingWhenHit {
    public int[] hitBricks(int[][] grid, int[][] hits) {

        int len = hits.length;
        int[] res = new int[len];
        int n = grid.length;
        int m = grid[0].length;
        UnionFind uf = new UnionFind(n*m+1);

        for(int[] hit : hits){
            if(grid[hit[0]][hit[1]] == 1){
                grid[hit[0]][hit[1]] = 2;
            }
        }

        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(grid[i][j] == 1)unionElem(i, j, grid, uf);
            }
        }
        int count = uf.count[uf.find(0)];
        for(int i = len-1; i >= 0; i --){
            if(grid[hits[i][0]][hits[i][1]] == 2){
                unionElem(hits[i][0], hits[i][1], grid, uf);
                grid[hits[i][0]][hits[i][1]] = 1;
            }
            int newCount = uf.count[uf.find(0)];
            res[i] = (newCount - count > 0) ? newCount - count - 1 : 0;
            count = newCount;
        }
        return res;
    }

    public static void main(String[] args){
        int[][] grid = {{1,0,0,0},{1,1,1,0}};
        int[][] hits = {{1,0}};
        System.out.print(new BricksFallingWhenHit().hitBricks(grid, hits));
    }

    private void unionElem(int x, int y, int[][] grid, UnionFind uf){
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;
        int m = grid[0].length;

        for(int[] dir : dirs){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 1){
                uf.union(x*m + y + 1, newX*m + newY + 1);
            }
        }
        if(x == 0){
            uf.union(x*m + y + 1, 0);
        }
    }

    class UnionFind{
        int[] father;
        int[] count;

        public UnionFind(int len){
            father = new int[len];
            count = new int[len];
            for(int i = 0; i < len; i ++){
                father[i] = i;
                count[i] = 1;
            }
        }

        public int find(int i){
            while (father[i] != i){
                father[i] = father[father[i]];
                i = father[i];
            }
            return i;
        }

        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            if(f1 != f2){
                father[f1] = f2;
                count[f2] += count[f1];
            }
        }
    }


    public int[] hitBricks2(int[][] grid, int[][] hits) {
        int n = hits.length;
        int[] answer = new int[n];

        int m1 = grid.length;
        if (m1 == 0) return answer;
        int m2 = grid[0].length;
        if (m2 == 0) return answer;
        boolean[] isOrginalOne = new boolean[n];

        for (int k = 0; k < n; k++) {
            int[] hit = hits[k];

            if (grid[hit[0]][hit[1]] == 1) {
                isOrginalOne[k] = true;
                grid[hit[0]][hit[1]] = 0;
            }
        }

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                if (grid[i][j] == 1) grid[i][j] = 2;
            }
        }

        for (int j = 0; j < m2; j++) {
            if (grid[0][j] == 2) {
                count(grid, 0, j, m1, m2);
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            int[] hit = hits[k];
            if (!isOrginalOne[k]) continue;

            grid[hit[0]][hit[1]] = 2;
            if (connect(grid, hit[0], hit[1], m1, m2)) {
                answer[k] = count(grid, hit[0], hit[1], m1, m2) - 1;
            } else {
                answer[k] = 0;
            }

        }

        return answer;
    }

    public boolean connect(int[][] grid, int i, int j, int m, int n) {
        if (i == 0) return true;
        if (i - 1 >= 0 && grid[i - 1][j] == 1 ||
                i + 1 < m && grid[i + 1][j] == 1 ||
                j - 1 >= 0 && grid[i][j - 1] == 1 ||
                j + 1 < n && grid[i][j + 1] == 1) return true;

        return false;
    }

    public int count(int[][] grid, int i, int j, int m, int n) {
        int c = 0;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 2) return c;
        grid[i][j] = 1;
        c++;

        if (i - 1 >= 0 && grid[i - 1][j] == 2) c += count(grid, i - 1, j, m, n);
        if (i + 1 < m && grid[i + 1][j] == 2) c += count(grid, i + 1, j, m, n);
        if (j - 1 >= 0 && grid[i][j - 1] == 2) c += count(grid, i, j - 1, m, n);
        if (j + 1 < n && grid[i][j + 1] == 2) c += count(grid, i, j + 1, m, n);
        return c;
    }
}
