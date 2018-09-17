package algo.competition;

import java.util.Scanner;

public class MeituanJam3 {
    static int[] ns = new int[20001];

    public static void main(String[] args) {
        f(20000);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {//注意while处理多个case
            String s = sc.next();
            int start = 0;
            while (s.charAt(start) == '0') start++;
            int sum = 0;
            int i = 1;
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '1') break;
            }
            if (s.substring(i).contains("0")) {
                sum = s.length() - i - 1;
            } else {
                sum = s.length() - i;
            }
            int len = s.length() - start;
            sum += len*(len-1)/2;
            System.out.println(sum);
        }

    }

    public static void f(int n) {
        ns[1] = 1;
        for (int i = 2; i <= n; i++) {
            ns[i] = (i - 1) + ns[i - 1];
        }
    }
}
