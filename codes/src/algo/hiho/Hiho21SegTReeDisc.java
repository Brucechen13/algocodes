package algo.hiho;

import java.util.*;

public class Hiho21SegTReeDisc {
    private static int[] ans = new int[4000005];
    private static boolean[] visited = new boolean[4000005];

    private static void build(int l, int r, int rt){
        if(l+1 == r){
            ans[rt] = 0;
            return;
        }
        int mid = (l+r)>>1;
        build(l, mid, rt<<1);
        build(mid, r, rt<<1|1);
        ans[rt] = 0;
    }

    private static void update(int l, int r, int L, int R, int rt, int x){
        if(l >= R || r <= L)return;
        if(l >= L && r <= R){
            ans[rt] = x;
            return;
        }
        if(ans[rt] != 0){
            if(l+1 != r){
                ans[rt<<1] = ans[rt];
                ans[rt<<1|1] = ans[rt];
            }
            ans[rt] = 0;
        }
        int mid = (l+r)>>1;
        update(l, mid, L, R, rt<<1, x);
        update(mid, r, L, R, rt<<1|1, x);
    }

    private static int ans_num = 0;
    private static void query(int l, int r, int rt){
        if(ans[rt] != 0 && !visited[ans[rt]]){
            ans_num ++;
            visited[ans[rt]] = true;
            return;
        }
        if(l+1 != r && ans[rt] == 0){
            int mid = (l+r)>>1;
            query(l, mid, rt<<1);
            query(mid, r, rt<<1|1);
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] vals = new int[2*n];
        for(int i = 0; i < n; i ++){
            vals[i*2] = scanner.nextInt();
            vals[i*2+1] = scanner.nextInt();
        }
        Arrays.sort(vals);
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < 2*n; i ++){
            if(i!=0 && vals[i] == vals[i-1])continue;
            map.put(vals[i], count++);
        }
        build(0, count, 1);
        for(int i = 0; i < n; i ++){
            update(0, count, map.get(vals[i*2]), map.get(vals[i*2+1])+1, 1, i+1);
        }
        query(0, count, 1);
        System.out.println(ans_num);
    }
}
