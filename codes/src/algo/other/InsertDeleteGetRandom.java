package algo.other;

import java.util.*;

public class InsertDeleteGetRandom {
    class RandomizedCollection {

        private Map<Integer, Set<Integer>> values = new HashMap<>();
        private Map<Integer, Integer> numPos = new HashMap<>();

        /** Initialize your data structure here. */
        public RandomizedCollection() {

        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            if(!values.containsKey(val)){
                values.put(val, new HashSet<>());
            }
            int pos = numPos.size();
            values.get(val).add(pos);
            numPos.put(pos, val);
            return values.get(val).size()==1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(values.containsKey(val)){
                Set<Integer> poss = values.get(val);
                int rmPos = poss.iterator().next();
                values.get(val).remove(rmPos);
                if(values.get(val).size() == 0){
                    values.remove(val);
                }
                numPos.remove(rmPos);
                if(rmPos != numPos.size()){
                    int lastPos = numPos.size();
                    int lastVal = numPos.get(lastPos);
                    numPos.remove(lastPos);
                    numPos.put(rmPos, lastVal);
                    values.get(lastVal).remove(lastPos);
                    values.get(lastVal).add(rmPos);
                }
                return true;
            }
            return false;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            int index = new Random().nextInt(numPos.size());
            return numPos.get(index);
        }
    }
}
