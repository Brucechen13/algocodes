package algo.string;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for(String s : wordList){
            set.add(s);
        }
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            count ++;
            for(int i = 0; i < size; i ++){
                String s = queue.poll();
                char[] vals = s.toCharArray();
                for(int j = 0; j < vals.length; j ++){
                    char tmp = vals[j];
                    for(char c = 'a'; c <= 'z'; c++){
                        vals[j] = c;
                        String next = String.valueOf(vals);
                        if(visited.contains(next) || !set.contains(next))continue;
                        if(next.equals(endWord))return count+1;
                        visited.add(next);
                        queue.add(next);
                    }
                    vals[j] = tmp;
                }
            }
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> s = new HashSet<String>(wordList);
        if (!s.contains(endWord)) return 0;
        Set<String> q1 = new HashSet<String>();
        Set<String> q2 = new HashSet<String>();
        q1.add(beginWord);
        q2.add(endWord);
        int res = 0;
        while(!q1.isEmpty() && !q2.isEmpty()){
            res++;
            if(q1.size() > q2.size()){
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }

            Set<String> q = new HashSet<String>();
            for(String word : q1){
                char[] w = word.toCharArray();
                for(int i = 0; i < w.length; i++){
                    char ch = w[i];
                    for(char c = 'a'; c <= 'z'; c++){
                        w[i] = c;
                        String newWord = new String(w);
                        if(q2.contains(newWord)) return res+1;
                        if(!s.contains(newWord)) continue;
                        q.add(newWord);
                        s.remove(newWord);
                    }
                    w[i] = ch;
                }
            }
            q1 = q;
        }
        return 0;
    }



    public static void main(String[] args){
        float a = -0.0f;
        float b = 1.0f/a;
        System.out.println(-100 > b);
        System.out.println(Math.max(Integer.MIN_VALUE, b));
        List<String> dict = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        System.out.println(new WordLadder().ladderLength("hit", "cog", dict));
    }
}
