package algo.hiho;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Hiho28Heap {

    public static void main(String[] args){
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; i ++){
            String c = scanner.next();
            if(c.equals("A")){
                queue.add(scanner.nextInt());
            }else{
                System.out.println(queue.poll());
            }
        }
    }
}
