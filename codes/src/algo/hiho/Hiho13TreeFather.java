package algo.hiho;


import java.util.*;

public class Hiho13TreeFather {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, String> father = new HashMap<>();
        for(int i = 0; i < n; i ++){
            String c1 = scanner.next();
            String c2 = scanner.next();
            father.putIfAbsent(c2, c1);
        }
        int m = scanner.nextInt();
        for(int i = 0; i < m; i ++){
            String c1 = scanner.next();
            String c2 = scanner.next();
            Set<String> set = new HashSet<>();
            String head = c1;
            while (head != null){
                set.add(head);
                head = father.get(head);
            }
            head = c2;
            while (head != null){
                if(set.contains(head)){
                    System.out.println(head);
                    break;
                }
                head = father.get(head);
            }
            if(head == null)System.out.println(-1);
        }
    }
}
