package algo.linkedlist;

/**
 * 61. Rotate List
 * Created by chenc on 2017/5/14.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int len;
        for (len=0;fast.next!=null;len++)//Get the total length
            fast=fast.next;

        for (int j=len-k%len;j>0;j--) //Get the i-n%i th node
            slow=slow.next;

        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;

        return dummy.next;
    }
}
