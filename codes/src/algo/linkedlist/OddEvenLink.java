package algo.linkedlist;

import algo.linkedlist.ListNode;

public class OddEvenLink {
    public OddEvenLink() {
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}