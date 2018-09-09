package algo.competition;

import java.util.*;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/34d8ae1898c143f8b4873e7ef76c6193
 * 来源：牛客网
 *
 * 树链是指树里的一条路径。美团外卖的形象代言人袋鼠先生最近在研究一个特殊的最长树链问题。
 * 现在树中的每个点都有一个正整数值，他想在树中找出最长的树链，使得这条树链上所有对应点的值的最大公约数大于1。
 * 请求出这条树链的长度。
 */
public class Meituan2 {

    static class Node{
        private List<Integer> nodes = new ArrayList<>();
        private int val;
    }

    private static List<Integer> prims;

    public static void main(String[] args){
        //File file = new File("/home/cc/input.txt");
        prims = initPrims();
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int n = scanner.nextInt();
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i ++){
            nodes[i] = new Node();
        }
        for(int i = 0; i < n-1; i ++){
            int f = scanner.nextInt() - 1;
            int s = scanner.nextInt() - 1;
            nodes[f].nodes.add(s);
            nodes[s].nodes.add(f);
        }
        for(int i = 0; i < n; i ++){
            nodes[i].val = scanner.nextInt();
        }
        int res = dfs(nodes, 0, -1);
        System.out.println(res);
    }

    public static int dfs(Node[] nodes, int index, int parent){
        int max = -1;
        //search
        List<Integer> prims = getPrime(nodes[index].val);
        for (int prim : prims){
            int res = pathSearch(nodes, index, prim, parent, 1);
            max = Math.max(max, res);
        }
        for(int net : nodes[index].nodes){
            if(net == parent)continue;
            max = Math.max(dfs(nodes, net, index), max);
        }
        return max;
    }

    private static List<Integer> initPrims(){
        List<Integer> prims = new ArrayList<>();
        int maxn = (int)1e5 + 10;
        boolean[] vis = new boolean[maxn];
        for(int i = 2; i < maxn; i++) {
            if(vis[i]) continue;
            prims.add(i);
            for(int j = i + i; j < maxn; j += i) {
                vis[j] = true;
            }
        }
        return prims;
    }

    public static List<Integer> getPrime(int num){
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < prims.size() && prims.get(i) <= num; i++) {
            if(num % prims.get(i) == 0) {
                res.add(prims.get(i));
            }
            while(num % prims.get(i) == 0) {
                num /= prims.get(i);
            }
        }
        return res;
    }

    public static int pathSearch(Node[] nodes, int index, int prim, int parent, int len){
        int max = len;
        for (int net:
             nodes[index].nodes) {
            if(net == parent)continue;
            if(nodes[net].val%prim == 0){
                while (nodes[net].val%prim == 0)
                    nodes[net].val /= prim;
                max = Math.max(max, pathSearch(nodes, net, prim, index, len+1));
            }
        }
        return max;
    }
}
