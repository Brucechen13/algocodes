package algo.string;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class ACDictory {

    static class ACTieNode{
        ACTieNode[] nodes;
        ACTieNode tail;
        boolean isend;
        public ACTieNode(){
            nodes = new ACTieNode[26];
        }
    }

    static void buildTieTree(ACTieNode head, String s){
        ACTieNode tmp = head;
        for(int i = 0; i < s.length(); i ++){
            int index = s.charAt(i) - 'a';
            if(tmp.nodes[index] == null){
                tmp.nodes[index] = new ACTieNode();
            }
            tmp = tmp.nodes[index];
        }
        tmp.isend = true;
    }

    static void buildNextTie(ACTieNode head){
        Queue<ACTieNode> queue = new LinkedBlockingQueue<>();
        for(int i = 0; i < 26; i ++){
            if(head.nodes[i] != null){
                head.nodes[i].tail = head;
                queue.add(head.nodes[i]);
            }
        }
        while (!queue.isEmpty()) {
            ACTieNode curNode = queue.poll();
            for (int i = 0; i < 26; i++) {
                if(curNode.nodes[i] != null){
                    ACTieNode node = curNode.tail;
                    while (node != null){
                        if(node.nodes[i] != null){
                            curNode.nodes[i].tail = node.nodes[i];
                            break;
                        }
                        node = node.tail;
                    }
                    if(node == null){
                        curNode.nodes[i].tail = head;
                    }
                    queue.add(curNode.nodes[i]);
                }
            }
        }
    }

    static boolean searchString(String s, ACTieNode head){
        ACTieNode node = head;
        for(int i = 0; i < s.length(); i ++){
            int x = s.charAt(i) - 'a';
            while (node.nodes[x] == null && node != head){
                node = node.tail;
            }
            node = node.nodes[x];
            if(node == null){
                node = head;
            }else if(node.isend)return true;
        }
        return false;
    }

    public static void main(String[] args){
        ACTieNode head = new ACTieNode();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; i ++){
            String s = scanner.next();
            buildTieTree(head, s);
        }
        buildNextTie(head);
        String target = scanner.next();
        System.out.println(searchString(target, head)?"YES":"NO");

    }
}
