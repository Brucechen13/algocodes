package algo.other;

import java.util.*;

public class MaximumFrequencyStack {
    static class FreqStack {

        private Map<Integer, Integer> valCount = new HashMap<>();
        private Map<Integer, List<Integer>> countSet = new HashMap<>();
        int maxLen = 0;

        public FreqStack() {
        }

        public void push(int x) {
            if(valCount.containsKey(x)){
                int preCount = valCount.get(x);
                valCount.put(x, preCount+1);
                if(!countSet.containsKey(preCount+1)){
                    countSet.put(preCount+1, new ArrayList<>());
                }
                countSet.get(preCount+1).add(x);
                maxLen = Math.max(maxLen, preCount+1);
            }else{
                valCount.put(x, 1);
                if(!countSet.containsKey(1)){
                    countSet.put(1, new ArrayList<>());
                }
                countSet.get(1).add(x);
                maxLen = Math.max(maxLen, 1);
            }
        }

        public int pop() {
            int index = maxLen;
            int len = countSet.get(index).size();
            int val = countSet.get(index).get(len-1);
            if(len  == 1){
                maxLen --;
            }
            countSet.get(index).remove(len-1);
            int count = valCount.get(val);
            valCount.put(val, count-1);
            return val;
        }
    }

    public static void main(String[] args){
        FreqStack stack = new FreqStack();
        stack.push(56);
        stack.push(17);
        stack.push(55);
        stack.push(35);
        stack.push(96);
        stack.push(9);
        System.out.println(stack.pop());
        stack.push(98);
        System.out.println(stack.pop());
        stack.push(41);
        System.out.println(stack.pop());
        stack.push(50);
        System.out.println(stack.pop());
        stack.push(14);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
