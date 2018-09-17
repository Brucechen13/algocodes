package algo.competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//给出一个长度为n的排列{ai}，定义一种拆分方式如下
//∙ 在排列{ai}取出一个包含a1的子序列，设为{bi}。
//∙ 剩余的元素也是一个子序列，设为{ci}，其中{ci}可以为空，即前面取了{ai = bi}。
//∙ 称排列{ai}被拆分成了子序列{bi}和{ci}，可见总共有2n-1种不同的拆分方式。
//∙ 例如可以将序列{1,2,3,4,5}拆分为{1,3,5}和{2,4}。
//对于给定的m，求有多少种拆分方式使得序列{bi}和{ci}满足以下条件
//∙ 设序列为{d1,d2,d3,...,dL}，即长度为L。
//∙ 对1 < i ≤ L，
//– 若i为奇数，则d[i] > d[i−1]，否则d[i] < d[i−1]。
//– 允许有不超过m个i不满足前一条规则。
//答案对1,000,000,007取模。

public class MeituanJam6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {//注意while处理多个case
            int n = sc.nextInt();
            int m = sc.nextInt();
            //int[] nums = new int[n];
            List<Integer> nums = new ArrayList<>();
            for(int i = 0; i < n; i ++){
                //nums[i] = sc.nextInt();
                nums.add(sc.nextInt());
            }
            int preVal = nums.get(0);
            nums.remove(0);
            int res = dfs(nums, m, preVal, true, 0);
            System.out.println(res);
        }
    }

    public static int dfs(List<Integer> nums, int m, int preVal, boolean even, int start){
        if(m < 0)return 0;
        if(nums.size() == 0)return 0;
        double sum = testLeft(nums)?1:0;
        for(int i = start; i < nums.size(); i ++){
            int curVal = nums.get(i);
            nums.remove(i);
            if(even && preVal > curVal){
                sum += dfs(nums, m, curVal, !even, i);
            }else if(!even && preVal < curVal){
                sum += dfs(nums, m, curVal, !even, i);
            }else{
                sum += dfs(nums, m-1, curVal, !even, i);
            }
            nums.add(i, curVal);
        }
        return (int)(sum%(1e9+7));
    }

    public static boolean testLeft(List<Integer> nums){
        for(int i = 1; i < nums.size(); i ++){
            if(i % 2 == 0 && nums.get(i) > nums.get(i-1))continue;
            if(i % 2 == 1 && nums.get(i) < nums.get(i-1))continue;
            return false;
        }
        return true;
    }
}
