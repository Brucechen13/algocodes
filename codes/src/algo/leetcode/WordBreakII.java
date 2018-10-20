package algo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {

    ArrayList<String> output;
    StringBuilder sb;
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        output = new ArrayList<String>();
        sb = new StringBuilder();
        if (s.length() == 0 || dict.size() == 0) {
            return output;
        }
        // to speed up, check if each character is in the dict
        boolean found = false;
        for (int i = 0; i < s.length(); i++) {
            for (String dictWord : dict) {
                if (dictWord.indexOf(s.charAt(i)) >= 0) {
                    found = true;
                    break;
                }
                found = false;
            }
            if (!found) return output;
        }

        findWords(s, dict);
        return output;
    }
    public void findWords(String s, Set<String> dict) {
        // base case: return when string is ""
        if (s.length() == 0) {
            output.add(sb.toString());
            return;
        }
        // check substring from 0 to i
        for (int i = 1; i <= s.length(); i++) {
            String toFind = s.substring(0, i);
            if (dict.contains(toFind)) {
                if (sb.length() > 0)
                    sb.append(" ");
                sb.append(toFind);
                findWords(s.substring(i), dict);
                int start = sb.lastIndexOf(toFind);
                if (start > 0) start = start - 1;
                int end = sb.length();
                sb.delete(start, end);
            }
        }
        return;
    }


    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s.length() == 0 || wordDict.size() == 0) return result;
        Set<String> set = new HashSet<>();
        int maxLen = Integer.MIN_VALUE, minLen = Integer.MAX_VALUE;
        for(String str : wordDict){
            set.add(str);
            maxLen = Math.max(maxLen, str.length());
            minLen = Math.min(minLen, str.length());
        }
        wordBreakHelper(result, set, new boolean[s.length()], new StringBuilder(), s, maxLen, minLen, 0);
        return result;
    }

    public boolean wordBreakHelper(List<String> result, Set<String> set, boolean[] dead, StringBuilder sb,
                                   String s, int maxLen, int minLen, int start){
        if(start == s.length()){
            sb.setLength(sb.length() - 1);
            result.add(sb.toString());
            return true;
        }
        if(dead[start]) return false;
        boolean success = false;
        for(int i = start + minLen - 1; i < Math.min(start + maxLen, s.length()); i++){
            String sub = s.substring(start, i + 1);
            if(set.contains(sub)){
                int sbLen = sb.length();
                sb.append(sub).append(' ');
                if(wordBreakHelper(result, set, dead, sb, s, maxLen, minLen, i + 1)) success = true;
                sb.setLength(sbLen);
            }
        }
        dead[start] = !success;
        return success;
    }
}
