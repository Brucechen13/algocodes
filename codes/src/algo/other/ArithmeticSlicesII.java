package algo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlicesII {

    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int sum = 0;
        for(int i = 0; i < len-2; i ++){
            for(int j = i+2; j < len; j ++){
                int k = i+2;
                for(; k < j; k ++){
                    if(A[k] - A[k-1] != A[k-1] - A[k-2])break;
                }
                if(k == j) {
                    sum++;
                    int size = j - i + 1;
                    if(size % 2 == 0){
                        int step = 2;
                        size = size/2;
                        while (step < size){
                            if(size/step<2)break;
                            if(size%step == 0)sum++;
                            step += 2;
                        }
                    }
                }
            }
        }
        return sum;
    }


    public int numberOfArithmeticSlices2(int[] A) {
        int res = 0;

        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            m.putIfAbsent(A[i], new ArrayList<>());
            m.get(A[i]).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> e : m.entrySet()) {
            if (e.getValue().size() > 2) {
                int n = e.getValue().size();
                res += (1 << n) - 1 - n - n * (n - 1) / 2;
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] == A[i] || (long) A[j] - A[i] > Integer.MAX_VALUE || (long) A[j] - A[i] < Integer.MIN_VALUE) {
                    continue;
                }

                res += helper(A, A[j], A[j] - A[i], j, m);
            }
        }

        return res;
    }

    private int helper(int[] A, int curr, int d, int idx, Map<Integer, List<Integer>> m) {
        if ((long) curr + d > Integer.MAX_VALUE || (long) curr + d < Integer.MIN_VALUE || !m.containsKey(curr + d)) {
            return 0;
        }

        int res = 0;
        curr += d;
        List<Integer> list = m.get(curr);
        for (int i : list) {
            if (i > idx) {
                res += helper(A, curr, d, i, m) + 1;
            }
        }
        return res;
    }


    public int numberOfArithmeticSlices3(int[] A) {
        int res = 0;
        Map<Integer, Integer>[] map = new Map[A.length];

        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>(i);

            for (int j = 0; j < i; j++) {
                long diff = (long)A[i] - A[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;

                int d = (int)diff;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                res += c2;
                map[i].put(d, c1 + c2 + 1);
            }
        }

        return res;
    }
}
