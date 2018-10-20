package algo.leetcode;

import java.util.*;

public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {

        //List<Integer> window = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Queue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minQ = new PriorityQueue<>();
        int[] d = new int[2];

        for(int i = 0; i < nums.size(); i ++){
            int[] vals = new int[3];
            vals[0] = nums.get(i).get(0);
            vals[1] = i;
            vals[2] = 0;
            queue.add(vals);
            //window.add(vals[0]);
            maxQ.add(vals[0]);
            minQ.add(vals[0]);
        }
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int mix = maxQ.peek() - minQ.peek();
            if(mix < min){
                d[0] = minQ.peek();
                d[1] = maxQ.peek();
                min = mix;
            }
            int[] val = queue.poll();
            while (!queue.isEmpty() && val[2] == nums.get(val[1]).size()-1){
                val = queue.poll();
            }
            System.out.println(queue.size() + " " + val[0] + " " + val[2]);
            if(!queue.isEmpty()){

                maxQ.remove(val[0]);
                minQ.remove(val[0]);
                //window.remove((Integer)val[0]);
                val[2] ++;
                val[0] = nums.get(val[1]).get(val[2]);
                //window.add(nums.get(val[1]).get(val[2]));

                maxQ.add(val[0]);
                minQ.add(val[0]);
                queue.add(val);
            }
        }
        return d;
    }



    public int[] smallestRange2(List<List<Integer>> nums) {
        int start = -1;
        int end = -1;
        int range = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        PriorityQueue<Element> queue = new PriorityQueue<>(new Comparator<Element>(){
            public int compare(Element e1, Element e2){
                return e1.val - e2.val;
            }
        });

        for(int i = 0; i < nums.size(); i++){
            Element e = new Element(nums.get(i).get(0), 0, i);
            queue.offer(e);
            max = Math.max(max, e.val);
        }

        while(queue.size() == nums.size()){
            Element e = queue.poll();
            if(max - e.val < range){
                range = max - e.val;
                start = e.val;
                end = max;
            }
            if(e.index + 1 < nums.get(e.row).size()){
                e.index = e.index + 1;
                e.val = nums.get(e.row).get(e.index);
                queue.offer(e);
                if(e.val > max){
                    max = e.val;
                }
            }

        }
        return new int[] {start, end};
    }
    class Element{
        int val;
        int index;
        int row;
        public Element(int val, int index, int row){
            this.val = val;
            this.index = index;
            this.row = row;
        }
    }
}
