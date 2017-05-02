package algo.linkedlist;

/**
 * 160. Intersection of Two Linked Lists
 * Created by chenc on 2017/5/2.
 * a better solution
 * ListNode cur1 = headA, cur2 = headB
 * while(cur1!=cur2){
 *     cur1 = cur1==null?headB:cur1.next;
 *     cur2 = cur2==null?headA:cur2.next;
 * }
 * return cur1;
 */
public class IntersectionTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int dividLen = listLen(headA) - listLen(headB);
        if(dividLen > 0){
            while(dividLen--!=0){
                headA = headA.next;
            }
        }else if(dividLen < 0){
            while(dividLen++!=0){
                headB = headB.next;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public int listLen(ListNode root){
        int len = 0;
        while(root != null){
            root = root.next;
            len++;
        }
        return len;
    }
}
