package algo.competition;

import java.util.*;
public class CodeHulu {


    public int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0)
            return 0;
        int res = 0;
        for(int i = 1; i < n; i *= 10){
            int c = i * 10;
            res += (n/c)*i + Math.min(Math.max((n%c -i + 1), 0), i);
        }
        return res;
    }



    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int a, b;
        while(cin.hasNextInt())
        {
            a = cin.nextInt();
            b = cin.nextInt();
            System.out.println(a + b);
        }
    }
}
