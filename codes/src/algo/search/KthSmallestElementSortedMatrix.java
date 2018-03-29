package algo.search;

import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KthSmallestElementSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], le = matrix[matrix.length-1][matrix.length-1]+1;
        while (lo < le){
            //int mid = (lo+le)/2;
            int mid = lo + (le - lo) / 2;
            int count = 0, j = matrix.length-1;
            for(int i = 0; i < matrix.length; i ++){
                while (j>=0 && matrix[i][j] > mid) j--;
                count += j+1;
            }
            if(count<k)lo=mid+1;
            else le=mid;
        }
        return lo;
    }
}
