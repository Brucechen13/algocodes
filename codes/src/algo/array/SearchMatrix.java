package algo.array;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length < 1){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0, j = cols-1;
        while (j>=0 && i < rows){
            if(matrix[i][j] > target){
                j--;
            }else if(matrix[i][j] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;

    }
}
