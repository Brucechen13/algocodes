package algo.linkedlist;

/**
 * 206. Reverse Linked List
 * Created by chenc on 2017/5/2.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while(head != null){
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
