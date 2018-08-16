package algo.other;

import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups {

    public boolean isSimilar(String A, String B){
        int count = 0;
        for(int i = 0; i < A.length(); i ++){
            if(A.charAt(i) != B.charAt(i))count++;
            if(count > 2)return false;
        }
        return true;
    }
    public int numSimilarGroups(String[] A) {
        Set<String> set = new HashSet<>();
        //遍历数组并存入集合,如果元素已存在则不会重复存入
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        //返回Set集合的数组形式
        A =  set.toArray(new String[0]);
        int n = A.length;
        Union uf = new Union(n);
        for(int i = 0; i < A.length; i ++){
            for(int j = i+1; j < A.length; j ++){
                if(isSimilar(A[i], A[j])){
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    class Union{
        int[] vals;

        public Union(int n){
            vals = new int[n];
            for(int i = 0; i < vals.length; i ++){
                vals[i] = i;
            }
        }

        public int father(int index){
            if(vals[index] == index)return index;
            return father(vals[index]);
        }

        public void union(int index1, int index2){
            index1 = father(index1);
            index2 = father(index2);
            if(index1 != index2){
                vals[index1] = index2;
            }
        }

        public int count(){
            Set<Integer> groups = new HashSet<>();
            for(int i = 0; i < vals.length; i++){
                if(!groups.contains(father(i))){
                    groups.add(father(i));
                }
            }
            return groups.size();
        }
    }

    public static void main(String[] args){
        String[] A = {"aaaaaaaaa","aaaaaaaaa","aaaaaaaaa","aaaaaaaaa"};
        System.out.print(new SimilarStringGroups().numSimilarGroups(A));
    }
}
