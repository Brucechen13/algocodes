package algo.other;

import java.util.TreeSet;

public class MaxSumRectangleK {
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int result = Integer.MIN_VALUE;
        int rows = matrix.length, cols = matrix[0].length;
        if (rows == 0) {
            return 0;
        }
        for (int i = 0; i < rows; i++) {
            int[] sumPer = new int[cols];
            for (int j = i; j >= 0; j--) {
                int sumCur = 0;
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0);
                for (int k = 0; k < cols; k++) {
                    sumPer[k] += matrix[j][k];
                    sumCur += sumPer[k];
                    Integer subRes = sumSet.ceiling(sumCur - target);
                    if (subRes != null) {
                        result = Math.max(result, sumCur - subRes);
                    }
                    sumSet.add(sumCur);
                }
            }
        }
        return result;
    }
}
