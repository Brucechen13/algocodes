package algo.leetcode;


import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        UnionFind uf = new UnionFind(grid);
        int n = grid.length;
        int m = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    for(int[] dir : dirs){
                        int index0 = i*m + j;
                        int newI = i + dir[0];
                        int newJ = j + dir[1];
                        if(newI >= 0 && newI < n && newJ >= 0 && newJ < m && grid[newI][newJ]==1){
                            uf.union(index0, newI*m + newJ);
                        }
                    }
                }
            }
        }
        int maxCount = uf.getCount(0);

        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    maxCount = Math.max(maxCount, uf.combineAll(i, j));
                }
            }
        }
        return maxCount;
    }

    class UnionFind{
        int[] father;
        int[] count;
        int n, m;
        public UnionFind(int[][] grid){
            n = grid.length;
            m = grid[0].length;
            int size = n*m + 1;
            father = new int[size];
            count = new int[size];
            for(int i = 0; i < n; i ++){
                for(int j = 0; j < m; j ++){
                    father[i*m+j] = i*m+j;
                    if(grid[i][j] == 1){
                        count[i*m+j] = 1;
                    }
                }
            }
        }

        public int union(int index1, int index2){
            int id1 = find(index1);
            int id2 = find(index2);
            if(id1 != id2){
                father[id2] = id1;
                count[id1] += count[id2];
            }
            return id1;
        }

        public int find(int index){
            if(index == father[index])return index;
            return find(father[index]);
        }

        public int getCount(int index){
            return count[find(index)];
        }

        public int combineAll(int i, int j){
            Set<Integer> visited = new HashSet<>();
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int sum = 0;
            for(int[] dir : dirs){
                int newI = i + dir[0];
                int newJ = j + dir[1];
                if(newI >= 0 && newI < n && newJ >= 0 && newJ < m){
                    int index1 = newI*m + newJ;
                    int id1 = find(index1);
                    if(!visited.contains(id1)){
                        visited.add(id1);
                        sum += count[id1];
                    }
                }
            }
            return sum+1;
        }
    }


    void dfs(int[][] grid,int[][] label,int n,int m,int x,int y,int c)
    {
        if (grid[x][y]==0 || label[x][y]!=0) return;
        label[x][y]=c;
        if (x>0) dfs(grid,label,n,m,x-1,y,c);
        if (y>0) dfs(grid,label,n,m,x,y-1,c);
        if (x<n-1) dfs(grid,label,n,m,x+1,y,c);
        if (y<m-1) dfs(grid,label,n,m,x,y+1,c);
    }
    public int largestIsland2(int[][] grid) {
        int n=grid.length,m=grid[0].length,count=0;
        int[][] label=new int[n][m];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (grid[i][j]==1 && label[i][j]==0)
                {
                    count++;
                    dfs(grid,label,n,m,i,j,count);
                }
        int[] area=new int[count+1];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                area[label[i][j]]++;
        int ans=0;
        for (int i=1;i<=count;i++) ans=Math.max(ans,area[i]);
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (label[i][j]==0)
                {
                    int left=0,right=0,up=0,down=0;
                    if (i>0) left=label[i-1][j];
                    if (j>0) right=label[i][j-1];
                    if (i<n-1) up=label[i+1][j];
                    if (j<m-1) down=label[i][j+1];
                    int now=1;
                    if (left>0) now+=area[left];
                    if (right>0 && right!=left) now+=area[right];
                    if (up>0 && up!=left && up!=right) now+=area[up];
                    if (down>0 && down!=left && up!=down && down!=right) now+=area[down];
                    ans=Math.max(ans,now);
                }
        return ans;
    }

    public static void main(String[] args){
        int[][] gird = {{0,0,0,0,0,0,0},{0,1,1,1,1,0,0},{0,1,0,0,1,0,0},{1,0,1,0,1,0,0},{0,1,0,0,1,0,0},{0,1,0,0,1,0,0},{0,1,1,1,1,0,0}};
        System.out.println(new MakingALargeIsland().largestIsland(gird));
    }
}
