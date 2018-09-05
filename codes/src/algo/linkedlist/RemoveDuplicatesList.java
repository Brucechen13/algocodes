package algo.linkedlist;

/**
 * 83. Remove Duplicates from Sorted List
 * Created by chenc on 2017/5/9.
 */
public class RemoveDuplicatesList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        ListNode preNode = null;
        while(temp != null){
            if(preNode== null || preNode.val != temp.val){
                preNode = temp;
                temp = temp.next;
            }else{
                preNode.next = temp.next;
                temp = temp.next;
            }
        }
        return head;
    }
}
