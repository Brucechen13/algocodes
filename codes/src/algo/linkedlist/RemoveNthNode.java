package algo.linkedlist;

/**
 * 19. Remove Nth Node From End of List
 * Created by chenc on 2017/5/16.
 */
public class RemoveNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode l1 = head;
        ListNode l2 = head;
        while(n-- > 0){
            l2 = l2.next;
        }
        if(l2 == null){
            return l1.next;
        }
        while(l2.next != null){
            l1 = l1.next;
            l2 = l2.next;
        }
        l1.next = l1.next.next;
        return head;
    }
}
