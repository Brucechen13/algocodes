package algo.linkedlist;

/**
 * 203. Remove Linked List Elements
 * Created by chenc on 2017/5/2.
 */
public class RemoveLinkedList {
    public ListNode removeElements(ListNode head, int val) {
        ListNode tmp = head;
        ListNode pre = null;
        while(tmp != null){
            if(tmp.val == val){
                if(pre != null){
                    pre.next = tmp.next;
                }else{
                    head = tmp.next;
                }
            }else{
                pre = tmp;
            }
            tmp = tmp.next;
        }
        return head;
    }
}
