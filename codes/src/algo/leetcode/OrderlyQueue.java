package algo.leetcode;

import java.util.Arrays;

public class OrderlyQueue {
    public String orderlyQueue(String S, int K) {
        int len = S.length();
        char[] cs = S.toCharArray();
        if(K > 1){
            Arrays.sort(cs);
            return new String(cs);
        }
        int pre = 0;
        for(int i = 1; i < len; i ++){
            if(isMin(cs, i, pre))pre = i;
        }
        char[] res = new char[len];
        for(int i = 0; i < len; i ++){
            res[i] = cs[(i+pre)%len];
        }
        return new String(res);
    }

    public boolean isMin(char[] arrs, int cur, int pre){
        int len = arrs.length;
        for(int i = 0; i < len; i ++){
            int index1 = (cur+i)%len;
            int index2 = (pre+i)%len;
            if(arrs[index1] != arrs[index2])return arrs[index1] < arrs[index2];
        }
        return false;
    }

    public String orderlyQueue2(String S, int K) {
        if (S == null || S.length() <= 1) {
            return S;
        }

        if (K > 1) {
            char[] buf = S.toCharArray();
            Arrays.sort(buf);
            return new String(buf);
        } else {
            char min = 'z';
            int n = S.length();
            String sMin = S;
            for (int i = 0; i < n; i++) {
                if (S.charAt(i) <= min) {
                    min = S.charAt(i);
                    String s = S.substring(i, n) + S.substring(0, i);
                    if (s.compareTo(sMin) < 0) {
                        sMin = s;
                    }
                }
            }
            return sMin;
        }
    }

    public static void main(String[] args){
        System.out.println(new OrderlyQueue().orderlyQueue("baaca", 1));
    }
}
