package algo.linkedlist;

/**
 * 25. Reverse Nodes in k-Group
 * Created by chenc on 2017/5/18.
 */
public class ReverseNodesk {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = fakeHead;
        while(head != null){
            ListNode next = null;//before-1,2,3,..k,-next
            int i = 0;
            for(i = 0; head != null && i < k; i ++){
                head = head.next;
                next = head;
            }
            if(i == k){
                head = cur.next;
                ListNode nextNode = head.next;
                while(nextNode != next) {
                    ListNode tmp = nextNode.next;
                    nextNode.next = head;
                    head = nextNode;
                    nextNode = tmp;
                }
                ListNode tmp = cur.next;
                cur.next = head;
                tmp.next = next;
                cur = tmp;
                head = next;
            }
        }
        return fakeHead.next;
    }
}
