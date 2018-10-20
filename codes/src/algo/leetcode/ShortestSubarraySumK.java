package algo.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarraySumK {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for(int i = 0; i < N; i ++){
            B[i+1] = B[i] + A[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < N+1; i ++){
            while (deque.size() > 0 && B[i] - B[deque.getFirst()] >= K){
                res = Math.min(res, i - deque.pollFirst());
            }
            while (deque.size() > 0 && B[i] <= deque.getLast()){
                deque.pollLast();
            }
            deque.addLast(i);
        }
        return res <= N ? res : -1;
    }

    public final int shortestSubarray2(final int[] array, final int k) {
        final int length = array.length;
        final long[] prefixSums = new long[length + 1];
        for (int i = 0; i < length; ++i) {
            prefixSums[i + 1] = prefixSums[i] + array[i];
        }

        final int[] window = new int[length + 1];
        int left = 0;
        int right = 0;
        int res = length + 1;
        for (int i = 1; i <= length; i++) {
            while (left <= right && prefixSums[i] - prefixSums[window[left]] >= k) {
                res = Math.min(res, i - window[left]);
                left++;
            }
            while (left <= right && prefixSums[i] <= prefixSums[window[right]]) {
                right--;
            }
            window[++right] = i;
        }
        return res <= length ? res : -1;
    }

    public static void main(String[] args){
        int[] A = {84, -37, 32, 40, 95};
        System.out.print(new ShortestSubarraySumK().shortestSubarray(A, 167));
    }
}
