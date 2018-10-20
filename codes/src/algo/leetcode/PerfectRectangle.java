package algo.leetcode;

import java.util.HashSet;

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
        if (!set.contains(leftX + " " + leftY) || !set.contains(leftX + " " + rightY) || !set.contains(rightX + " " + leftY) || !set.contains(rightX + " " + rightY) || set.size() != 4) return false;

        return zero == (rightX-leftX)*(rightY-leftY);
    }
}
