package algo.hiho;

import java.util.Arrays;
import java.util.Scanner;

public class Hiho20Map {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int group = scanner.nextInt();
        while (group-- > 0){
            int n = scanner.nextInt();
            int[] nums = new int[n+1];
            for(int i = 1; i <= n; i ++){
                nums[i] = scanner.nextInt();
            }
            int[] res = new int[n+1];
            int[] ans = new int[n+1];
            Arrays.fill(ans, Integer.MAX_VALUE);
            dfs(1, 0, nums, res, ans);
            dfs(1, 1, nums, res, ans);

            int a = 0, b = 0;
            for (int i = 1; i <= n; i++){
                if (ans[i] == 1)
                    a++;
                else if (ans[i] == 0)
                    b++;
            }
            System.out.print(a + " ");
            for (int i = 1; i <= n; i++) if (ans[i] == 1)
                System.out.print(i + " ");
            System.out.println();
            System.out.print(b + " ");
            for (int i = 1; i <= n; i++) if (ans[i] == 0)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    public static void dfs(int cur, int val, int[] nums, int[] res, int[] ans){
        res[cur] = val;
        if(nums[cur] != res[cur-1]+val && nums[cur] != res[cur-1]+val+1)return;
        if(cur == nums.length-1){
            if(nums[cur] != res[cur-1]+val)return;
            for(int i = 1; i < nums.length; i ++){
                if(ans[i] == -1 || (ans[i] != Integer.MAX_VALUE && ans[i] != res[i])){
                    ans[i] = -1;
                }else{
                    ans[i] = res[i];
                }
            }
            return;
        }
        if(nums[cur] == res[cur-1]+val)dfs(cur+1, 0, nums, res, ans);
        else if(nums[cur] == res[cur-1]+val+1)dfs(cur+1, 1, nums, res, ans);
        else{
            dfs(cur+1, 0, nums, res, ans);
            dfs(cur+1, 1, nums, res, ans);
        }
    }
}
