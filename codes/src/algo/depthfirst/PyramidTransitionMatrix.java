package algo.depthfirst;

import java.util.*;

public class PyramidTransitionMatrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> maps = new HashMap<>();
        for(String allow : allowed){
            if(!maps.containsKey(allow.substring(0,2))){
                maps.put(allow.substring(0,2), new ArrayList<>());
            }
            maps.get(allow.substring(0,2)).add(allow.substring(2));
        }
        return dfs(bottom, maps, new ArrayList<String>(), 0);
    }

    public boolean dfs(String bottom, Map<String, List<String>> maps, List<String> layers, int index){
        if(bottom.length()==1 && (layers.size()==0 || (layers.size()==index && index==1))){
            return true;
        }else if(layers.size() <= index){
            layers = new ArrayList<String>();
            for(int i = 1; i < bottom.length(); i ++){
                layers.add(bottom.substring(i-1, i +1));
            }
            bottom = "";
            return dfs(bottom, maps, layers, 0);
        }
        for(; index < layers.size(); index ++){
            if(maps.containsKey(layers.get(index))){
                if(maps.get(layers.get(index)).size() == 1){
                    bottom += maps.get(layers.get(index)).get(0);
                }else{
                    for(String sub : maps.get(layers.get(index))){
                        if(dfs(bottom+sub, maps, layers, index+1))return true;
                    }
                    return false;
                }
            }else{
                return false;
            }
        }
        return dfs(bottom, maps, layers, index);

    }




    int[][]T;
    HashSet<Long> seen;
    public boolean pyramidTransition2(String bottom, List<String> allowed) {
        T = new int[7][7];
        for(String a: allowed){
            T[a.charAt(0)-'A'][a.charAt(1)-'A'] |= 1 << a.charAt(2)-'A';
        }
        seen = new HashSet<>();
        int N = bottom.length();
        int [][] A = new int[N][N];
        int t=0;
        for(char c: bottom.toCharArray()){
            A[N-1][t++] = c-'A';
        }

        return solve(A, 0, N-1, 0);
    }

    public boolean solve(int [][]A, long R, int N, int i){
        if(N==1 && i==1){
            return true;
        }
        else if(i==N){
            if(seen.contains(R))return false;
            seen.add(R);
            return solve(A, 0, N-1, 0);
        }
        else{
            int w = T[A[N][i]][A[N][i+1]];

            for(int b=0; b<7; b++){
                if(((w>>b)&1)!=0){
                    A[N-1][i] = b;

                    if(solve(A, R*8 + (b+1), N, i+1))return true;
                }
            }

            return false;
        }
    }
}
