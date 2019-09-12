package algo.array;

import algo.linkedlist.ListNode;

import java.math.BigDecimal;
import java.util.*;

public class FindConnectArr {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        int length = input.length;
        if(k > length || k == 0){
            return result;
        }
        int l = 0, r = input.length-1;
        while (true){
            int pl = l, pr = r;
            int pri = input[l];
            while (l < r){
                while (l < r && input[r] > pri){
                    r--;
                }
                if(l < r) {
                    input[l] = input[r];
                }
                while (l< r && input[l] < pri){
                    l++;
                }
                if(l < r) {
                    input[r] = input[l];
                }
            }
            input[l] = pri;
            if(l == k-1)break;
            if(l > k){
                r = l-1;
                l = pl;
            }else{
                l = l+1;
                r = pr;
            }
        }
        for(int i = 0; i < k; i ++){
            result.add(input[i]);
        }

        return result;
    }


    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0)return 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int v : array){
            if(sum < 0){
                sum = 0;
            }
            sum += v;
            max = Math.max(max, sum);
        }
        return max;
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0)
            return 0;
        int count = 0;
        for(long i = 1; i <= n; i *= 10){
            long diviver = i * 10;
            count += (n / diviver) * i + Math.min(Math.max(n % diviver - i + 1, 0), i);
        }
        return count;
    }

    public String PrintMinNumber(int [] numbers) {
        List<String> arr = new ArrayList<>();
        for(int n : numbers)arr.add(String.valueOf(n));
        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s : arr)sb.append(s);
        return sb.toString();
    }

    public int FirstNotRepeatingChar(String str) {
        int[] arr = new int[256];
        for(char c : str.toCharArray()){
            arr[c - 'a'] ++;
        }
        for(int i = 0; i < str.length(); i ++){
            if(arr[str.charAt(i) - 'a'] == 1)return i;
        }
        return -1;
    }


    int sum = 0;
    public int InversePairs(int [] array) {
        mergeSort(array, 0, array.length-1);
        return sum;
    }

    public void mergeSort(int[] array, int l, int r){
        if(l >= r)return;
        int m = l + ((r-l)>>1);
        mergeSort(array, l, m);
        mergeSort(array, m+1, r);
        int[] newArray = new int[r-l+1];
        int l1 = l, l2 = m+1;
        int index = 0;
        while (index < newArray.length){
            if(l1 > m ||  (l2 <= r && array[l2] < array[l1])){
                newArray[index++] = array[l2++];
                sum = (sum +(m - l1 + 1))%1000000007;
            }else{
                newArray[index++] = array[l1++];
            }
        }
        for(int i = l; i <= r; i ++){
            array[i] = newArray[i-l];
        }
    }


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode current1 = pHead1;
        ListNode current2 = pHead2;


        HashMap<ListNode, Integer> hashMap = new HashMap<ListNode, Integer>();
        while (current1 != null) {
            hashMap.put(current1, null);
            current1 = current1.next;
        }
        while (current2 != null) {
            if (hashMap.containsKey(current2))
                return current2;
            current2 = current2.next;
        }

        return null;
    }

    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0)return 0;
        int l = binaryLeftSearch(array, 0, array.length-1, k);
        int r = binaryRightSearch(array, 0, array.length-1, k);
        if(l == -1 || r == -1)return 0;
        return (r-l+1);
    }

    public int binaryLeftSearch(int[] array, int l, int r, int k){
        if(l == r)return array[l] == k ? l : -1;
        int m = l + ((r-l)>>1);
        if(array[m] == k && (m == l || array[m] != array[m-1]))return m;
        if(array[m] < k){
            return binaryLeftSearch(array, m+1, r, k);
        }else{
            return binaryLeftSearch(array, l, m-1, k);
        }
    }
    public int binaryRightSearch(int[] array, int l, int r, int k){
        if(l == r)return array[l] == k ? l : -1;
        int m = l + ((r-l)>>1);
        if(array[m] == k && (m == r || array[m] != array[m+1]))return m;
        if(array[m] > k){
            return binaryRightSearch(array, l, m-1, k);
        }else{
            return binaryRightSearch(array, m+1, r, k);
        }
    }

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int res = 0;
        for(int v : array){
            res ^= v;
        }
        num1[0] = 0;
        int flag = 1;
        while((res & flag) == 0) flag <<= 1;
        for(int v : array){
            if((v & flag) == 0){
                num1[0] ^= v;
            }else{
                num2[0] ^= v;
            }
        }
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = array.length;
        int i = 0, j = n - 1;
        while(i < j){
            if(array[i] + array[j] == sum){
                res.add(array[i]);
                res.add(array[j]);
                break;
            }
            while(i < j && array[i] + array[j] > sum) --j;
            while(i < j && array[i] + array[j] < sum) ++i;
        }
        return res;
    }
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() == 0)return str;
        String s1 = str.substring(0, n);
        String s2 = str.substring(n+1);
        return s2 + s1;
    }


    public String ReverseSentence(String str) {
        int start = 0;
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        System.out.println(cur);
//        while(start < str.length()){
//            while (cur == str.length() || str.charAt(cur) != ' '){
//                cur++;
//            }
//            sb.append(reverse(str, start, cur));
//            start = cur+1;
//        }
        return sb.reverse().toString();
    }

    public String reverse(String str, int l, int r){
        StringBuilder sub = new StringBuilder(str.substring(l, r));
        return sub.reverse().toString();
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        ListNode p1 = pHead, p2 = pHead;
        while(true){
            p1 = p1.next;
            p2 = p2.next;
            if(p2 == null)return null;
            if(p2 == p1)break;
            p2 = p2.next;
            if(p2 == null)return null;
        }
        p2 = pHead;

        while(p1!=p2){

            p1=p1.next;

            p2=p2.next;

        }

        return p1;
    }

    public int minUnsatis(int[][] arrs, int n){
        if(arrs == null || arrs.length == 0)return -1;
        List<int[]> front = new ArrayList<>();
        List<int[]> tail = new ArrayList<>();
        for(int[] v : arrs){
            if(v[0] >= v[1]){
                front.add(v);
            }else{
                tail.add(v);
            }
        }
        Collections.sort(front, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        Collections.sort(tail, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int sum = 0;
        int index = 0;
        for(int[] v : front){
            sum += v[0] * index + v[1] * (n-1-index);
            index++;
        }
        index = 0;
        for(int[] v: tail){
            sum += v[1] * index + v[0] * (n-index-1);
            index++;
        }
        return sum;
    }

    public int getArrs(int s, int e, int k){
        int[] dp = new int[e+1];
        dp[0] = 1;
        dp[1] = k == 1 ? 2 : 1;
        int res = 0;
        for(int i = 2; i <= e; i ++){
            dp[i] = dp[i-1];
            if(i-k >= 0){
                dp[i] += dp[i-k];
            }
            if(i >= s)res += dp[i];
        }
        return res;
    }


    public int maxProduct(int[] nums) {
        int cur = 1;
        int max = Integer.MIN_VALUE;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < nums.length; i ++){
            max = Math.max(max, nums[i]);
            cur *= nums[i];
            if(cur > 0){
                max = Math.max(max, cur);
            }else if(cur < 0){
                if(!queue.isEmpty()) {
                    max = Math.max(max, cur / queue.peek());
                }
                queue.add(cur);
            }else{
                max = Math.max(max, 0);
                cur = 1;
                queue.clear();
            }
        }
        return max;
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res);
        return res;
    }
    public void dfs(int[] nums, int index, List<List<Integer>> res){
        if(index == nums.length){
            List<Integer> cur = new ArrayList<>();
            for(int v : nums)cur.add(v);
            res.add(cur);
            return;
        }
        for(int i = index; i < nums.length; i ++){
            swap(nums, index, i);
            dfs(nums, index+1, res);
            swap(nums, index, i);
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int findKthNumber(int n, int k) {
        int cur = 1;
        k -= 1;
        while (k > 0){
            int step = calStep(n, cur, cur+1);
            if(step <= k){
                k -= step;
                cur += 1;
            }else{
                k -= 1;
                cur *= 10;
            }
        }
        return cur;
    }
    public int calStep(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }


    public String solveEquation(String equation) {

        String[] strs = equation.split("=");
        int[] constants = new int[2];
        int[] coes = new int[2];
        for (int i = 0; i < strs.length; i++) {
            int coe = 0;
            int sign = 1;
            Integer curNum = null;
            int constant = 0;

            for (char ch : strs[i].toCharArray()) {
                if (Character.isDigit(ch)) {
                    if (curNum == null) {
                        curNum = ch - '0';
                    } else {
                        curNum = curNum * 10 + (ch - '0');
                    }
                } else if (ch == '+' || ch == '-') {
                    if (curNum != null) {
                        constant += sign * curNum;
                    }
                    curNum = null;
                    sign = (ch == '+' ? 1 : -1);
                } else if (ch == 'x') {
                    if (curNum == null) {
                        coe += sign;
                    } else {
                        coe += sign * curNum;
                    }
                    curNum = null;
                }
            }

            if (Character.isDigit(strs[i].charAt(strs[i].length() - 1))) {
                if (curNum != null) {
                    constant += sign * curNum;
                }

            } else if (strs[i].charAt(strs[i].length() - 1) == 'x') {
                if (curNum != null) {
                    coe += sign * curNum;
                }

            }

            constants[i] = constant;
            coes[i] = coe;
        }

        int resCoe = coes[0] - coes[1];
        int resConst = constants[1] - constants[0];

        if (resCoe == 0) {
            if (resConst == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }

        return "x=" + resConst / resCoe;
    }


    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<Integer> total = new ArrayList<>();
        dfs(price,special,needs,0,total);
        Collections.sort(total);
        if(total.size() > 0){
            return total.get(0);
        }
        return 0;
    }

    private static void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int totalPrice,
                            List<Integer> total) {
        if (isEmpty(needs)) {
            total.add(totalPrice);
            return;
        }

        for (List<Integer> list : special) {
            Integer tmp = isCanDo(list, needs);
            if (tmp != 0) {
                dfs(price, special, needs, totalPrice + tmp, total);
                revertDo(list,needs);
            }
        }
        List<Integer> revert = new ArrayList<>(needs);
        int tmp = single(price, needs);
        dfs(price, special, needs, totalPrice + tmp, total);
        needs.clear();
        needs.addAll(revert);
    }

    private static void revertDo(List<Integer> list, List<Integer> needs) {
        for(int i = 0;i < needs.size();i++){
            needs.set(i,needs.get(i)+list.get(i));
        }
    }

    private static int single(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for(int i = 0;i < needs.size();i++){
            total += needs.get(i)*price.get(i);
            needs.set(i,0);
        }
        return total;
    }

    private static int isCanDo(List<Integer> list, List<Integer> needs) {
        List<Integer> temp = new ArrayList<>(needs);
        for(int i = 0;i < needs.size();i++){
            int count = temp.get(i) - list.get(i);
            if(count >= 0){
                temp.set(i,count);
            }else {
                return 0;
            }
        }
        needs.clear();
        needs.addAll(temp);
        return list.get(list.size()-1);
    }

    private static boolean isEmpty(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != 0)
                return false;
        }
        return true;
    }

    static class MagicDictionary {

        class Node {
            Node[] next = new Node[26];
            boolean isWord;
        }

        Node root;
        /** Initialize your data structure here. */
        public MagicDictionary() {
            root = new Node();
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            Node tmp;
            for (int i = 0; i < dict.length; i++) {
                tmp = root;
                for (int j = 0; j < dict[i].length(); j++) {
                    int k = dict[i].charAt(j)-'a';
                    if (tmp.next[k] == null) tmp.next[k] = new Node();
                    tmp = tmp.next[k];
                }
                tmp.isWord = true;
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            return helper(word, 0, root, false);
        }

        public boolean helper(String word, int start, Node n, boolean flag) {
            if (start == word.length()) return flag && n.isWord;
            if (flag) {
                if (n.next[word.charAt(start)-'a'] != null) {
                    return helper(word, start+1, n.next[word.charAt(start)-'a'], true);
                }
                return false;
            } else {
                for (int i = 0; i < 26; i++) {
                    if (n.next[i] == null) continue;
                    if (word.charAt(start)-'a' == i) {
                        if (helper(word, start+1, n.next[i], false)) return true;
                    } else {
                        if (helper(word, start+1, n.next[i], true)) return true;
                    }
                }
                return false;
            }
        }
    }


    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums.length == 0)return 0;
        int res = 0;
        int s = 0, e = 0;
        long total = 1;
        while (s < nums.length){
            while (e < nums.length){
                if(total * nums[e] >= k)break;
                total *= nums[e];
                e++;
            }
            res += (e - s);
            if(e == s){
                s ++;
                e = s;
            }else{
                total /= nums[s];
                s ++;
            }
        }
        return res;
    }


    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i = 1; i <= s1.length(); i ++){
            dp[i][0] = dp[i-1][0] + (int)(s1.charAt(i-1));
        }
        for(int i = 1; i <= s2.length(); i ++){
            dp[0][i] = dp[0][i-1] + (int)(s2.charAt(i-1));
        }
        for(int i = 0; i < s1.length(); i ++){
            for(int j = 0; j < s2.length(); j ++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }else{
                    dp[i+1][j+1] = Math.min(dp[i+1][j] + (int)(s2.charAt(j)),
                            dp[i][j+1] + (int)(s1.charAt(i)));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args){
        int[] arr = {1,2,3};
        int[] v1 = new int[1];
        new FindConnectArr().numSubarrayProductLessThanK(arr, 0);
    }
}
