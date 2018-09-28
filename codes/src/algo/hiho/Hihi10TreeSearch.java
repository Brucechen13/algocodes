package algo.hiho;

import java.util.Scanner;

public class Hihi10TreeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        solve(s1, s2);
        System.out.println();
    }

    static void solve(String s, String t){
        int len = t.length();
        char c = s.charAt(0);
        int i = t.indexOf(c);
        if (i > 0){
            String ls = s.substring(1, i+1);
            String lt = t.substring(0, i);
            solve(ls, lt);
        }
        if (i < len-1){
            String rs = s.substring(i + 1);
            String rt = t.substring(i + 1);
            solve(rs, rt);
        }
        System.out.print(c);
    }

    // s1前序遍历 s2中序遍历
    public static void buildTree(String s1, String s2) {
        if (s1.length() <= 0) return;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(0)) break;
            sb.append(s2.charAt(i));
        }
        buildTree(s1.substring(1, 1 + i), sb.toString());
        buildTree(s1.substring(i + 1), s2.substring(i + 1));
        System.out.print(s1.charAt(0));
    }
}
