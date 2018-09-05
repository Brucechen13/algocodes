package algo.other;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class KSimilarStrings {
    //BFS
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Set<String> vis= new HashSet<>();
        Queue<String> q= new LinkedList<>();
        q.add(A);
        vis.add(A);
        int res=0;
        while(!q.isEmpty()){
            res++;
            for (int sz=q.size(); sz>0; sz--){
                String s= q.poll();
                int i=0;
                while (s.charAt(i)==B.charAt(i)) i++;
                for (int j=i+1; j<s.length(); j++){
                    if (s.charAt(j)==B.charAt(j) || s.charAt(i)!=B.charAt(j) ) continue;
                    String temp= swap(s, i, j);
                    if (temp.equals(B)) return res;
                    if (vis.add(temp)) q.add(temp);
                }
            }
        }
        return res;
    }
    public String swap(String s, int i, int j){
        char[] ca=s.toCharArray();
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;
        return new String(ca);
    }

    //DFS

    public int kSimilarity2(String A, String B) {
        if (A.equals(B)) return 0;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int res = preProcess(a, b);
        res += dfs(a, b, 0);
        return res;
    }

    private int dfs(char[] a, char[] b, int start){
        if (start == a.length) return 0;
        if (a[start] == b[start]) return dfs(a, b, start + 1);

        int res = Integer.MAX_VALUE;

        for (int i = start + 1; i < a.length; i++){
            if (a[i] == b[start] && a[i] != b[i]){
                swap(a, i, start);
                res = Math.min(res, dfs(a, b, start + 1) + 1);
                swap(a, i, start);
            }
        }

        return res;
    }

    private int preProcess(char[] a, char[] b){
        int res = 0;
        for (int i = 0; i < a.length; i++){
            if (a[i] == b[i]) continue;
            for (int j = i + 1; j < a.length; j++){
                if (a[i] == b[j] && a[j] == b[i]){
                    swap(a, i, j);
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    private void swap(char[] a, int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
