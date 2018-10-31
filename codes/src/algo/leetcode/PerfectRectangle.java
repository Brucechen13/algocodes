package algo.leetcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles == null || rectangles[0].length < 1)return false;
        int w = rectangles[0].length;
        int h = rectangles.length;
        int leftX=Integer.MAX_VALUE, leftY=Integer.MAX_VALUE;
        int rightX=Integer.MIN_VALUE, rightY=Integer.MIN_VALUE;


        HashSet<String> set = new HashSet<String>();

        long zero = 0;
        for(int i = 0; i < h; i ++){
            leftX = Math.min(leftX, rectangles[i][0]);
            leftY = Math.min(leftY, rectangles[i][1]);
            rightX = Math.max(rightX, rectangles[i][2]);
            rightY = Math.max(rightY, rectangles[i][3]);
            zero += (long)(rectangles[i][2] - rectangles[i][0])*(rectangles[i][3]-rectangles[i][1]);

            int[] rect = rectangles[i];
            String s1 = rect[0] + " " + rect[1];
            String s2 = rect[0] + " " + rect[3];
            String s3 = rect[2] + " " + rect[3];
            String s4 = rect[2] + " " + rect[1];

            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }
        if (!set.contains(leftX + " " + leftY) || !set.contains(leftX + " " + rightY)
                || !set.contains(rightX + " " + leftY) || !set.contains(rightX + " " + rightY)
                || set.size() != 4) return false;

        return zero == (rightX-leftX)*(rightY-leftY);
    }


    public class Event implements Comparable<Event> {
        int time;
        int[] rect;

        public Event(int time, int[] rect) {
            this.time = time;
            this.rect = rect;
        }

        public int compareTo(Event that) {
            if (this.time != that.time) return this.time - that.time;
            else return this.rect[0] - that.rect[0];
        }
    }

    // 扫描线算法
    public boolean isRectangleCover2(int[][] rectangles) {
        PriorityQueue<Event> pq = new PriorityQueue<Event> ();
        // border of y-intervals
        int[] border= {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int[] rect : rectangles) {
            Event e1 = new Event(rect[0], rect);
            Event e2 = new Event(rect[2], rect);
            pq.add(e1);
            pq.add(e2);
            if (rect[1] < border[0]) border[0] = rect[1];
            if (rect[3] > border[1]) border[1] = rect[3];
        }
        TreeSet<int[]> set = new TreeSet<int[]> (new Comparator<int[]>() {
            @Override
            // if two y-intervals intersects, return 0
            public int compare (int[] rect1, int[] rect2) {
                if (rect1[3] <= rect2[1]) return -1;
                else if (rect2[3] <= rect1[1]) return 1;
                else return 0;
            }
        });
        int yRange = 0;
        while (!pq.isEmpty()) {
            int time = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == time) {
                Event e = pq.poll();
                int[] rect = e.rect;
                if (time == rect[2]) {
                    set.remove(rect);
                    yRange -= rect[3] - rect[1];
                } else {
                    if (!set.add(rect)) return false;
                    yRange += rect[3] - rect[1];
                }
            }
            // check intervals' range
            if (!pq.isEmpty() && yRange != border[1] - border[0]) {
                return false;
                //if (set.isEmpty()) return false;
                //if (yRange != border[1] - border[0]) return false;
            }
        }
        return true;
    }
}
