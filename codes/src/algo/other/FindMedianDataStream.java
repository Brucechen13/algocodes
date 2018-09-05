package algo.other;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianDataStream {
    class MedianFinder {

        private Queue<Integer> smallQue = new PriorityQueue<Integer>(100, Comparator.reverseOrder());
        private Queue<Integer> largeQue = new PriorityQueue<>();

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

        }

        public void addNum(int num) {
            if (smallQue.size() == 0) {
                smallQue.add(num);
                return;
            }

            if (smallQue.size() == largeQue.size()) {
                int tmp = largeQue.peek();
                if (tmp > num) {
                    smallQue.add(num);
                } else {
                    largeQue.poll();
                    largeQue.add(num);
                    smallQue.add(tmp);
                }
            } else {
                int tmp = smallQue.peek();
                if (tmp > num) {
                    smallQue.poll();
                    smallQue.add(num);
                    largeQue.add(tmp);
                } else {
                    largeQue.add(num);
                }
            }
        }

        public double findMedian() {
            double res = 0;
            if (smallQue.size() == largeQue.size()) {
                res = smallQue.peek() + (largeQue.peek() - smallQue.peek()) / 2.0;
                return res;
            } else {
                res = smallQue.peek();
            }
            return res;
        }
    }
}
