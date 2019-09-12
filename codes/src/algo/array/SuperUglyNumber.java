package algo.array;

import java.util.*;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n == 1)return 1;
        --n;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        for(int p : primes){
            queue.add(p);
            visited.add(p);
        }
        while (--n > 0){
            int val = queue.poll();
            //System.out.println(val);
            for(int p : primes){
                long res = (long)val*p;
                if(res > Integer.MAX_VALUE)continue;
                int r = (int)res;
                if(visited.contains(r))continue;
                queue.add(r);
                visited.add(r);
            }
        }
        if(queue.isEmpty())return -1;
        return queue.poll();
    }

    public int nthSuperUglyNumberI2(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            //find next
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);

            //slip duplicate
            for (int j = 0; j < primes.length; j++) {
                while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++;
            }
        }

        return ugly[n - 1];
    }

    public int nthSuperUglyNumber3(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;

            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                if (val[j] == ugly[i]) val[j] = ugly[idx[j]++] * primes[j];
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }

        return ugly[n - 1];
    }

    public int nthSuperUglyNumberHeap4(int n, int[] primes) {
        int[] ugly = new int[n];

        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) pq.add(new Num(primes[i], 1, primes[i]));
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            ugly[i] = pq.peek().val;
            while (pq.peek().val == ugly[i]) {
                Num nxt = pq.poll();
                pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
            }
        }

        return ugly[n - 1];
    }

    private class Num implements Comparable<Num> {
        int val;
        int idx;
        int p;

        public Num(int val, int idx, int p) {
            this.val = val;
            this.idx = idx;
            this.p = p;
        }

        @Override
        public int compareTo(Num that) {
            return this.val - that.val;
        }
    }

    public static void main(String[] args){
        int[] a = {7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,
                127,131,137,139,157,167,179,181,199,211,229,233,239,241,251};
        System.out.println(new SuperUglyNumber().nthSuperUglyNumber(100000, a));
    }
}
