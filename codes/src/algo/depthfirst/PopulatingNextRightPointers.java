package algo.depthfirst;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class PopulatingNextRightPointers {
    public void connect(TreeLinkNode root) {
        if(root == null || root.left ==null){
            return ;
        }
        Queue<TreeLinkNode> nodes = new LinkedBlockingQueue<>();
        int n = 1;
        root.next = null;
        nodes.add(root);
        while (!nodes.isEmpty()){
            List<TreeLinkNode> tmps = new ArrayList<>();
            while (!nodes.isEmpty()){
                TreeLinkNode cur = nodes.poll();
                if(cur.right!=null) {
                    tmps.add(cur.right);
                }
                if(cur.left != null) {
                    tmps.add(cur.left);
                }
            }
            TreeLinkNode pre = tmps.get(0);
            pre.next = null;
            for (int i = tmps.size()-1; i > 0; i --){
                tmps.get(i).next = tmps.get(i-1);
            }
            boolean flag = true;
            for(int i = 0; i < tmps.size(); i ++){
                nodes.add(tmps.get(i));
                if(tmps.get(i).left != null || tmps.get(i).right != null)flag=false;
            }
            if(flag)break;
        }
    }

    public void connect2(TreeLinkNode root) {
        if (root == null) return ;

        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            else { // root.next == null
                root.right.next = null;
            }
        }
        connect(root.left);
        connect(root.right);
    }

    public void connect22(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
    }

    public static void main(String[] args){
        TreeLinkNode node = new TreeLinkNode(1);
        TreeLinkNode left = new TreeLinkNode(2);
        TreeLinkNode right = new TreeLinkNode(3);
        node.left = left;node.right = right;
        left.left = new TreeLinkNode(4);
        left.right = new TreeLinkNode(5);
        TreeLinkNode right2 = new TreeLinkNode(7);
        right.right = right2;
        right2.left = new TreeLinkNode(8);
        right2.right = new TreeLinkNode(8);
        new PopulatingNextRightPointers().connect(node);
    }
}
