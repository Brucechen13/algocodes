package algo.other;

public class MaxChunksMakeSortedII {
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] leftMax = new int[len];
        int[] rightMin = new int[len];

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i ++){
            max = Math.max(max, arr[i]);
            leftMax[i] = max;
            rightMin[len-1-i] = min;
            min = Math.min(min, arr[len-1-i]);
        }
        int count = 0;

        for(int i = 0; i < len; i ++){
            if(arr[i] <= rightMin[i] && leftMax[i] <= rightMin[i]){
                if(arr[i] < rightMin[i] || arr[i] == leftMax[i])
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] arr = {0,3,0,3,2};
        System.out.println(new MaxChunksMakeSortedII().maxChunksToSorted(arr));
    }
}
