package algo.leetcode;

public class ZumaGame {

    private int getChar(char c){
        return c == 'R'?1:c=='Y'?2:c == 'B'?3:c == 'G'?4:5;
    }


    class Node{
        int c;
        int count = 0;
        Node next;
    }

    public int findMinStep(String board, String hand) {
        if(board == null || hand == null || board.length() < 1 || hand.length() < 1)return -1;
        Node head = new Node();
        Node tmp = new Node();
        tmp.c =  getChar(board.charAt(0));
        tmp.count = 1;
        head.next = tmp;
        for(int i = 1; i < board.length(); i ++){
            int val = getChar(board.charAt(i));
            if(tmp.c == val){
                tmp.count++;
            }else{
                tmp.next = new Node();
                tmp = tmp.next;
                tmp.c =  getChar(board.charAt(i));
                tmp.count = 1;
            }
        }
        //remove 3+ cell
        Node pre = head;
        tmp = head.next;
        while (tmp.next != null){
            if(tmp.count > 2){
                pre.next = tmp.next;
                tmp = pre;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        int[] handNums = new int[5];
        for(int i = 0; i < hand.length(); i ++){
            int val = getChar(hand.charAt(i));
            handNums[val-1] ++;
        }
        int step = remove(head, handNums, 0);
        return step == Integer.MAX_VALUE ? -1 : step;
    }

    public int remove(Node head, int[] hand, int step){
        if(head.next == null){
            return step;
        }
        int min = Integer.MAX_VALUE;
        Node curNode = head.next;
        Node pre = head;
        Node pre2 = head;
        while (curNode != null) {
            if (curNode.count > 2) {
                System.out.print("error");
            } else if (curNode.count == 2 && hand[curNode.c - 1] > 0) {
                hand[curNode.c - 1]--;
                Node nextNode = curNode.next;
                int preCount = pre.count;
                while (nextNode != null && pre.c == nextNode.c) {
                    pre.count += nextNode.count;
                    nextNode = nextNode.next;
                }
                pre.next = nextNode;
                if (pre.count > 2) {
                    pre2.next = pre.next;
                }
                int val = remove(head, hand, step + 1);
                min = Math.min(min, val);
                pre2.next = pre;
                pre.next = curNode;
                pre.count = preCount;
                hand[curNode.c - 1]++;
            }else if (curNode.count == 1 && hand[curNode.c - 1] > 1){
                hand[curNode.c - 1]--;
                curNode.count ++;
                int val = remove(head, hand, step + 1);
                min = Math.min(min, val);
                curNode.count --;
                hand[curNode.c - 1]++;
            }
            pre2 = pre;
            pre = curNode;
            curNode = curNode.next;
        }
        return min;
    }

    public static void main(String[] args){
        System.out.print(new ZumaGame().findMinStep("RBYYBBRRB", "YRBGB"));
    }



    int MAXCOUNT = 6;   // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"

    public int findMinStep2(String board, String hand) {
        int[] handCount = new int[26];
        for (int i = 0; i < hand.length(); ++i) ++handCount[hand.charAt(i) - 'A'];
        int rs = helper(board + "#", handCount);  // append a "#" to avoid special process while j==board.length, make the code shorter.
        return rs == MAXCOUNT ? -1 : rs;
    }
    private int helper(String s, int[] h) {
        s = removeConsecutive(s);
        if (s.equals("#")) return 0;
        int  rs = MAXCOUNT, need = 0;
        for (int i = 0, j = 0 ; j < s.length(); ++j) {
            if (s.charAt(j) == s.charAt(i)) continue;
            need = 3 - (j - i);     //balls need to remove current consecutive balls.
            if (h[s.charAt(i) - 'A'] >= need) {
                h[s.charAt(i) - 'A'] -= need;
                rs = Math.min(rs, need + helper(s.substring(0, i) + s.substring(j), h));
                h[s.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return rs;
    }
    //remove consecutive balls longer than 3
    private String removeConsecutive(String board) {
        for (int i = 0, j = 0; j < board.length(); ++j) {
            if (board.charAt(j) == board.charAt(i)) continue;
            if (j - i >= 3) return removeConsecutive(board.substring(0, i) + board.substring(j));
            else i = j;
        }
        return board;
    }
}
