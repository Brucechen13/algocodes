package algo.leetcode;

import java.util.HashMap;

public class InterleavingString {
    public boolean isInterLeave(char[] s1, int f1, char[] s2, int f2, char[] s3, int f3, HashMap<String, Boolean> cache){
        if (f3 == s3.length && f1 == s1.length && f2 == s2.length ) return true;
        String key = f1 + "," + f2 + "," + f3;
        if (cache.containsKey(key)) return cache.get(key);
        if (f1 < s1.length && s1[f1] == s3[f3] && isInterLeave(s1, f1 + 1, s2, f2, s3, f3 + 1, cache)) {
            cache.put(key, true);
            return true;
        }
        if (f2 < s2.length && s2[f2] == s3[f3] && isInterLeave(s1, f1, s2, f2 + 1, s3, f3 + 1, cache)) {
            cache.put(key, true);
            return true;
        }
        cache.put(key, false);
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return isInterLeave(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0, new HashMap<String, Boolean>());
    }
}
