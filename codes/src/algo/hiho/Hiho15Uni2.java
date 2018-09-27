package algo.hiho;

import java.util.*;

public class Hiho15Uni2 {

    static class Node{
        String val;
        String parent;
        List<String> sons = new ArrayList<>();
        List<String> searchs = new ArrayList<>();
        List<Integer> orders = new ArrayList<>();
        int type = 0; //0 unvisited 1 visiting 2 visited
        public Node(String val){
            this.val = val;
        }
    }

    public static Map<String, String> parents = new HashMap<>();

    public static String getParent(String s){
        if(parents.get(s).equals(s)){
            return s;
        }else{
            String parent = getParent(parents.get(s));
            parents.put(s, parent);
            return parent;
        }
    }

    public static void isIN(Node node, Map<String, Node> cached,
                            String[] res){
        for(int i = 0; i < node.searchs.size(); i ++){
            Node near = cached.get(node.searchs.get(i));
            if(near.type == 0)continue;
            int order = node.orders.get(i);
            if(near.type == 1){
                res[order] = near.val;
            }else if(near.type == 2){
                String val = getParent(near.val);
                res[order] = val;
            }
        }
    }

    public static void dfs(Node node, Map<String, Node> cached,
                           String[] res){

        node.type = 1;

        isIN(node, cached, res);
        parents.put(node.val, node.val);
        for(String s : node.sons){
            Node child = cached.get(s);
            dfs(child, cached, res);
            parents.put(child.val, node.val);
        }
        node.type = 2;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, Node> cached = new HashMap<>();
        String root = null;
        for(int i = 0; i < n; i ++){
            String s1 = scanner.next();
            if(root == null)root = s1;
            String s2 = scanner.next();
            if(!cached.containsKey(s1)){
                Node node = new Node(s1);
                cached.put(s1, node);
            }
            if(!cached.containsKey(s2)){
                Node node = new Node(s2);
                cached.put(s2, node);
            }
            Node node = cached.get(s1);
            node.sons.add(s2);
            node = cached.get(s2);
            node.parent = s1;
        }
        int m = scanner.nextInt();
        String[] res = new String[m];
        for(int i = 0; i < m; i ++){
            String s1 = scanner.next();
            String s2 = scanner.next();
            cached.get(s1).orders.add(i);
            cached.get(s1).searchs.add(s2);
            cached.get(s2).orders.add(i);
            cached.get(s2).searchs.add(s1);
        }
        dfs(cached.get(root), cached, res);
        for(int i = 0; i < m; i ++){
            System.out.println(res[i]);
        }
    }
}
