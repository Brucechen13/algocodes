package algo.linkedlist;

/**
 * Created by chenc on 2017/4/27.
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        if(node != null && node.next != null){
            node.val=node.next.val;
            node.next=node.next.next;
        }
    }
}
