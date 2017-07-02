package algo.array;

/**
 * Created by chenc on 2017/7/1.
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int[] bel = new int[nums.length];
        for(int i = 0;i < nums.length; i ++){
            int cur = nums[i];
            int len = 0;
            while(bel[cur] == 0){
                bel[cur] = -1;
                len++;
                cur = nums[cur];
            }
            System.out.println(len);
            bel[i] = len;
            cur = nums[nums[i]];
            while(bel[cur] == -1){
                bel[cur] = Math.max(--len, bel[cur]);
                cur = nums[cur];
            }
        }
        int max = -1;
        for (int val:
                bel) {
            max = Math.max(max, val);
        }
        return max;
    }

    //method2
    public int arrayNesting2(int[] a) {
        int maxsize = 0;
        for (int i = 0; i < a.length; i++) {
            int size = 0;
            for (int k = i; a[k] >= 0; size++) {
                int ak = a[k];
                a[k] = -1; // mark a[k] as visited;
                k = ak;
            }
            maxsize = Math.max(maxsize, size);
        }

        return maxsize;
    }
}
