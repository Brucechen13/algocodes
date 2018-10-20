package algo.leetcode;

public class SmallestRotationHighestScore {
    public int bestRotation_bad(int[] A) {
        int maxSocre = Integer.MIN_VALUE, maxIndex = -1;
        for(int i = 0; i < A.length; i ++){
            int res = smallerK(A, i);
            System.out.println(res + " " + i);
            if(res > maxSocre){
                maxIndex = i;
                maxSocre = res;
            }
        }
        return maxIndex;
    }

    public int smallerK(int[] A, int K){
        int res = 0;
        for(int i = 0; i < K; i ++){
            if(A[i] <= (A.length - K) + i)res++;
        }
        for(int i = K; i < A.length; i ++){
            if(A[i] <= (i-K))res++;
        }
        return res;
    }

    public int bestRotation(int[] A) {
        int[] count = new int[A.length + 1];

        // 对于每个下标，找出这个数在什么范围的K旋转时会导致加分
        // 在区间的左右分别+1, -1
        int len = A.length;
        for(int i = 0; i < len; i ++){
            int num = A[i];
            int next = (i + len - num) % len;
            int cur = (i + 1) % len;
            if(num <= i){
                count[0] ++;
                count[next + 1] --;
                if(i != len - 1){
                    count[cur] ++;
                }
            }else{
                count[cur] ++;
                count[next + 1] --;
            }
        }
        int maxScore = count[0], maxIndex = 0;
        for(int i = 1; i < len; i ++){
            count[i] = count[i] + count[i-1];
            if(count[i] > maxScore){
                maxScore = count[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
