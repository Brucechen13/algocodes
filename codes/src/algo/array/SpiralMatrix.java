package algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenc on 2017/10/2.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<Integer>();

        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }

        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int rowBegin = 0, rowEnd = n;
        int colBegin = 0, colEnd = n;
        int cur = 1;
        while (rowBegin < rowEnd && colBegin < colEnd) {
            for(int i = colBegin; i < colEnd; i ++){
                res[rowBegin][i] = cur++;
            }
            rowBegin++;
            for(int i = rowBegin; i < rowEnd; i ++){
                res[i][colEnd-1] = cur++;
            }
            colEnd--;
            for(int i = rowEnd; i > rowBegin; i --){
                res[rowEnd-1][i-1] = cur++;
            }
            rowEnd--;
            for(int i = colEnd; i > colBegin; i --){
                res[rowBegin][i-1] = cur++;
            }
            colBegin ++;
        }
        return res;
    }
}
