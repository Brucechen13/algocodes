package algo.other;

public class KthSmallestNumber {
    public int findKthNumber(int m, int n, int k) {
        if (m > n)
        {
            int t = m;
            m = n;
            n = t;
        }

        int low = 1 , high = k;

        while (low < high - 1) {
            int mid = (high + low) / 2;
            if (getRank(m, n, mid) < k) low = mid;
            else high = mid;
        }

        return high;
    }

    private int getRank(int m, int n, int p) {
        m = Math.min(m, p);
        int rank = 0;
        for (int i = 1; i <= m; i++)  {
            rank += Math.min(n, p/i);
        }
        return rank;
    }
}
