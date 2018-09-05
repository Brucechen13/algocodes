package algo.competition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Codejam52 {

    static class Node{
        String s;
        int diff;
        Set<Integer> changed;
        int isIn;
        public Node(String s, int diff){
            this.s = s;
            this.diff = diff;
            changed = new HashSet<>();
            isIn = 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ii = 1; ii <= t; ++ii) {

            int N = in.nextInt();
            int M = in.nextInt();
            int P = in.nextInt();
            Set<String> notIn = new HashSet<>();
            int[] ones = new int[P];
            for(int i = 0; i < N; i ++){
                char[] s = in.next().toCharArray();
                for(int j = 0; j < s.length; j ++){
                    ones[j] += (s[j] - '0');
                }
            }
            for(int i = 0 ; i < M; i ++){
                notIn.add(in.next());
            }
            StringBuilder sb = new StringBuilder();
            int diff = 0;
            for (int i = 0; i < P; i ++){
                char c = ones[i]*2 > N ? '1':'0';
                diff += (c == '1' ? N - ones[i] : ones[i]);
                sb.append(c);
            }
            Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node node, Node t1) {
                    return node.diff != t1.diff ? node.diff - t1.diff
                            : (node.isIn - t1.isIn);
                }
            });
            Set<String> visited = new HashSet<>();
            visited.add(sb.toString());
            queue.add(new Node(sb.toString(), diff));
            while (!queue.isEmpty()){
                Node node = queue.poll();
                if(!notIn.contains(node.s)){
                    System.out.println("Case #" + ii + ": " + node.diff);
                    break;
                }
                for(int i = 0; i < P; i ++){
                    if(node.changed.contains(i))continue;
                    Node newNode = new Node(node.s, node.diff);
                    newNode.changed.add(i);
                    char[] cs = newNode.s.toCharArray();
                    cs[i] = (cs[i] == '1' ? '0':'1');
                    newNode.diff += (cs[i] == '1' ? N-2*ones[i] : 2*ones[i]-N);
                    newNode.s = new String(cs);
                    if(visited.contains(newNode.s))continue;
                    visited.add(newNode.s);
                    if(notIn.contains(newNode.s)){
                        newNode.isIn = 1;
                    }
                    queue.add(newNode);
                }
            }
            //if(ii > 5)continue;
        }
    }
}
