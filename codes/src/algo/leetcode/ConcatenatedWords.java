package algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ConcatenatedWords {
    private TrieNode root;

    private class TrieNode {
        TrieNode[] next;
        boolean isWord;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length <= 2) return res;
        root = new TrieNode();
        for (String word : words) {
            add(word, root);
        }
        for (String word : words) {
            if (testWord(word.toCharArray(), 0, 0))
                res.add(word);
        }
        return res;
    }

    private boolean testWord(char[] word, int start, int count) {
        if (start == word.length && count >= 2) return true;
        TrieNode cur = root; //when found a word, restart the TrieNode from the root...
        for (int i = start; i < word.length; i++) {
            cur = cur.next[word[i] - 'a'];
            if (cur == null) return false; //the current segment is not in the list...
            else if(cur.isWord && testWord(word, i + 1, count + 1)) return true;
        }
        return false;
    }

    private void add(String word, TrieNode root) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new TrieNode();
            cur = cur.next[c - 'a'];
        }
        cur.isWord = true;
    }
}
