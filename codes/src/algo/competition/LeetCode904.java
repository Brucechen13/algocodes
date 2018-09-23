package algo.competition;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCode904 {
    public int totalFruit(int[] tree) {
        int len = tree.length;
        Map<Integer, Integer> counts = new HashMap<>();
        int first = -1, second = -1;
        int sum = 0;
        int max = -1;
        for(int i = 0; i < len; i ++){
            if(first == -1){
                counts.put(tree[i], 1);
                first = tree[i];
                sum = 1;
            }else if(second == -1){
                if(first == tree[i]){
                    counts.put(tree[i], 1);
                }else{
                    second = tree[i];
                    counts.put(tree[i], 1);
                }
                sum ++;
            }else if(first == tree[i] || second == tree[i]){
                if(first == tree[i]){
                    first = second;
                    second = tree[i];
                    counts.put(tree[i], 1);
                    counts.put(first, 0);
                }else{
                    counts.put(tree[i], counts.get(tree[i]) + 1);
                }
                sum ++;
            }else{
                first = second;
                second = tree[i];
                counts.put(tree[i], 1);
                sum = counts.get(first) + 1;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args){
        int[] trees = {1,2,3,2,2}; //{1,0,1,4,1,4,1,2,3};
        System.out.println(new LeetCode904().totalFruit(trees));
    }
}
