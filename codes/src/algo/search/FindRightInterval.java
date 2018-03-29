package algo.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindRightInterval {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int[] findRightInterval(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < intervals.length; i ++){
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
            map.put(starts[i], i);
        }
        Arrays.sort(starts);
        int[] res = new int[intervals.length];

        for(int i = 0; i < intervals.length; i ++){
            int index = binarySearch(starts, intervals[i].end);
            if(index < intervals[i].end){
                res[i] = -1;
            }else{
                res[i] = map.get(index);
            }
        }
        return res;
    }

    public int binarySearch(int[] list, int x) {
        int left = 0, right = list.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return list[left];
    }
    public static void main(String[] args){
        Interval[] intervals = new Interval[1];
        intervals[0] = new Interval(1,2);

        System.out.println(new FindRightInterval().findRightInterval(intervals)[0]);
    }
}
