package algo.competition;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static int countPairs(List<Integer> numbers, int k) {
        // Write your code here
        Collections.sort(numbers);
        Set<Integer> vals = new HashSet<>(numbers);
        int sum = 0;
        for(int i = 0; i < numbers.size(); i ++){
            if(i > 0 && numbers.get(i) == numbers.get(i-1))continue;
            if(vals.contains(numbers.get(i) + k)){
                for(int j = i+1; j < numbers.size(); j ++){
                    if(j > 0 && numbers.get(j) == numbers.get(j-1))continue;
                    if(numbers.get(j) != numbers.get(i) && vals.contains(numbers.get(j) + k)){
                        sum ++;
                    }
                }
            }
        }
        return sum;

    }

    public static void main(String[] args){
       String s = "asd";
    }
}
