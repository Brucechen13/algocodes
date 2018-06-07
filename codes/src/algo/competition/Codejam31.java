package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Codejam31 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();

            Map<Integer, List<Integer>> edgs = new HashMap<>();
            for(int j = 0; j < n; j ++){
                int x = in.nextInt();
                int y = in.nextInt();
                if(!edgs.containsKey(x)){
                    edgs.put(x, new ArrayList<>());
                }
                if(!edgs.containsKey(y)){
                    edgs.put(y, new ArrayList<>());
                }
                edgs.get(x).add(y);
                edgs.get(y).add(x);
            }
            int[] res = new int[n];
            if(dfs(edgs, 1, new ArrayList<>(), -1)){
                findALl(edgs, res);
            }
            StringBuilder resStr = new StringBuilder();
            for (int r:
                 res) {
                resStr.append(r + " ");
            }
            System.out.println("Case #" + i + ": " + resStr.substring(0, resStr.length()-1));
        }
    }

    public static boolean dfs(Map<Integer, List<Integer>> edgs, int node, List<Integer> cached, int preNode){
        cached.add(node);
        for(int nextNode : edgs.get(node)){
            if(nextNode == preNode)continue;

            if(!cached.contains(nextNode)){
                if(dfs(edgs, nextNode, cached, node))return true;
            }else{
                int index = cached.indexOf(nextNode);
                cached = cached.subList(index, cached.size());
                edgs.put(0, new ArrayList<>());
                for (int key : edgs.keySet()) {
                    List<Integer> newEdges = new ArrayList<>();
                    for(int value : edgs.get(key)){
                        if(cached.contains(value)){
                            if(!newEdges.contains(0)){
                                newEdges.add(0);
                            }
                        }else{
                            newEdges.add(value);
                        }
                    }
                    if(cached.contains(key)){
                        edgs.get(0).addAll(newEdges);
                    }else{
                        edgs.put(key, newEdges);
                    }
                }
                return true;
            }
        }
        cached.remove((Integer) node);
        return false;
    }

    public static void findALl(Map<Integer, List<Integer>> edges, int[] res){
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.add(0);
        int len = 1;
        int[] visited = new int[res.length];
        while (!queue.isEmpty()){
            List<Integer> leftNodes = new ArrayList<>();
            while (!queue.isEmpty())leftNodes.add(queue.poll());
            for (Integer leftNode:
                 leftNodes) {
                for (int nextEdg:
                        edges.get(leftNode)) {
                    if(nextEdg==0)continue;
                    if(res[nextEdg-1] == 0){
                        res[nextEdg-1] = len;
                        queue.add(nextEdg);
                    }
                }
            }
            len++;
        }
    }



}
