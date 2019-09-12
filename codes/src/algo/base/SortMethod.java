package algo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SortMethod {

    public boolean canCross(int[] stones) {
        if(stones[1]-stones[0]!=1)
            return false;

        for(int i=1; i<stones.length; i++){
            /**
             When the frog is subscripted with i-1, the maximum hop count of the next hop is i. This conclusion can be observed.
             Then if the distance between the stones[i] and the stones[i-1] is greater than i, it is impossible to jump.
             If there is no such code, it will exceed the time limit.
             青蛙在下标为 i-1 的位置时，其下一跳可能的最大跳数是 i，这个结论可以观察得出。
             那么如果第 stones[i] 个石头和第 stones[i-1] 个石头距离大于 i，就一定跳不过去。
             如果没有这段代码，将会时间超限。
             **/

            if(stones[i]-stones[i-1]>i)
                return false;
        }

        return fuc(stones, 0, 1);
    }

    // There may be three cases for each hop, that is, the hop count may be step+1, step, step-1.
    // Traverse all possible situations by deep search.
    // 每一跳都可能有三种情况，即跳数可能step+1,step,step-1。
    // 通过深度搜索遍历所有可能情况。
    public boolean fuc(int[] stones, int start, int step){
        if(start==stones.length-1)
            return true;

        if(step<=0||start<0)
            return false;

        boolean res = false;



        int loc = Arrays.binarySearch(stones, stones[start]+step+1);
        res = res || fuc(stones, loc, step+1);

        loc = Arrays.binarySearch(stones, stones[start]+step);
        res = res || fuc(stones, loc, step);
        loc = Arrays.binarySearch(stones, stones[start]+step-1);
        res = res || fuc(stones, loc, step-1);

        return res;
    }

    public void heapSort(int[] arrs){
        int n = arrs.length;
        for(int i = n/2-1; i >= 0; i --){
            adjustHeap(arrs, i, n);

        }

        for(int i = n-1; i >= 0; i --){
            swap(arrs, i, 0);
            adjustHeap(arrs, 0, i);
        }
    }

    public void adjustHeap(int[] arrs, int i, int len){
        int temp = arrs[i];
        for(int k = i*2+1; k < len; k = k*2+1){
            if(k+1 < len && arrs[k] < arrs[k+1]){
                k++;
            }
            if(arrs[k] > temp){
                arrs[i] = arrs[k];
                i = k;
            }else{
                break;
            }
        }
        arrs[i] = temp;
    }

    public void swap(int[] arrs, int i, int j){
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    public static void main(String[] args){
        int[] arrs = new int[]{0,1};
        SortMethod sortMethod = new SortMethod();
        sortMethod.canCross(arrs);
        Arrays.stream(arrs).forEach(System.out::println);
    }
}
