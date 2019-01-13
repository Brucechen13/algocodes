package algo.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for(int i = 0; i < S.length(); i ++){
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int pre = 0;
        Set<Integer> exist = new HashSet<>();
        for(int i = 0; i < S.length(); i ++){
            exist.add(S.charAt(i) - 'a');
            if(last[S.charAt(i) - 'a'] == i){
                boolean flag = true;
                for (int e:
                     exist) {
                    if(last[e] > i){
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    exist.clear();
                    res.add(i - pre + 1);
                    pre = i + 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij"));
    }
}
