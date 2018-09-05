package algo.other;

import java.util.*;

public class CrackingTheSafe {

    public String crackSafe(int n, int k) {
        int kn = (int)Math.pow(k, n);
        int nn = (int)Math.pow(10, n);

        StringBuilder cur = new StringBuilder();
        for(int i = 0; i < n; i ++){
            cur.append("0");
        }
        List<Integer> cached = new ArrayList<>();
        cached.add(0);

        return dfs(cached, cur.toString(), k, kn, nn, 0);

    }

    public String dfs(List<Integer> cached, String s, int k, int kn, int nn, int pre){
        if(cached.size() == kn)return s;
        for(int i = 0; i < k; i ++){
            int cur = pre * 10 % nn + i;
            if(!cached.contains(cur)) {
                cached.add(cur);
                String res = dfs(cached, s + i, k, kn, nn, cur);
                if (res != null) return res;
                cached.remove((Integer) cur);
            }
        }
        return null;
    }


    public String crackSafe2(int n, int k) {
        int d = 1;
        for (int i = 0; i < n - 1; ++i, d *= k);
        boolean[] used = new boolean[d * k];
        char[] chrs = new char[d * k + n - 1];
        for (int i = 0, l = 0; i < used.length; ++i) {
            if (used[i]) continue;
            int j = i;
            while (!used[j]) {
                used[j] = true;
                int q = j / d;
                int r = j % d;
                chrs[l++] = (char) (q + '0');
                j = r * k + q;
            }
        }
        for (int i = 0; i < n - 1; ++i) chrs[used.length + i] = chrs[i];
        return String.valueOf(chrs);
    }


    public String crackSafe3(int n, int k) {
        // [0,1], _ _
        // 00, 01, 11, 10
        StringBuilder sb = new StringBuilder();
        if (n == 1) {
            for (int i = 0; i < k; i++) {
                sb.append(i);
            }
            return sb.toString();
        }

        Map<String,boolean[]> nums = new HashMap<>();
        listNums(n,k,nums,0,"");

        int maxSize = n+(int)Math.pow(k,n)-1;
        StringBuilder initialKey = new StringBuilder();
        for (int i = 0; i < n-1; i++) {
            initialKey.append("0");
        }
        int[] ans = new int[maxSize];
        composeShortest(nums, ans, n-1, initialKey.toString());

        for (int num : ans) {
            sb.append(num);
        }
        return sb.toString();
    }

    // Backtracking
    private void listNums(int n, int k, Map<String,boolean[]> nums, int cur, String key) {
        if (cur == n-1) {
            if (!nums.containsKey(key)) {
                nums.put(key, new boolean[k]);
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            listNums(n, k, nums, cur+1, key+i);
        }
    }

    // DFS
    private boolean composeShortest(Map<String,boolean[]> nums, int[] ans, int cur, String preKey) {
        if (cur >= ans.length) return true;
        boolean[] marked = nums.get(preKey);
        int used = 0;
        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) {
                used++;
                continue;
            }
            String nextKey = preKey.substring(1) + i;
            ans[cur] = i;
            marked[i] = true;
            if (composeShortest(nums, ans, cur+1, nextKey)) {
                return true;
            }
            marked[i] = false;
        }
        if (used == marked.length) return false;
        return false;
    }
}
