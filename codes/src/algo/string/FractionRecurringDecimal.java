package algo.string;

import java.util.*;

public class FractionRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        long n = (long)numerator, d = (long)denominator;
        int sign = n * d >= 0 ? 1 : -1;
        n = Math.abs(n);
        d = Math.abs(d);
        if (d == 0) {
            if (sign == 1) return Integer.MAX_VALUE + "";
            else return Integer.MIN_VALUE + "";
        }

        long larger = n / d;
        long r = n - larger * d;
        List<Long> list = new ArrayList<>();
        Map<Long, Integer> remainders = new HashMap<>();

        boolean inf = false;
        int idx = -1;
        remainders.put(r, ++idx);
        while (r > 0) {
            r *= 10;
            Long res = r / d;
            r -= res * d;
            list.add(res);
            if (remainders.containsKey(r)) {
                idx = remainders.get(r);
                inf = true;
                break;
            } else if (r != 0) remainders.put(r, ++idx);
        }
        StringBuilder sb = new StringBuilder();

        if (sign == -1) sb.append("-");
        sb.append(larger);
        if (list.size() != 0) {
            sb.append(".");
            for (int i = 0; i < list.size(); i++) {
                if (i == idx && inf) sb.append("(");
                sb.append(list.get(i));
            }
            if (inf == true) sb.append(")");
        }
        return sb.toString();
    }
}
