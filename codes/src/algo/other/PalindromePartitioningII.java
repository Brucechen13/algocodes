package algo.other;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioningII {
    private Map<String, Integer> cached = new HashMap<>();
    public boolean isPartition(String s){
        int len = s.length();
        for(int i = 0; i < len/2; i ++){
            if(s.charAt(i) != s.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }
    public int minCut(String s) {
        return digui(s, 0);
    }
    public int digui(String s, int count){
        if(cached.containsKey(s)){
            return cached.get(s) == -1 ? -1 : cached.get(s) + count;
        }
        int len = s.length();
        int min = Integer.MAX_VALUE;
        //System.out.println(s);
        for(int i = len; i > 0; i --){
            if(isPartition(s.substring(0,i))){
                if(i == len){
                    cached.put(s, 0);
                    return count;
                }
                int cc = digui(s.substring(i,len), count+1);
                if(cc != -1 && min>cc){
                    min = cc;
                }
            }
        }
        int res = min!=Integer.MAX_VALUE?min:-1;
        cached.put(s, res == -1 ? -1 : res - count);
        return res;
    }


    public int minCut2(String s) {
        int len = s.length();
        int[] f = new int[len];

        for (int i = 0;i < len; i++) f[i] = i;

        for (int i = 0; i < len;i++) {
            expand(f, s, i, i);
            expand(f, s, i, i+1);
        }

        return f[len-1];
    }

    public void expand(int[] f, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                f[right] = 0;
            } else {
                f[right] = Math.min(f[right], f[left-1] + 1);
            }

            left--;
            right++;
        }
    }

    public static void main(String[] args){
        String s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
        System.out.println(new PalindromePartitioningII().minCut(s));
    }
}
