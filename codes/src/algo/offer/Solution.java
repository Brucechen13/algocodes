package algo.offer;

import algo.depthfirst.TreeNode;
import algo.linkedlist.ListNode;

import java.util.*;

public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0)return false;
        int i = 0;
        int j = array[0].length-1;

        while (i < array.length && j >= 0){
            if(array[i][j] == target){
                return true;
            }else if(array[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }

    public int minNumberInRotateArray(int [] array) {
        int low = 0 ; int high = array.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(array[mid] > array[high]){
                low = mid + 1;
            }else if(array[mid] == array[high]){
                high = high - 1;
            }else{
                high = mid;
            }
        }
        return array[low];
    }


    public int Fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        //底
        int[][] base = {{1,1},
                {1,0}};
        //求底为base矩阵的n-2次幂
        int[][] res = matrixPower(base, n - 2);
        //根据[f(n),f(n-1)] = [1,1] * {[1,1],[1,0]}^(n-2)，f(n)就是
        //1*res[0][0] + 1*res[1][0]
        return res[0][0] + res[1][0];
    }

    public int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        //先把res设为单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        } //单位矩阵乘任意矩阵都为原来的矩阵
        //用来保存每次的平方
        int[][] tmp = m;
        //p每循环一次右移一位
        for ( ; p != 0; p >>= 1) {
            //如果该位不为零，应该乘
            if ((p&1) != 0) {
                res = multiMatrix(res, tmp);
            }
            //每次保存一下平方的结果
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    //矩阵乘法
    public int[][] multiMatrix(int[][] m1,int[][] m2) {
        //参数判断什么的就不给了，如果矩阵是n*m和m*p,那结果是n*p
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null)return res;
        dfs(root, target, res, new ArrayList<>());
        Collections.sort(res, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size() - o1.size();
            }
        });
        return res;
    }

    public void dfs(TreeNode cur, int left, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> pres){
        if(cur.left == null && cur.right == null && left == 0){
            pres.add(cur.val);
            res.add(new ArrayList<>(pres));
            pres.remove(pres.size()-1);
            return;
        }
        if(cur.left != null){
            pres.add(cur.val);
            dfs(cur.left, left-cur.val, res, pres);
            pres.remove(pres.size()-1);
        }
        if(cur.right != null){
            pres.add(cur.val);
            dfs(cur.right, left-cur.val, res, pres);
            pres.remove(pres.size()-1);
        }

    }


    public ArrayList<String> Permutation(String str){

        ArrayList<String> list = new ArrayList<String>();
        if(str!=null && str.length()>0){
            PermutationHelper(str.toCharArray(),0,list);
            Collections.sort(list);
        }
        return list;
    }
    private void PermutationHelper(char[] chars,int i,ArrayList<String> list){
        if(i == chars.length-1){
            list.add(String.valueOf(chars));
        }else{
            Set<Character> charSet = new HashSet<Character>();
            for(int j=i;j<chars.length;++j){
                if(j==i || !charSet.contains(chars[j])){
                    charSet.add(chars[j]);
                    swap(chars,i,j);
                    PermutationHelper(chars,i+1,list);
                    swap(chars,j,i);
                }
            }
        }
    }

    private void swap(char[] cs,int i,int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if(num == null || num.length == 0 || size == 0)return res;
        LinkedList<int[]> cache = new LinkedList<>();
        for(int i = 0; i < size-1; i ++){
            while (!cache.isEmpty() && cache.getLast()[1] < num[i]){
                cache.pollLast();
            }
            cache.addLast(new int[]{i, num[i]});
        }
        for(int i = size-1; i < num.length; i ++){
            while (!cache.isEmpty() && cache.getLast()[1] < num[i]){
                cache.pollLast();
            }
            cache.add(new int[]{i, num[i]});
            res.add(cache.getFirst()[1]);
            if(i+1 - cache.getFirst()[0] >= size){
                cache.pollFirst();
            }
        }
        return res;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] pre = {2,3,4,2,6,2,5,1};
        int[] in = {1};

        System.out.println(solution.maxInWindows(pre, 3));
    }
}
