package algo.other;

public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return findBiggestNum(boxes, 0, n-1, 0, dp);
    }

    public int findBiggestNum(int[] boxes, int i, int j, int k, int[][][] dp){
        if(i > j)return 0;
        if(dp[i][j][k] != 0)return dp[i][j][k];
        int ni = i;
        int preK = k;
        for(; ni < j; ni ++){
            if(boxes[ni] == boxes[ni+1]){
                k++;
            }else{
                break;
            }
        }
        int res = (k+1)*(k+1) + findBiggestNum(boxes, ni, j, 0, dp);
        for(int z = ni+1; z <= j; z++){
            if(boxes[z] == boxes[i]){
                res = Math.max(res, findBiggestNum(boxes, ni, z-1, 0, dp) +
                findBiggestNum(boxes, z, j, k+1, dp));
            }
        }
        dp[i][j][preK] = res;
        return res;
    }
}
