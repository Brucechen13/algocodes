package algo.other;

<<<<<<< HEAD
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
=======
import java.util.Arrays;
>>>>>>> 4c166f8df32599bf860d275e92f930feb6b0eab1
import java.util.HashSet;
import java.util.Set;

public class CountRangeSum {
<<<<<<< HEAD
    class SegmentTree{
        public long min;
        public long max;
        public int count;
        public SegmentTree left;
        public SegmentTree right;

        public SegmentTree(long min, long max){
=======
    class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int count;
        long min;
        long max;
        public SegmentTreeNode(long min, long max) {
>>>>>>> 4c166f8df32599bf860d275e92f930feb6b0eab1
            this.min = min;
            this.max = max;
        }
    }
<<<<<<< HEAD

    public SegmentTree buildSegmentTree(Long[] values, int l, int r){
        SegmentTree root = new SegmentTree(values[l], values[r]);
        if(l < r) {
            int mid = (l + r) / 2;
            root.left = buildSegmentTree(values, l, mid);
            root.right = buildSegmentTree(values, mid+1, r);
        }
        return root;
    }

    public void updateSegmentTree(SegmentTree root, long val){
        if(root == null)return;
        if(root.min <= val && root.max >= val){
            root.count ++;
            updateSegmentTree(root.left, val);
            updateSegmentTree(root.right, val);
        }
    }

    public int countSegmentTree(SegmentTree root, long left, long right){
        if(root == null || root.max < left || root.min > right)return 0;
        if(root.min >= left && root.max <= right ){
            return root.count;
        }
        return countSegmentTree(root.left, left, right) + countSegmentTree(root.right, left, right);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length < 1)return 0;
        Set<Long> sums = new HashSet<>();
        long sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            sums.add(sum);
        }

        Long[] ss  = sums.toArray(new Long[1]);
        Arrays.sort(ss);
        SegmentTree root = buildSegmentTree(ss, 0, ss.length-1);

        int ans = 0;
        for(int i = nums.length-1; i >= 0; i --){
            updateSegmentTree(root, sum);
            sum -= nums[i];
            ans += countSegmentTree(root, sum+lower, sum+upper);
        }
        return ans;
    }




    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        long[] temp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[] count = new int[1];
        mergeSort(sum, 0, sum.length - 1, lower, upper, temp, count);
        return count[0];
    }

    public void mergeSort(long[] sum, int start, int end, int lower, int upper, long[] temp, int[] count) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(sum, start, mid, lower, upper, temp, count);
        mergeSort(sum, mid + 1, end, lower, upper, temp, count);
        merge(sum, start, mid, end, lower, upper, temp, count);
    }

    public void merge(long[] sum, int start, int mid, int end, int lower, int upper, long[] temp, int[] count) {
        int right = mid + 1;
        int low = mid + 1;
        int high = mid + 1;
        int index = start;

        for (int left = start; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) {
                ++low;
            }

            while (high <= end && sum[high] - sum[left] <= upper) {
                ++high;
            }

            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
            count[0] += high - low;
        }

        while (right <= end) {
            temp[index++] = sum[right++];
        }

        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
=======
    private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
        if(low > high) return null;
        SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
        if(low == high) return stn;
        int mid = (low + high)/2;
        stn.left = buildSegmentTree(valArr, low, mid);
        stn.right = buildSegmentTree(valArr, mid+1, high);
        return stn;
    }
    private void updateSegmentTree(SegmentTreeNode stn, Long val) {
        if(stn == null) return;
        if(val >= stn.min && val <= stn.max) {
            stn.count++;
            updateSegmentTree(stn.left, val);
            updateSegmentTree(stn.right, val);
        }
    }
    private int getCount(SegmentTreeNode stn, long min, long max) {
        if(stn == null) return 0;
        if(min > stn.max || max < stn.min) return 0;
        if(min <= stn.min && max >= stn.max) return stn.count;
        return getCount(stn.left, min, max) + getCount(stn.right, min, max);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums == null || nums.length == 0) return 0;
        int ans = 0;
        Set<Long> valSet = new HashSet<Long>();
        long sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += (long) nums[i];
            valSet.add(sum);
        }

        Long[] valArr = valSet.toArray(new Long[0]);

        Arrays.sort(valArr);
        SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length-1);

        for(int i = nums.length-1; i >=0; i--) {
            updateSegmentTree(root, sum);
            sum -= (long) nums[i];
            ans += getCount(root, (long)lower+sum, (long)upper+sum);
        }


        ans += nums[0] >= lower && nums[0] <= upper ? 1 : 0;
        sum = nums[0];
        for(int i = 1; i<nums.length; i++) {
            updateSegmentTree(root, sum);
            sum += nums[i];
            ans += sum >= lower && sum <= upper ? 1 : 0;
            ans += getCount(root, (long)sum-upper, (long)sum-lower);
        }
        return ans;
>>>>>>> 4c166f8df32599bf860d275e92f930feb6b0eab1
    }
}
