package algo.other;

import java.util.Stack;

public class RemoveDuplicatrLetters {
    public String removeDuplicateLetters(String s) {

        Stack<Character> res = new Stack<>();
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        for (char cs:
             s.toCharArray()) {
            count[cs - 'a'] ++;
        }
        for (char cs:
             s.toCharArray()) {
            count[cs - 'a'] --;
            if(visited[cs - 'a'])continue;
            while (!res.empty() && res.peek() > cs && count[res.peek()- 'a'] > 0){
                visited[res.peek() - 'a'] = false;
                res.pop();
            }
            res.push(cs);
            visited[cs - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : res) {
            sb.append(c);
        }
        return sb.toString();

    }
}
