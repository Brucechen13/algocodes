package algo.matrics;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if(A > E){
            return computeArea(E, F, G, H, A, B, C, D);
        }
        int total = (C - A)*(D-B) + (G-E)*(H-F);
        if(C <= E || B >= H || F >= D){
            return total;
        }
        int leftX, leftY, rightX, rightY;
        leftX = E;
        rightX = Math.min(C, G);
        leftY = Math.max(B, F);
        rightY = Math.min(D, H);
        return total - (rightX-leftX)*(rightY-leftY);
    }
}
