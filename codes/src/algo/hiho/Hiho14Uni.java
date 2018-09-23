package algo.hiho;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hiho14Uni {

    static class UnionSearch{

        private Map<String, String> root = new HashMap<>();

        UnionSearch(){

        }

        void union(String s1, String s2){
            if(root.containsKey(s1)){
                String father = getRoot(s1);
                if(root.containsKey(s2)){
                    root.put(getRoot(s2), father);
                }else{
                    root.put(s2, father);
                }
            }else{
                root.put(s1, s1);
                if(root.containsKey(s2)){
                    root.put(getRoot(s2), s1);
                }else{
                    root.put(s2, s1);
                }
            }
        }

        String getRoot(String s){
            if(!root.get(s).equals(s)){
                String father = getRoot(root.get(s));
                root.put(s, father);
                return father;
            }else{
                return s;
            }
        }

        public boolean search(String s1, String s2){
            if(root.containsKey(s1) && root.containsKey(s2)){
                    return getRoot(s1).equals(getRoot(s2));
            }
            return false;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        UnionSearch union = new UnionSearch();
        for(int i = 0; i < n; i ++){
            int op = scanner.nextInt();
            String s1 = scanner.next();
            String s2 = scanner.next();
            if(op == 0){
                union.union(s1, s2);
            }else{
                System.out.println(union.search(s1, s2)?"yes":"no");
            }
        }
    }
}
