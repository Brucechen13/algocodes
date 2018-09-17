package algo.other;

public class MyCalendarThree {

    class SegNode {
        int count;
        int maxV;
        boolean isCover;
        SegNode left, right;

        public SegNode() {
        }
    }

    public void updateSegNode(SegNode node, int L, int R, int l, int r) {
        if (l > r) return;

        node.isCover = true;
        if (L == l && R == r) {
            node.count++;
            node.maxV++;
        } else {
            int M = L + (R - L) / 2;

            if (node.left == null)
                node.left = new SegNode();
            updateSegNode(node.left, L, M, l, Math.min(M, r));
            if (node.right == null)
                node.right = new SegNode();
            updateSegNode(node.right, M + 1, R, Math.max(M + 1, l), r);
        }
        node.maxV = node.count + Math.max(node.left.maxV, node.right.maxV);
    }

    private SegNode node;
    private int L = 0;
    private int R = (int) (1e9) + 1;

    public MyCalendarThree() {
        node = new SegNode();
    }

    public int book(int start, int end) {
        updateSegNode(node, L, R, start, end);
        return node.maxV;
    }

    public static void main(String[] args) {
        MyCalendarThree calendarIII = new MyCalendarThree();
        System.out.println(calendarIII.book(10, 30));
        System.out.println(calendarIII.book(50, 60));
    }
}
