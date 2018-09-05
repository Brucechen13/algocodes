package algo.array;

/**
 * Created by chenc on 2017/10/2.
 */

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        List<Interval> res = new ArrayList<Interval>();
        if(intervals==null || intervals.size() < 1){
            return res;
        }
        int min = intervals.get(0).start;
        int max = intervals.get(0).end;
        for(int i = 1; i < intervals.size(); i ++){
            if(intervals.get(i).start > max || intervals.get(i).end < min){
                if(intervals.get(i).start > max) {
                    res.add(new Interval(min, max));
                    min = intervals.get(i).start;
                    max = intervals.get(i).end;
                }else{
                    res.add(new Interval(intervals.get(i).start, intervals.get(i).end));
                }
            }else{
                min = Math.min(min, intervals.get(i).start);
                max = Math.max(max, intervals.get(i).end);
            }
        }
        res.add(new Interval(min, max));
        return res;
    }
}
