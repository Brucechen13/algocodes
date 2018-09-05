package algo.competition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        try {
            in = new Scanner(new BufferedReader(new FileReader("C:\\Users\\chenc\\Downloads\\C-large-practice.in")));
        }catch (Exception e){
            e.printStackTrace();
        }
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Map<String, Map<String, Integer>> dt_cached = new HashMap<>();
            Set<Integer> dt_len = new HashSet<>();
            int n = in.nextInt();
            //System.out.println(n);
            String[] dict = new String[n];
            for(int j = 0; j < n; j ++){
                dict[j] = in.next();
                String val = dict[j].substring(0,1)+dict[j].substring(dict[j].length()-1)+dict[j].length();
                if(!dt_cached.containsKey(val)){
                    dt_cached.put(val, new HashMap<>());
                }
                int[] alpha = new int[26];
                Arrays.fill(alpha, 0);
                for(int alp = 0; alp < dict[j].length(); alp ++){
                    int index = dict[j].charAt(alp) - 'a';
                    alpha[index] ++;
                }
                dt_cached.get(val).put(valueOfArray(alpha),
                        dt_cached.get(val).getOrDefault(valueOfArray(alpha),0)+1);
                dt_len.add(dict[j].length());
            }
            char s1 = in.next().toCharArray()[0];
            char s2 = in.next().toCharArray()[0];
            int N = in.nextInt();
            long A = in.nextLong();
            long B = in.nextLong();
            long C = in.nextLong();
            long D = in.nextLong();
            char[] res = new char[N];
            res[0] = s1;res[1] = s2;
            int count = 0;
            nextChar(A,B,C,D,(int)s1, (int)s2, res, N);
            //System.out.println(res);

            int[][] s_lens = new int[res.length+1][26];
            int[] alpha = new int[26];
            for(int j = 0; j < res.length; j ++){
                alpha[res[j] - 'a']++;
                for(int k = 0; k < 26; k ++){
                    s_lens[j+1][k] = alpha[k];
                }
            }
            //System.out.println("build String map");

            for(int j = 0; j < res.length; j ++){
                String cur = String.valueOf(res[j]);
                for(Integer len:dt_len){
                    if(j+len > res.length)continue;
                    String key = cur+String.valueOf(res[j+len-1]) + len;
                    if(dt_cached.containsKey(key)){
                        int[] s_alpha = new int[26];
                        for(int k = 0; k < 26; k ++){
                            s_alpha[k] = s_lens[j+len][k] - s_lens[j][k];
                        }
                        String s_val = valueOfArray(s_alpha);
                        if(dt_cached.get(key).keySet().contains(s_val)) {
                            count += dt_cached.get(key).get(s_val);
                            dt_cached.get(key).remove(s_val);
                        }
                    }
                }
            }
            System.out.println("Case #" + i + ": " + count);
        }
    }

    public static String valueOfArray(int[] values){
        StringBuilder sb = new StringBuilder();
        for (int val:
             values) {
            sb.append(String.valueOf(val));
        }
        return sb.toString();
    }

    public static void nextChar(long A, long B, long C, long D, int xi1, int xi2, char[] res, int N){
        for(int i = 2; i < N; i ++){
            int xi = (int)((A * xi2 + B * xi1 + C ) % D);
            res[i] = (char)(97+(xi)%26);
            xi1 = xi2;
            xi2 = xi;
        }
    }
}
