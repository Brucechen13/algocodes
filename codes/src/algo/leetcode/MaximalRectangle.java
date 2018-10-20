package algo.leetcode;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length < 1)return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        int max = 0;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(matrix[i][j] == '1')height[j] ++;
                else height[j] = 0;
            }
            int curleft = 0;
            for(int j = 0; j < n; j ++){
                if(matrix[i][j] == '1')left[j] = Math.max(left[j], curleft);
                else{
                    left[j] = 0;
                    curleft = j+1;
                }
            }
            int curright= n;
            for(int j = n-1; j >= 0; j --){
                if(i == 0)right[j] = n;
                if(matrix[i][j] == '1')right[j] = Math.min(right[j], curright);
                else{
                    right[j] = n;
                    curright = j;
                }
            }
            for(int j = 0; j < n; j ++)
                max = Math.max(max, (right[j]-left[j])*height[j]);
        }
        return max;
    }
}
