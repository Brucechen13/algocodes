package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam61 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {
            int N = in.nextInt();
            int[] arrs = new int[N];
            Map<Integer, List<Integer>> valCounts = new HashMap<>();
            int zeroCount = 0;
            for(int i = 0; i < N; i ++){
                arrs[i] = in.nextInt();
                if(arrs[i] == 0){
                    zeroCount ++;
                    continue;
                }
                if(!valCounts.containsKey(arrs[i])){
                    valCounts.put(arrs[i], new LinkedList<>());
                }
                valCounts.get(arrs[i]).add(i);
            }
            //System.out.println("sovle:" + ii + " " + arrs[0]);
            int res = zeroCount * (zeroCount-1)/2 * (N-zeroCount) + zeroCount*(zeroCount-1)*(zeroCount-2)/6;
            for(int i = 0; i < N; i ++){
                for(int j = i+1; j < N; j ++){
                    int val1 = arrs[i];
                    int val2 = arrs[j];
                    if(val1 == 0 || val2 == 0)continue;
                    if(Integer.MAX_VALUE/val1>val2 &&  valCounts.containsKey(val1 * val2)){
                        List<Integer> lts = valCounts.get(val1 * val2);
                        for (int li:
                             lts) {
                            if(li > j)res++;
                        }
                    }
                    int max = Math.max(val1, val2);
                    int min = Math.min(val1, val2);

                    if(max%min == 0 && valCounts.containsKey(max / min) && (max/min != val1 * val2)){
                        List<Integer> lts = valCounts.get(max / min);
                        for (int li:
                                lts) {
                            if(li > j)res++;
                        }
                    }
                }
            }
//            res = 0;
//            for(int i  = 0; i < N; i ++){
//                for(int j = i+1; j < N; j ++){
//                    for(int k = j+1; k < N; k ++){
//                        if(arrs[i] == 0 || arrs[j] == 0 || arrs[k] == 0){
//                            int zeros = 0;
//                            zeros += arrs[i] == 0?1:0;
//                            zeros += arrs[j] == 0?1:0;
//                            zeros += arrs[k] == 0?1:0;
//                            if(zeros >= 2)res++;
//                        }else if((arrs[i]%arrs[j] == 0 && arrs[i]/arrs[j] == arrs[k])
//                            || (arrs[i]%arrs[k] == 0 && arrs[i]/arrs[k] == arrs[j])
//                            || (arrs[j]%arrs[k] == 0 && arrs[j]/arrs[k] == arrs[i])
//                        || (arrs[j]%arrs[i] == 0 && arrs[j]/arrs[i] == arrs[k])
//                        || (arrs[k]%arrs[i] == 0 && arrs[k]/arrs[i] == arrs[j])
//                        || (arrs[k]%arrs[j] == 0 && arrs[k]/arrs[j] == arrs[i])){
//                            res++;
//                        }
//                    }
//                }
//            }

            System.out.println("Case #" + ii + ": " + String.format("%d",
                    res));
        }
    }
}
