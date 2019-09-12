package algo.interesting;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */

public class AtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int start = 0;
        int end = 0;
        while(end < s.length()) {
            char c = s.charAt(end);
            if(count[c-'a'] < k) {
                if(start == end) {
                    start++;
                } else {
                    return Math.max(longestSubstring(s.substring(start, end), k),
                            longestSubstring(s.substring(end+1), k));
                }
            }
            end++;
        }

        return end-start;
    }
}
