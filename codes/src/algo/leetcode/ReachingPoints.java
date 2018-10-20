package algo.leetcode;

public class ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        return check(sx, sy, tx, ty);
    }

    public boolean check(int sx, int sy, int tx, int ty) {
        if (tx < sx || ty < sy) {
            return false;
        }
        if (sx == tx && sy == ty) {
            return true;
        }
        if (tx > ty) {
            if (sy == ty) {
                if ((tx - sx) % sy == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return check(sx, sy, tx - ty, ty);
        } else {
            if (sx == tx) {
                if ((ty - sy) % sx == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return check(sx, sy, tx, ty - tx);
        }
    }
}
