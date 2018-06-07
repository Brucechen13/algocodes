package algo.other;

import java.util.*;

public class ReversePairs {
    class SegTreeNode{
        int left;
        int right;
        int count;
        SegTreeNode leftNode;
        SegTreeNode rightNode;
        public SegTreeNode(int l, int r){
            this.left = l;
            this.right = r;
        }
    }
    public SegTreeNode buildTree(int l, int r, Integer[] nums){
        SegTreeNode node = new SegTreeNode(nums[l], nums[r]);
        if(l == r)return node;
        int mid = l + (r-l)/2;
        node.leftNode = buildTree(l, mid, nums);
        node.rightNode = buildTree(mid+1, r, nums);
        return node;
    }

    public void updateNode(SegTreeNode node, int val){
        if(node.left > val || node.right < val)return;
        node.count ++;
        if(node.leftNode != null)updateNode(node.leftNode,  val);
        if(node.rightNode != null)updateNode(node.rightNode, val);
    }

    public int searchNode(SegTreeNode node, long val){
        if(node.right <= val)return 0;
        if(node.left > val)return node.count;
        else return searchNode(node.rightNode, val) + searchNode(node.leftNode, val);
    }

    public int reversePairs(int[] nums) {
        if(nums == null || nums.length < 1)return 0;
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortNums);
        List<Integer> values = new ArrayList<>();
        for(int i = 0; i < sortNums.length; i ++){
            if(i == sortNums.length-1 || sortNums[i] != sortNums[i+1]){
                values.add(sortNums[i]);
            }
        }
        Integer[] sortNums2 = (Integer[])values.toArray(new Integer[1]);
        SegTreeNode node = buildTree(0, sortNums2.length-1, sortNums2);
        int res = 0;
        for(int num : nums){
            res += searchNode(node, (long)(num)*2);
            updateNode(node, num);
        }
        return res;
    }




    static class SegmentNode {
        int l, r, count;
        SegmentNode lc, rc;

        SegmentNode(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public int reversePairs2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        TreeSet<Long> set = new TreeSet<>();
        for (int num : nums) {
            set.add((long) num);
        }
        long[] sortNums = new long[set.size()];
        HashMap<Long, Integer> idxMap = new HashMap<>();
        int idx = 0;
        for (long num : set) {
            sortNums[idx] = num;
            idxMap.put(num, idx++);
        }
        SegmentNode root = buildTree(0, idx - 1);

        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            long num = nums[i] * 2L + 1;
            int index = Arrays.binarySearch(sortNums, num);
            if (index < 0) {
                index = -(index + 1);
            }
            result += getCount(index, idxMap.size() - 1, root);
            update(idxMap.get((long) nums[i]), root);
        }
        return result;
    }

    private SegmentNode buildTree(int l, int r) {
        if (l == r) {
            return new SegmentNode(l, r);
        }
        SegmentNode cur = new SegmentNode(l, r);
        int mid = l + (r - l) / 2;
        cur.lc = buildTree(l, mid);
        cur.rc = buildTree(mid + 1, r);
        return cur;
    }

    private int getCount(int l, int r, SegmentNode node) {
        if (node == null || node.l > r || node.r < l) {
            return 0;
        }
        if (l == node.l && r == node.r) {
            return node.count;
        }
        int mid = node.l + (node.r - node.l) / 2;
        if (mid >= r) {
            return getCount(l, r, node.lc);
        } else if (mid < l) {
            return getCount(l, r, node.rc);
        } else {
            return getCount(l, mid, node.lc) + getCount(mid + 1, r, node.rc);
        }
    }

    private void update(int idx, SegmentNode node) {
        if (node.l <= idx && node.r >= idx) {
            node.count++;
        } else {
            return;
        }
        if (node.l == node.r) {
            return;
        }
        update(idx, node.lc);
        update(idx, node.rc);
    }
}
