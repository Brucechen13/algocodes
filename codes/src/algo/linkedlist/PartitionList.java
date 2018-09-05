package algo.linkedlist;

import java.util.Optional;

/**
 * 86. Partition List
 * Created by chenc on 2017/5/9.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode fakeBefore = new ListNode(0);
        ListNode tempBefore = fakeBefore;
        ListNode fakeNext = new ListNode(0);
        ListNode tempNext = fakeNext;
        while(head!=null){
            if(head.val < x){
                tempBefore.next = head;
                tempBefore = head;
            }else{
                tempNext.next = head;
                tempNext = head;
            }
            head = head.next;
        }

        tempNext.next = null;
        tempBefore.next = fakeNext.next;
        return fakeBefore.next;
    }
}
