package algo.search;
import java.util.*;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = search(arr, x, 0, arr.length);
        List<Integer> res = new ArrayList<>(k);
        int left = index-1, right = index;
        while (left >= 0 || right < arr.length){
            if(left < 0){
                res.add(arr[right++]);
            }else if(right >= arr.length){
                res.add(arr[left--]);
            }else{
                if(x-arr[left] > arr[right]-x){
                    res.add(arr[right++]);
                }else{
                    res.add(arr[left--]);
                }
            }
            if(res.size() == k){
                break;
            }
        }
        Collections.sort(res);
        return res;
    }
    public int search(int[] arr, int x, int start, int end){
        if(start+1 >= end){
            return start;
        }
        int mid = (start+end)/2;
        if(arr[mid] > x){
            return search(arr, x, start, mid);
        }else if(arr[mid] < x){
            return search(arr, x, mid+1, end);
        }else{
            return mid;
        }
    }

    public static void main(String[] args){
        System.out.println(new FindKClosestElements().findClosestElements(new int[]{0,0,3,3,3,7,7,7,7,8}, 3, 5));
    }
}
