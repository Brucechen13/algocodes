package algo.array;

import algo.linkedlist.ListNode;

import java.util.*;

public class MyCalendar {

    TreeSet<Integer> startSet = new TreeSet<>();
    Map<Integer, Integer> map = new HashMap<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        Integer pre1 = startSet.floor(start);
        Integer pre2 = startSet.floor(end-1);
        System.out.println(pre1 + " " + pre2);
        if(pre1 != null && map.get(pre1) > start){
            return false;
        }
        if(pre2 != null && pre2 > start){
            return false;
        }
        startSet.add(start);
        map.put(start, end);
        return true;
    }


    public ListNode[] splitListToParts(ListNode root, int k) {
        int count = 0;
        ListNode cur = root;
        while (cur != null){
            count ++;
            cur = cur.next;
        }
        int d = count / k;
        int l = count % k;
        ListNode[] res = new ListNode[k];
        cur = root;
        int index = 0;
        while (cur != null){
            int len = d;
            if(index < l){
                len += 1;
            }
            res[index] = cur;
            ListNode pre = cur;
            for(int i = 0; i < len; i ++){
                pre = cur;
                cur = cur.next;
            }
            pre.next = null;
        }
        return res;
    }

    public static void main(String[] args){
        int[] vals = {3,3,3,4,2};
        System.out.println(new MyCalendar().deleteAndEarn(vals));
    }


    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> vals = new ArrayList<>();
        for(int n : nums){
            if(!count.containsKey(n)){
                count.put(n, 0);
                vals.add(n);
            }
            count.put(n, count.get(n) + 1);
        }
        Collections.sort(vals);
        int[][] dp = new int[vals.size()+1][2];
        for(int i = 0; i < vals.size(); i ++){
            dp[i+1][0] = Math.max(dp[i][0], dp[i][1]);
            int sum = vals.get(i) * count.get(vals.get(i));
            if(i == 0 || vals.get(i) != (vals.get(i-1)+1)){
                dp[i+1][1] = dp[i+1][0]
                        + sum;
            }else{
                dp[i+1][1] = Math.max(dp[i+1][0], dp[i][0] + sum);
            }
        }
        return Math.max(dp[vals.size()][0], dp[vals.size()][1]);
    }

    public int monotoneIncreasingDigits(int N) {
        char[] nums = String.valueOf(N).toCharArray();
        int len = nums.length;
        for(int i=len-2; i>=0; i--){
            if(nums[i]-'0'>nums[i+1]-'0'){
                nums[i] = (char)(nums[i] - 1);
                nums[i+1] = '9';
                for(int j=i+2; j<len; j++){
                    if(nums[j]!='9'){
                        nums[j] = '9';
                    }
                }
            }
        }
        return Integer.parseInt(new String(nums));
    }


    public String reorganizeString(String S) {
        
    }
}
