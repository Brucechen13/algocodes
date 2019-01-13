package algo.tree;

public class ConvertSortedListTree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        if(head.next == null){
            return new TreeNode(head.val);
        }

        ListNode pre, cur, tmp;
        pre = cur = tmp = head;
        while (tmp != null && tmp.next != null){
            pre = cur;
            cur = cur.next;
            tmp = tmp.next;
            if(tmp == null)break;
            tmp = tmp.next;
        }
        TreeNode node = new TreeNode(cur.val);
        pre.next = null;
        node.left = sortedListToBST(pre);
        node.right = sortedListToBST(cur.next);
        return node;
    }



    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }
}
