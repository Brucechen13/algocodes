package algo.leetcode;

import java.util.*;

public class AllOoneDataStructure {
    class AllOne {

        Map<String, Integer> map;
        PriorityQueue<Map.Entry<String,Integer>> minpq;
        PriorityQueue<Map.Entry<String,Integer>> maxpq;
        /** Initialize your data structure here. */
        public AllOne() {
            map = new HashMap<>();
            minpq = new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
            maxpq = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            map.put(key, 1+map.getOrDefault(key,0));
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if(map.containsKey(key)){
                int val = map.get(key);

                if(val==1){
                    map.remove(key);
                }else{
                    map.put(key, val-1);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {

            if(map.size()==0) return "";
            map.entrySet().stream().forEach(m->maxpq.offer(m));
            return maxpq.poll().getKey();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {

            if(map.size()==0) return "";
            map.entrySet().stream().forEach(m->minpq.offer(m));
            return minpq.poll().getKey();
        }
    }
}
