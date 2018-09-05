package algo.other;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMedian {

    class SegNode{
        int left;
        int right;
        int count;
        SegNode leftNode;
        SegNode rightNode;
        public SegNode(int left, int right){
            this.left = left;
            this.right = right;
            this.count = 0;
        }
    }

    public SegNode buildSegTree(int left, int right){
        SegNode node = new SegNode(left, right);
        if(left == right)return node;
        int mid = left + (right-left)/2;
        node.leftNode = buildSegTree(left, mid);
        node.rightNode = buildSegTree(mid+1, right);
        return node;
    }

    public void addToNode(SegNode node, int val){
        if(node.left < val || node.right > val)return;
        node.count++;
        if(node.left == node.right)return;
        addToNode(node.leftNode, val);
        addToNode(node.rightNode, val);
    }

    public void removeFromNode(SegNode node, int val){
        if(node.left < val || node.right > val)return;
        node.count--;
        if(node.left == node.right)return;
        addToNode(node.leftNode, val);
        addToNode(node.rightNode, val);
    }

    public int searchVal(SegNode node, int k){
        if(node.left == node.right){
            if(k == 0)
                return node.left;
            else{
                System.out.println(node.left + " " + node.right + " " + k);
            }
        }
        if(node.leftNode.count < k){
            searchVal(node.rightNode, k - node.leftNode.count);
        }else{
            searchVal(node.leftNode, k);
        }
        return -1;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i ++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        double[] res = new double[nums.length - k+1];
        SegNode head = buildSegTree(min, max);
        for (int i = 0; i < k - 1; i ++){
            addToNode(head, nums[i]);
        }
        for(int i = k-1; i < nums.length; i ++){
            addToNode(head, nums[i]);
            int index = i - k + 1;
            if(k%2 == 0){
                res[index] = (searchVal(head, k/2) + searchVal(head, k/2 + 1))/2;
            }else{
                res[index] = searchVal(head, k/2);
            }
            removeFromNode(head, nums[index]);
        }
        return res;
    }



    public double[] medianSlidingWindow2(int[] nums, int k) {

        List<Integer> window = new ArrayList<Integer>();
        double []res = new double[nums.length-k+1];
        for(int i=0; i<nums.length; i++){
            if(i>=k) res[i-k] = (k%2==0?((double)window.get(k/2)+(double)window.get(k/2-1))/2:(double)window.get(k/2));
            window.add(find(window, nums[i]), nums[i]);
            if(i>=k) window.remove(find(window, nums[i-k]));
        }
        res[res.length-1] = (k%2==0?((double)window.get(k/2)+(double)window.get(k/2-1))/2:(double)window.get(k/2));
        return res;
    }

    public int find(List<Integer> list, int target){
        int start =0, end = list.size()-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(list.get(mid)<target){
                start = mid +1;
            }else if (list.get(mid)>target){
                end = mid -1;
            }else{
                return mid;
            }
        }
        return start;
    }
}
