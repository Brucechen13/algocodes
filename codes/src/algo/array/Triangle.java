package algo.array;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chenc on 2017/7/9.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i = triangle.size()-1; i >= 0; i --){
            for(int j = 0; j < triangle.get(i).size(); j ++){
                A[i] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }
}
