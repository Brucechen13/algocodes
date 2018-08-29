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
            for(int i = 0; i < 3*N; i ++){
                A[i] = in.nextInt();
            }
            for(int i = 0; i < 3*N; i ++){
                B[i] = in.nextInt();
            }
            boolean[] visited = new boolean[3*N];
            List<int[]> resB = generate_arrs(B, visited, 0);


            List<int[]> resA0 = generate_arrs(A, visited, 0);
            Set<String> resAs = new HashSet<>();
            for (int[] a : resA0){
                resAs.add(a[0] + " " + a[1] + " " + a[2]);
            }

            double maxRate = 0;

            for (String s : resAs) {
                int[] resA = new int[3];
                String[] ss = s.split(" ");
                for(int i = 0; i < 3; i ++){
                    resA[i] = Integer.parseInt(ss[i]);
                }
                int beats = 0;
                for (int[] cur : resB) {
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
                    int sum = A[i0] + A[i1] + A[i2];
                    if(index == 2){
                        int[] arr = new int[3];
                        arr[2] = sum;
                        res.add(arr);
                    }else{
                        List<int[]> preRes = generate_arrs(A, visited, index+1);
                        for(int[] arr : preRes){
                            arr[index] = sum;
                        }
                        res.addAll(preRes);
                    }
                    visited[i2] = false;
                }
                visited[i1] = false;
            }
            visited[i0] = false;
        }
        return res;
    }
}
