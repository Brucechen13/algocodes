package algo.array;

public class PartitionArrayDisjointIntervals {
    public int partitionDisjoint(int[] A) {
        int left = 0;
        int right = A.length-1;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        // 因为题目保证有解，所以不用判断异常情况
        while (left < right){
            max = Math.min(max, A[right]);
            min = Math.max(min, A[left]);
            A[right] = max;
            if(max >= min){
                right--;
            }else{
                right++;
                max = A[right];
                left++;
            }
        }
        return left+1;
    }

    public static void main(String[] args){
        int[] A = {32,57,24,19,0,24,49,67,87,87}; //{5,0,3,8,6};
        System.out.println(new PartitionArrayDisjointIntervals().partitionDisjoint(A));
    }
}
