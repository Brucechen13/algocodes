package algo.linkedlist;

/**
 * Created by chenc on 2017/5/15.
 */
public class RemoveDuplicatesII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;//pre上一个不同节点
        ListNode start = null;
        ListNode end = null;
        while(head != null){
            if(start == null){
                start = head;
            }else if(head.val == start.val) {
                end = head;
            }else {
                if(end == null){
                    pre = start;
                    start = head;
                    end = null;
                }else{
                    pre.next = head;
                    start = head;
                    end = null;
                }
            }
            head = head.next;
        }
        if(end != null){
            pre.next = null;
        }
        return fakeHead.next;
    }
}
