package algo.array;

/**
 * Created by chenc on 2017/9/28.
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int start = 0;
        while (start < matrix.length/2) {
            int len = matrix.length - 1 - start - start;
            for (int i = 0; i < len; i++) {
                moveMat(matrix, start, i);
            }
            start++;
        }
    }

    public void moveMat(int[][] matrix, int start, int index){
        int temp = matrix[start][start + index];
        int end = matrix.length - 1 - start;
        matrix[start][start + index] = matrix[end-index][start];
        matrix[end-index][start] = matrix[end][end-index];
        matrix[end][end-index] = matrix[start+index][end];
        matrix[start+index][end] = temp;
    }
}
