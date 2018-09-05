package algo.other;

import java.util.Arrays;
import java.util.Comparator;

public class IntersectionSizeAtLeastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]));
            }
        });

        int largest = -1, second = -1;
        boolean isInLargest = false, isInSecond = false;
        for(int i = 0; i < intervals.length; i ++){
            int a = intervals[i][0];
            int b = intervals[i][1];
             isInLargest = a <= largest;
             isInSecond = a <= second;

             if(isInLargest && isInSecond)continue;

             res += isInLargest?1:2;

             second = isInLargest?largest:b-1;
             largest = b;
        }
        return res;
    }
}
