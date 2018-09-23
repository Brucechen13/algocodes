package algo.string;

import java.util.Scanner;

public class KMPAlgo {

    public static int searchString(String s1, String s2){

        int[] nexts = buildNext(s2);
        int i = 0, j = 0;
        int count = 0;
        while (i < s1.length()){
            if(j == -1 || s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
                if(j == s2.length()){
                    count++;
                    j = nexts[j];
                }
            }else{
                j = nexts[j];
            }
        }
        return count;
    }

    public static int[] buildNext(String s){
        int len = s.length();
        int[] nexts = new int[len+1];
        nexts[0] = -1;
        int k = -1, i = 0;
        while (i < len){
            if(k == -1 || s.charAt(i) == s.charAt(k)){
                k++;
                i++;
                nexts[i] = k;
            }else{
                k = nexts[k];
            }
        }
        nexts[i] = k;
        return nexts;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        while (N-- > 0){
            String s2 = scanner.next();
            String s1 = scanner.next();
            System.out.println(searchString(s1, s2));
        }
    }
}
