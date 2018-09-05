package algo.other;

import java.util.*;

public class CourseScheduleIII {
    public int scheduleCourse(int[][] courses) {
        List<Integer> leftDays = new ArrayList<>();
        Map<Integer, List<Integer> > leftData = new HashMap<>();
        for(int[] course : courses){
            int leftDay = course[1] - course[0];
            if(!leftData.containsKey(leftDay)){
                leftDays.add(leftDay);
                leftData.put(leftDay, new LinkedList<>());
            }
            leftData.get(leftDay).add(course[0]);
        }
        Collections.sort(leftDays);
        for (List<Integer> data:
             leftData.values()) {
            Collections.sort(data);
        }
        int maxCount = 0;
        for(int i = 0; i < leftDays.size(); i ++){
            maxCount = Math.max(dfs(leftDays, leftData, i, 0, 0, 0), maxCount);
        }
        return maxCount;
    }

    public int dfs(List<Integer> leftDays, Map<Integer, List<Integer> > leftData,
                   int i, int j, int curDays, int count){
        if(i >= leftDays.size())return count;
        int leftDay = leftDays.get(i);
        if(leftDay < curDays || j == leftData.get(leftDay).size())
            return dfs(leftDays, leftData, i+1, 0, curDays, count);
        int maxCount = count;
        for(int k = j; k < leftData.get(leftDay).size(); k ++){
            int cost = leftData.get(leftDay).get(k);
            maxCount = Math.max(maxCount, dfs(leftDays, leftData, i,
                    j + 1, curDays+cost, count+1));
        }
        maxCount = Math.max(maxCount, dfs(leftDays, leftData, i+1, 0, curDays, count));
        return maxCount;
    }

    public int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses,(a,b)->a[1]-b[1]); //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        int time=0;
        for (int[] c:courses)
        {
            time+=c[0]; // add current course to a priority queue
            pq.add(c[0]);
            if (time>c[1]) time-=pq.poll(); //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
        }
        return pq.size();
    }

    public static void main(String[] args){
        int[][] data = {{7,17},{3,12},{10,20},{9,10},{5,20},{10,19},{4,18}};
        new CourseScheduleIII().scheduleCourse(data);
    }
}
