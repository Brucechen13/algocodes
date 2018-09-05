package algo.linkedlist;

import java.util.Stack;

/**
 * 234. Palindrome Linked List
 * Created by chenc on 2017/5/1.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        //翻转前半链表，开始比较
        ListNode headFirst = head;
        ListNode headDouble = head;
        ListNode headMid = head.next;
        ListNode headPre = head;
        while (headDouble.next != null && headDouble.next.next != null){
            headDouble = headDouble.next.next;
            headPre = headFirst;
            headFirst = headMid;
            headMid = headMid.next;
            headFirst.next = headPre;
        }
        if(headDouble.next == null){
            headFirst = headFirst.next;
        }
        while(headMid != null){
            if(headMid.val != headFirst.val){
                return false;
            }
            headMid = headMid.next;
            headFirst = headFirst.next;
        }
        return true;
    }
}
