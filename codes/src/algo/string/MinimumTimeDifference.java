package algo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> times = new ArrayList<Integer>();
        for(int i = 0; i < timePoints.size(); i ++){
            times.add(getTime(timePoints.get(i)));
        }
        Collections.sort(times);
        int max = times.get(timePoints.size()-1);
        int min = times.get(0);
        List<Integer> mines = new ArrayList<Integer>();
        for(int i = 0; i < times.size()-1; i ++){
            mines.add(times.get(i+1)-times.get(i));
        }
        Collections.sort(mines);
        return Math.min(mines.get(0), 24*60+min -max);
    }
    public int getTime(String time){
        return new Integer(time.substring(0, 2))*60 + new Integer(time.substring(3));
    }
    public static void main(String[] args){
        List<String> inputs = new ArrayList<String>();
        inputs.add("01:01");
        inputs.add("02:01");
        inputs.add("03:00");
        System.out.println(new MinimumTimeDifference().findMinDifference(inputs));
    }
}
