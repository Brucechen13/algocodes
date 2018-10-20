package algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];

        for(int i = 0; i < s.length(); i ++)dp[i][0] = 1;
        for(int i = 1; i <= s.length(); i ++){
            for(int j = 1; j <= t.length(); j ++){
                dp[i][j] = dp[i-1][j];
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    public int numDistinct2(String s, String t) {
        int[] counts = new int[t.length()+1]; // holds the number of distinct prefixes of t in s; index is the length of the prefix;
        counts[0] = 1; // zero-length prefix of t which is a empty string

        // preprocess t building a table of characters' positions in t;
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i+1);
                map.put(c, list);
            } else {
                list.add(i+1);
            }
        }

        // loop through s and update counts
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) continue;
            // Here is the tricky part.
            // You may be tempted to update the counts on the fly,
            // but that would imply that a single character could
            // be in mulitple places at the same, which is wrong.
            List<Integer> add = new ArrayList<>();
            for (Integer pos: list) {
                add.add(counts[pos-1]);
            }
            for (int j = 0; j < list.size(); j++) {
                counts[list.get(j)] += add.get(j);
            }
        }

        // return the count of the longest prefix of t which is t itself
        return counts[t.length()];
    }
}
