package algo.hiho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hiho11TreeDis {

    static class Node{
        List<Integer> nears = new ArrayList<>();
        int first;
        int second;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i ++)nodes[i] = new Node();
        for(int i = 0; i <  n-1; i ++){
            int e1 = scanner.nextInt();
            int e2 = scanner.nextInt();
            nodes[e1-1].nears.add(e2-1);
            nodes[e2-1].nears.add(e1-1);
        }
        calNodes(nodes, 0, -1);
        int max = 0;
        for(int i = 0; i < n; i ++){
            max = Math.max(max, nodes[i].first + nodes[i].second);
        }
        System.out.println(max);
    }

    public static void calNodes(Node[] nodes, int index, int father){
        int max1 = 0, max2 = 0;
        for (int idx:
             nodes[index].nears) {
            if(idx == father)continue;
            calNodes(nodes, idx, index);
            if(nodes[idx].first + 1 > max1){
                max2 = max1;
                max1 = nodes[idx].first + 1;
            }else if(nodes[idx].first + 1 > max2){
                max2 = nodes[idx].first + 1;
            }
        }
        nodes[index].first = max1;
        nodes[index].second = max2;
    }
}
