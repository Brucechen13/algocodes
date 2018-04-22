package algo.depthfirst;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int w = dungeon[0].length;
        int h = dungeon.length;
        int[][] dp = new int[h][w];

        dp[h-1][w-1] = Math.max(1 - dungeon[h - 1][w - 1], 1);
        for(int i = h-2; i >= 0; i --){
            dp[i][w-1] = Math.max(1, dp[i+1][w-1]-dungeon[i][w-1]);
        }
        for(int i = w-2; i >= 0; i --){
            dp[h-1][i] = Math.max(1, dp[h-1][i+1]-dungeon[h-1][i]);
        }
        for(int i = h-2; i >= 0; i --){
            for(int j = w-2; j >= 0; j --){
                int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }



        return dp[0][0];
    }
}
