package algo.leetcode;

import java.util.Scanner;

public class MaxCircleString {

    public int maxCircleStringLen(String s){
        int max = 1;
        int mx = 0, id = 0;
        s = addS(s);
        int len = s.length();
        int[] f = new int[len+1];
        for(int i = 1; i < len-1; i ++){
            if(mx > i)
                f[i] = Math.min(f[2*id - i], f[id] + id - i);
            else
                f[i] = 1;
            for(; s.charAt(i+f[i]) == s.charAt(i - f[i]); f[i]++);
            if(f[i] + i > mx){
                mx = f[i] + i;
                id = i;
            }
            max = Math.max(max, f[i]);
        }
        return max - 1;
    }
    public String addS(String s){
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        sb.append("#");
        for(int i = 0; i < s.length();i ++){
            sb.append(s.charAt(i));
            sb.append("#");
        }
        sb.append("$");
        return sb.toString();
    }

    public static void main(String[] args){
        Scanner scanner  = new Scanner(System.in);

        int n = scanner.nextInt();
        TieNode root = new TieNode();
        for(int i = 0; i < n; i ++){
            String ss = scanner.next();
            buildTieTree(root, ss, 0);
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i ++){
            int count = searchTieTRee(root, scanner.next(), 0);
            System.out.println(count);
        }

    }

    static int searchTieTRee(TieNode node, String s, int index){
        if(index == s.length())return node.count;
        TieNode child = node.c[s.charAt(index) - 'a'];
        if(child == null)return 0;
        return searchTieTRee(child, s, index+1);
    }

    static void buildTieTree(TieNode node, String s, int index){
        if(index >= s.length())return;
        if(node.c[s.charAt(index) - 'a'] == null){
            node.c[s.charAt(index) - 'a'] = new TieNode();
        }
        TieNode child = node.c[s.charAt(index) - 'a'];
        child.count ++;
        buildTieTree(child, s, index+1);
    }


    static class TieNode{
        TieNode[] c = new TieNode[26];
        int count = 0;
    }
}
