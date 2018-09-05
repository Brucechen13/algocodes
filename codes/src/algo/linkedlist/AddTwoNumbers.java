package algo.linkedlist;

/**
 * Created by chenc on 2017/5/16.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int curSum = 0;
        ListNode head = l1;
        ListNode pre = null;
        while(l1!=null && l2 != null){
            int sum = l1.val + l2.val + curSum;
            l1.val = sum%10;
            curSum = sum/10;
            pre = l1;//cache l1 last node
            l1 = l1.next;l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + curSum;
            l1.val = sum%10;
            curSum = sum/10;
            pre = l1;
            l1 = l1.next;
        }
        while(l2 != null){
            int sum = l2.val + curSum;
            l2.val = sum%10;
            curSum = sum/10;
            pre.next = l2;
            pre = l2;
            l2 = l2.next;
        }
        if(curSum != 0){
            pre.next = new ListNode(curSum);
        }

        return head;

    }
}
