package algo.linkedlist;

/**
 * 24. Swap Nodes in Pairs
 * Created by chenc on 2017/5/18.
 */
public class SwapNodes {
    public ListNode swapPairs(ListNode head) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = fakeHead;
        while(head != null && head.next != null){
            cur.next = head.next;
            head.next = head.next.next;
            cur.next.next = head;
            cur = head;
            head = head.next;
        }
        cur.next = head;
        return fakeHead.next;
    }
}
