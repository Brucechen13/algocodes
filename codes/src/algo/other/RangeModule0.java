package algo.other;

import java.util.ArrayList;
import java.util.List;

public class RangeModule0 {

    class RangeModule {

        class SegNode {
            int l;
            int r;
            boolean tracked;
            SegNode left;
            SegNode right;

            SegNode(int left, int right, boolean tracked) {
                this.l = left;
                this.r = right;
                this.tracked = tracked;
            }

            boolean setTracking(int ll, int rr, boolean tracking) {
                if (ll <= l && rr >= r) {
                    left = right = null;
                    return tracked = tracking;
                }
                int mid = l + (r - l) / 2;
                if (left == null || right == null) {
                    left = new SegNode(l, mid, tracked);
                    right = new SegNode(mid, r, tracked);
                }
                boolean ltracked = ll <= mid ? left.setTracking(ll, rr, tracking) : left.tracked;
                boolean rtracked = rr > mid ? right.setTracking(ll, rr, tracking) : right.tracked;
                return tracked = ltracked && rtracked;
            }

            boolean getTracking(int ll, int rr) {
                if (left == null && right == null) return tracked;
                if (ll <= l && rr >= r) return tracked;
                int mid = l + (r - l) / 2;
                boolean ltracked = ll >= mid || left.getTracking(ll, rr);
                boolean rtracked = rr <= mid || right.getTracking(ll, rr);
                return ltracked && rtracked;
            }
        }

        private SegNode root;

        public RangeModule() {
            root = new SegNode(0, (int)1e9, false);
        }

        public void addRange(int left, int right) {
            root.setTracking(left, right, true);
        }

        public boolean queryRange(int left, int right) {
            return root.getTracking(left, right);
        }

        public void removeRange(int left, int right) {
            root.setTracking(left, right, false);
        }
    }

}
