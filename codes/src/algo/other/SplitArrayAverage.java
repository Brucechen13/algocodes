package algo.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SplitArrayAverage {
    public boolean splitArraySameAverage(int[] A) {
        return dfs(A, 0, 0, 0, 0, 0);
    }

    public boolean dfs(int[] A, int index, int a1, int a2, int count1, int count2) {
        if (index == A.length) return (a1 * count2 == a2 * count1) && (a1 != 0) && (a2 != 0);
        boolean flag1 = dfs(A, index + 1, a1 + A[index], a2, count1 + 1, count2);
        boolean flag2 = dfs(A, index + 1, a1, a2 + A[index], count1, count2 + 1);
        return flag1 || flag2;
    }

    public static void main(String[] args){
        System.out.print(new SplitArrayAverage().splitArraySameAverage(new int[]{
                //17, 3, 7, 12, 1
                //1,2,3,4,5,6,7,8
                //6, 8, 18, 3, 1
                17,5,5,1,14,10,13,1,6
        }));
    }

    public boolean splitArraySameAverage2(int[] A) {
        int n = A.length, s = Arrays.stream(A).sum();
        for (int i = 1; i <= n / 2; ++i)
            if (s * i % n == 0 && find(A, s * i / n, i, 0)) return true;
        return false;
    }

    public boolean find(int[] A, int target, int k, int i) {
        if (k == 0) return target == 0;
        if (k + i > A.length) return false;
        return find(A, target - A[i], k - 1, i + 1) || find(A, target, k, i + 1);
    }


    //unordered_map<int, unordered_map<int, unordered_map<int, bool>>> dp;
    Map<Integer, Map<Integer, Map<Integer, Boolean>>> dp = new HashMap<>();
    public boolean possible(int[] A, int n, int k, int target) {
        if (k == 0 && target == 0) {
            return true;
        }

        if (k == 0 || n == 0) {
            return false;
        }

        if(dp.containsKey(n) && dp.get(n).containsKey(k) &&
                dp.get(n).get(k).containsKey(target)){
            return dp.get(n).get(k).get(target);
        }

        boolean res = possible(A, n - 1, k, target);
        if (A[n - 1] <= target) {
            res |= possible(A, n - 1, k - 1, target - A[n - 1]);
        }
        if(!dp.containsKey(n)) {
            dp.put(n, new HashMap<>());
        }
        if(!dp.get(n).containsKey(k)) {
            dp.get(n).put(k, new HashMap<>());
        }
        dp.get(n).get(k).put(target, res);
        return res;
    }
    public boolean splitArraySameAverage3(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += A[i];
        }

        for (int k = 1; k <= n / 2; ++k) {
            if (((sum * k) % n == 0) && possible(A, n, k, sum * k / n)) {
                return true;
            }
        }
        return false;
    }


    public boolean check(int[] A, int sumB, int lenB, int sIndex) {
        if(lenB == 0) return sumB == 0;
        if(A[sIndex] > sumB / lenB) return false;
        for(int i = sIndex; i < A.length - lenB + 1; i++) {
            if(i > sIndex && A[i] == A[i - 1]) continue;
            if(check(A, sumB - A[i], lenB - 1, i + 1)) return true;
        }
        return false;
    }
    public boolean splitArraySameAverage4(int[] A) {
        if(A.length < 2) return false;
        int sumA = 0;
        Arrays.sort(A);
        for(int n : A) sumA += n;
        for(int lenB = 1; lenB <= A.length / 2; lenB++) {
            if((sumA * lenB) % A.length == 0) {
                if(check(A, (sumA * lenB) / A.length, lenB, 0))
                    return true;
            }
        }
        return false;
    }
}
