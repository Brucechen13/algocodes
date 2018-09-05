package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam53 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {

            int N = in.nextInt();
            int[] A = new int[3*N];
            int[] B = new int[3*N];
            int sumA = 0;
            for(int i = 0; i < 3*N; i ++){
                A[i] = in.nextInt();
                sumA += A[i];
            }
            int sumB = 0;
            for(int i = 0; i < 3*N; i ++){
                B[i] = in.nextInt();
                sumB += B[i];
            }
            boolean[] visited = new boolean[3*N];
            List<int[]> resB = generate_arrs(B, visited, 0);


//            List<int[]> resA0 = generate_arrs(A, visited, 0, sumA);
//            Set<String> resAs = new HashSet<>();
//            for (int[] a : resA0){
//                resAs.add(a[0] + " " + a[1] + " " + a[2]);
//            }
//
            double maxRate = 0;
            List<int[]> resAs = generate_best_arrs(A);

            for (int[] resA : resAs) {
//                int[] resA = new int[3];
//                String[] ss = s.split(" ");
//                for(int i = 0; i < 3; i ++){
//                    resA[i] = Integer.parseInt(ss[i]);
//                }
                int beats = 0;
                for (int[] cur : resB) {
                    cur[2] = sumB - cur[0] - cur[1];
                    int beat = 0;
                    for (int i = 0; i < 3; i++) {
                        if (resA[i] > cur[i]) beat++;
                    }
                    if (beat > 1) beats++;
                }
                maxRate = Math.max(1.0*beats/resB.size(), maxRate);
            }

            System.out.println("Case #" + ii + ": " + String.format("%.9f",
                    maxRate));
        }
    }

    public static List<int[]> generate_best_arrs(int[] A){
        Arrays.sort(A);
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        int[] arr = new int[3];
        arr[0] = A[0] + A[1] + A[2];
        queue.add(new int[]{A[3], 1});
        queue.add(new int[]{A[4], 1});
        for(int i = 5; i < A.length; i ++){
            int[] cur = queue.poll();
            cur[0] += A[i];
            cur[1] ++;
            if(cur[1] == 3){
                if(i == A.length-1){
                    arr[2] = cur[0];
                }else {
                    arr[1] = cur[0];
                }
            }else{
                queue.add(cur);
            }
        }
        res.add(arr);
        arr = new int[3];
        int index = 0;
        queue.add(new int[]{A[0], 1});
        queue.add(new int[]{A[1], 1});
        queue.add(new int[]{A[2], 1});
        for(int i = 3; i < A.length; i ++){
            int[] cur = queue.poll();
            cur[0] += A[i];
            cur[1] ++;
            if(cur[1] == 3){
                arr[index++] = cur[0];
            }else{
                queue.add(cur);
            }
        }
        res.add(arr);


        arr = new int[3];
        index = 1;
        arr[0] = A[8] + A[0] + A[2];
        queue.add(new int[]{A[3], 1});
        queue.add(new int[]{A[2], 1});
        for(int i = 4; i < A.length-1; i ++){
            int[] cur = queue.poll();
            cur[0] += A[i];
            cur[1] ++;
            if(cur[1] == 3){
                arr[index++] = cur[0];
            }else{
                queue.add(cur);
            }
        }
        res.add(arr);

        return res;
    }

    public static List<int[]> generate_arrs(int[] A, boolean[] visited, int index){
        int len = A.length;
        List<int[]> res = new ArrayList<>();
        for(int i0 = 0; i0 < len; i0 ++){
            if(visited[i0])continue;
            visited[i0] = true;
            for(int i1 = i0+1; i1 < len; i1 ++){
                if(visited[i1])continue;
                visited[i1] = true;
                for(int i2 = i1+1; i2 < len; i2 ++){
                    if(visited[i2])continue;
                    visited[i2] = true;
//                    for(int i3 = i2+1; i3 < len; i3 ++){
//                        if(visited[i3])continue;
//                        visited[i3] = true;
//                        for(int i4 = i3+1; i4 < len; i4 ++){
//                            if(visited[i4])continue;
//                            visited[i4] = true;
                            int sum = A[i0] + A[i1] + A[i2];// + A[i3] + A[i4];
                            if(index == 1){
                                int[] arr = new int[3];
                                arr[1] = sum;
                                res.add(arr);
                            }else{
                                List<int[]> preRes = generate_arrs(A, visited, index+1);
                                for(int[] arr : preRes){
                                    arr[index] = sum;
                                }
                                res.addAll(preRes);
                            }
//                            visited[i4] = false;
//                        }
//                        visited[i3] = false;
//                    }
                    visited[i2] = false;
                }
                visited[i1] = false;
            }
            visited[i0] = false;
        }
        return res;
    }
}
