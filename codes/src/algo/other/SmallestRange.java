package algo.other;

import java.util.*;

public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {

        List<Integer> window = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < nums.size(); i ++){
            int[] vals = new int[3];
            vals[0] = nums.get(i).get(0);
            vals[1] = i;
            vals[2] = 0;
            queue.add(vals);
            window.add(vals[0]);
        }
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int mix = Collections.max(window) - Collections.min(window);
            if(mix < min){
                res = new ArrayList<>(window);
                min = mix;
            }
            int[] val = queue.poll();
            while (!queue.isEmpty() && val[2] == nums.get(val[1]).size()){
                val = queue.poll();
            }
            if(!queue.isEmpty()){
                window.remove((Integer)val[0]);
                window.add(nums.get(val[1]).get(val[2]));
                val[2] ++;
                val[0] = nums.get(val[1]).get(val[2]);
                window.add(nums.get(val[1]).get(val[2]));
            }
        }
        int[] d = new int[res.size()];
        for(int i = 0;i<res.size();i++){
            d[i] = res.get(i);
        }
        return d;
    }
}
