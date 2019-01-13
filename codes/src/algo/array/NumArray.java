package algo.array;

class NumArray {

    private int[] sum;
    private int len;

    public NumArray(int[] nums) {
        len = nums.length;
        if(len == 0)return;
        sum = new int[1<<Math.min(len, 20)];
        build(nums, 0, len-1, 1);
    }

    private int build(int[] nums, int l, int r, int index){
        if(l == r){
            sum[index] = nums[l];
            return nums[l];
        }
        int mid = l + ((r - l)>>1);
        System.out.println(l + " " + r + " " + mid);
        sum[index] = build(nums, l, mid, index<<1) + build(nums, mid+1, r, (index<<1)+1);
        return sum[index];
    }

    public void update(int i, int val) {
        if(len == 0)return;
        update(0, len-1, 1, i, val);
    }

    private void update(int l, int r, int index, int i, int val){
        if(l > i)return;
        if(i > r)return;
        if(l == r)sum[index] = val;
        else{
            int mid = l + ((r-l)>>1);
            //System.out.println(l + " " + mid);
            update(l, mid, index<<1, i, val);
            update(mid+1, r, (index<<1)+1, i, val);
            sum[index] = sum[index<<1] + sum[(index<<1)+1];
        }
    }

    public int sumRange(int i, int j) {
        if(len == 0)return -1;
        return query(0, len-1, 1, i, j);
    }

    private int query(int l, int r, int index, int i, int j){
        if(i <= l && j >= r)return sum[index];
        if(l > j)return 0;
        if(i > r)return 0;
        int mid = l + ((r-l)>>1);
        return  query(l, mid, index<<1, i, j) + query(mid+1, r, (index<<1)+1, i, j);
    }

    public static void main(String[] args){
        int[] a = {-28,-39,53,65,11,-56,-65,-39,-43,97};
        NumArray array = new NumArray(a);
        System.out.println(array.sumRange(5, 6));
        array.update(9, 27);
        System.out.println(array.sumRange(2, 3));
        System.out.println(array.sumRange(6, 7));
        array.update(1, -82);
        array.update(3, -72);
        System.out.println(array.sumRange(3, 7));
        System.out.println(array.sumRange(1, 8));
        array.update(1, 9);
    }
}