package algo.linkedlist;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ReverseNodeskGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode fakeNode = new ListNode(0);
        fakeNode.next = head;
        int size = 0;
        ListNode cur = head;
        while (cur != null){
            size ++;
            cur = cur.next;
        }
        if(head == null || size < k || k <= 1)return head;
        int count = 0;
        cur = head;
        ListNode last = fakeNode;
        while (count+k <= size){
            ListNode pre = null;
            ListNode last2 = cur;
            for(int i = 0; i < k; i ++){
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            if(last != null){
                last.next = pre;
            }
            last = last2;
            count += k;
        }
        if(cur != null){
            last.next = cur;
        }
        return fakeNode.next;
    }

}
