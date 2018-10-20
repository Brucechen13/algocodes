package algo.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chenc on 2017/9/9.
 */
public class MoreSum {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()){
            int n = scan.nextInt();
            int sum = scan.nextInt();
            findAllSum(n, sum);
        }
    }

    public static void findAllSum(int n, int sum){
        List<Integer> vals = new LinkedList<Integer>();
        findPartSum(1, n, 0, sum, vals);
    }

    public static void findPartSum(int start, int n, int cur, int sum, List<Integer> values){
        findPartSum(start+1, n, cur, sum, values);
        cur += start;
        if(cur < sum){
            values.add(start);
            findPartSum(start+1, n, cur, sum, values);
        }else if(cur == sum){
            for (int val:
                 values) {
                System.out.print(val + " ");
            }
            System.out.println(start);
        }
    }

}
