package algo.array;

import java.util.*;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Comp comparator = new Comp(k,t);
        Set<Node> s = new TreeSet(comparator);
        for(int i =0; i < nums.length; i++){
            Node nd = new Node(nums[i], i);
            if(s.contains(nd)){
                return true;
            }
            else{
                s.add(nd);
            }
        }
        return false;
    }

    private class Node{
        long val;
        int idx;
        public Node(long v, int i){
            this.val = v;
            this.idx = i;
        }
    }
    private class Comp implements Comparator<Node> {
        int t;
        int k;
        Comp(int k, int t){
            this.k = k;
            this.t = t;
        }
        @Override
        public int compare(Node n1, Node n2){
            if(t >=Math.abs(n1.val - n2.val) && k >= Math.abs(n1.idx - n2.idx)){
                return 0;
            }
            else if(n1.val > n2.val){
                return -1;
            }
            else{
                return 1;
            }
        }
    }
}
