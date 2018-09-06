package algo.other;

import java.lang.reflect.Array;
import java.util.*;

public class MaxPointsLine {
     class Point {
         int x;
         int y;
         Point() { x = 0; y = 0; }
         Point(int a, int b) { x = a; y = b; }
     }
    int maxPoints(Point[] points) {
        int result = 0;
        int len = points.length;
        for(int i = 0; i < len; i++){
            int samePoint = 1;
            Map<String, Integer> map = new HashMap<>();
            for(int j = i + 1; j < len; j++){
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    samePoint++;
                }
                else if(points[i].x == points[j].x){
                    String key = "0 0";
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                else{
                    String key = getLine(points[i], points[j]);
                    //double slope = 1.0 * (points[i].y - points[j].y) / (points[i].x - points[j].x);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
            int localMax = 0;
            for (String key:
                 map.keySet()) {
                localMax = Math.max(localMax, map.get(key));
            }
            localMax += samePoint;
            result = Math.max(result, localMax);
        }
        return result;
    }

    public int maxPoints2(Point[] points) {
        if(points.length==0) return 0;
        if(points.length<=2) return points.length;
        int result=0;
        for(int i=1;i<points.length;i++){
            int max=0;
            Point a=points[i], b=points[i-1];
            if(a.x==b.x && a.y==b.y){
                for(int j=0;j<points.length;j++){
                    if(a.x==points[j].x && a.y==points[j].y){
                        max++;
                    }
                }
            }else{
                long dx=a.x-b.x;
                long dy=a.y-b.y;
                for(int j=0;j<points.length;j++){
                    if(dx*(a.y-points[j].y)==dy*(a.x-points[j].x)){
                        max++;
                    }
                }
            }
            result=Math.max(result, max);
        }
        return result;

    }

    public String getLine(Point p1, Point p2){
        int x = p1.x - p2.x;
        int y = p1.y - p2.y;
        if(y < 0){
            y = y*-1;
            x = x*-1;
        }
        int gcd = gcd(x, y);
        return (x/gcd) + " " + (y/gcd);
    }

    public int gcd(int a, int b){
        while(b != 0)
        {
            int r = b;
            b = a%b;
            a = r;
        }
        return a;
    }
}
