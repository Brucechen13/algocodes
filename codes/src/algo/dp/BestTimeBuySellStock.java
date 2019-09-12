package algo.dp;

import algo.depthfirst.TreeNode;

import java.util.*;

public class BestTimeBuySellStock {

    // buy once
    public int maxProfit(int[] prices) {
        int max = 0;
        int res = 0;
        for(int i = prices.length-1; i >= 0; i --){
            int p = prices[i];
            max = Math.max(max, p);
            res = Math.max(res, max - p);
        }
        return res;
    }

    // but many times
    public int maxProfit2(int[] prices) {
        int profit =0;
        for(int i=0;i+1<prices.length;i++){
            profit += (prices[i+1]-prices[i]>0)? prices[i+1]-prices[i]:0;
        }
        return profit;
    }

    // buy twice
    public int maxProfit3(int[] prices) {
        int[] buy = new int[2];
        int[] sell = new int[2];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int i = 0; i < prices.length; i++) {
            // buy first
            buy[0] = Math.max(buy[0], -prices[i]);
            sell[0] = Math.max(sell[0], buy[0] + prices[i]);

            // buy second
            buy[1] = Math.max(buy[1], sell[0] - prices[i]);
            sell[1] = Math.max(sell[1], buy[1] + prices[i]);
        }
        return sell[1];
    }

    // buy at most k times
    public int maxProfit2(int k, int[] prices) {
        int[] buy = new int[k+1];
        int[] sell = new int[k+1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int i = 0; i < prices.length; i++) {
            for(int j = 1; j <= k; j ++){
                buy[j] = Math.max(buy[j], sell[j-1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for(int i = 0; i < n; i ++){
            System.out.println(buy + " " + sell);
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        while (true){
            if(cur.val < val){
                if(cur.right == null){
                    cur.right = new TreeNode(val);
                    break;
                }
                cur = cur.right;
            }else{
                if(cur.left == null){
                    cur.left = new TreeNode(val);
                    break;
                }
                cur = cur.left;
            }
        }
        return root;
    }

    class UnionFind {
        int[] father;
        public int find(int x) {
            if(father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }
        public boolean union(int a, int b) {
            int rootA = find(a), rootB = find(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                return false; // not a edge
            }
            return true;
        }
        public UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        Set<String> visited = new HashSet<>();
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[0].length; j ++){
                if(grid[i][j] == 0 || visited.contains(i + " " + j))continue;
                max = Math.max(max, dfs(grid, i, j, visited));
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j, Set<String> visited){
        if(grid[i][j] == 0)return 0;
        int sum = 1;
        visited.add(i + " " + j);
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] dir : dirs){
            int ni = i + dir[0];
            int nj = j + dir[1];
            if(ni < 0 || nj < 0 || ni >= grid.length || nj >= grid[0].length)continue;
            if(grid[ni][nj] == 0 || visited.contains(ni + " " + nj))continue;
            sum += dfs(grid, ni, nj, visited);
        }
        return sum;
    }


    public List<String> topKFrequent(String[] words, int k) {
        Arrays.sort(words);
        String pre = null;
        int count = 0;
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for(int i = 0; i < words.length; i ++){
            String w = words[i];
            if(pre == null || !pre.equals(w)){
                if(pre != null){
                    if(!cache.containsKey(count)){
                        cache.put(count, new ArrayList<>());
                    }
                    cache.get(count).add(i-1);
                }
                pre = w;
                count=0;
            }
            count++;
        }
        if(!cache.containsKey(count)){
            cache.put(count, new ArrayList<>());
        }
        cache.get(count).add(words.length-1);
        Map<String, Integer> dict = new HashMap<>();
        for(int c : cache.keySet()){
            for(int idx : cache.getOrDefault(c, new ArrayList<>())){
                dict.put(words[idx], c);
            }
        }
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(dict.getOrDefault(o1, 0).equals(dict.getOrDefault(o2, 0))){
                    return o1.compareTo(o2);
                }else{
                    return dict.get(o2) - dict.get(o1);
                }
            }
        });
        queue.addAll(dict.keySet());
        List<String> res = new ArrayList<>();
        for(int i = 0; i < k; i ++){
            res.add(queue.poll());
        }
        return res;
    }

    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        double[][] dp = new double[N][N];
        for(int i = 0; i < N; i ++){
            Arrays.fill(dp[i], 1);
        }
        for(int ii = 0; ii < K; ii ++){
            double[][] next = new double[N][N];
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    for(int[] dir : dirs){
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if(ni >= 0 && ni < N && nj >= 0 && nj < N){
                            next[ni][nj] += dp[i][j]/8;
                        }
                    }
                }
            }
            dp = next;
        }
        return dp[r][c];
    }


    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] count = new int[nums.length];
        int[] len = new int[nums.length];
        Arrays.fill(count, 1);
        Arrays.fill(len, 1);
        int maxLength = 0, lisCount = 0;
        for(int i = 0; i < nums.length; i ++){
            for(int j = 0; j < i; j ++){
                if(nums[i] > nums[j]){
                    if (len[i] == len[j] + 1) {
                        count[i] += count[j];
                    } else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLength == len[i]) {
                lisCount += count[i];
            } else if (maxLength < len[i]) {
                maxLength = len[i];
                lisCount = count[i];
            }
        }
        return lisCount;
    }

    public static void main(String[] args){
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new BestTimeBuySellStock().topKFrequent(words, 2));
    }

}
