package algo.array;

import java.util.*;

public class FindKPairsSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        k = (k > nums1.length * nums2.length) ? nums1.length * nums2.length : k;
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>(){
            @Override
            public int compare (int[] a, int[] b) {
                if (nums1[a[0]] + nums2[a[1]] == nums1[b[0]] + nums2[b[1]]) {
                    return 0;
                }
                return nums1[a[0]] + nums2[a[1]] < nums1[b[0]] + nums2[b[1]] ? -1 : 1;
            }
        });
        boolean[][] visited = new boolean[nums1.length][nums2.length];
        visited[0][0] = true;
        minHeap.offer(new int[]{0,0});
        while (k > 0) {
            int[] cur = minHeap.poll();
            res.add(new int[]{nums1[cur[0]], nums2[cur[1]]});
            if (cur[1] + 1 < nums2.length && !visited[cur[0]][cur[1] + 1]) {
                visited[cur[0]][cur[1] + 1] = true;
                minHeap.offer(new int[]{cur[0], cur[1] + 1});
            }
            if (cur[0] + 1 < nums1.length && !visited[cur[0] + 1][cur[1]]) {
                visited[cur[0] + 1][cur[1]] = true;
                minHeap.offer(new int[]{cur[0] + 1, cur[1]});
            }
            k--;
        }
        return res;
    }
}
