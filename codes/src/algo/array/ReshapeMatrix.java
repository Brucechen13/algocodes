package algo.array;

/**
 * 566. Reshape the Matrix
 * Created by chenc on 2017/5/19.
 */
public class ReshapeMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums.length * nums[0].length != r*c){
            return nums;
        }
        int[][] res = new int[r][c];
        int f1=0,f2=0;
        for(int i = 0; i < nums.length; i ++){
            for(int j = 0; j < nums[i].length; j++){
                res[f1][f2++] = nums[i][j];
                if(f2 == c){
                    f1++;
                    f2=0;
                }
            }
        }
        return res;
    }
}
