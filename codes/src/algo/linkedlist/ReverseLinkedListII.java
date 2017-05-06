package algo.linkedlist;

/**
 * 92. Reverse Linked List II
 * Created by chenc on 2017/5/6.
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode before = fakeHead;
        ListNode after = null;
        ListNode pre = null;
        ListNode temp = head;
        n = n- m;
        while(m-- > 1) {
            before = temp;
            temp = temp.next;
        }
        after = temp;
        //after对应开始转换的节点,before对应转换节点之前的节点
        while(n-- > 0){
            ListNode tmp = temp.next;
            temp.next = pre;
            pre = temp;
            temp = tmp;
        }
        if(temp != after){
            //temp对应需要转换的最后一个节点
            after.next = temp.next;
            temp.next = pre;
            before.next = temp;
        }
        return fakeHead.next;
    }
}
