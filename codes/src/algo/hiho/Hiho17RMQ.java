package algo.hiho;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hiho17RMQ {

    private static Map<String, Integer> map = new HashMap<>();
    private static Map<Integer, String> name = new HashMap<>();
    private static int idx = 1;

    static int getData(String s){
        if(map.containsKey(s))return map.get(s);
        map.put(s, idx++);
        name.put(idx-1, s);
        return idx-1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i, a, b;
        int n = scanner.nextInt();
        int[] parents = new int[10000];
        for (i = 0; i < n; i++) {
            a = getData(scanner.next());
            b = getData(scanner.next());
            parents[b] = a;
        }
        int[] stackA = new int[1000];
        int[] stackB = new int[1000];
        int m = scanner.nextInt();
        for (i = 0; i < m; i++) {
            a = getData(scanner.next());
            b = getData(scanner.next());
            if(a == b){
                System.out.println(name.get(a));
                continue;
            }
            int cntA = 0, cntB = 0;
            for (; a != 0; ) {
                stackA[cntA++] = a;
                a = parents[a];
            }
            for (; b != 0; ) {
                stackB[cntB++] = b;
                b = parents[b];
            }
            cntA--;
            cntB--;
            while (cntA >= 0 && cntB >= 0 && stackA[cntA] == stackB[cntB]){
                cntA--;
                cntB--;
            }
            System.out.println(name.get(stackA[cntA + 1]));

        }
    }
}
