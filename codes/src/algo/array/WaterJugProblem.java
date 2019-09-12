package algo.array;

import java.util.HashSet;
import java.util.Set;

public class WaterJugProblem {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) return false;
        if (z == x || z == y) return true;

        int a = Math.max(x,y);
        int b = Math.min(x,y);
        int gcd = findGcd(a,b);
        return ((y!= 0)  && z % y == 0) || ((x != 0) && z % x == 0) || ((x!= 0 && y != 0 && z % gcd == 0));
    }

    public static int findGcd(int a, int b){
        if (a == b) return a;
        int gcd = 1;
        for (int i = 2; i <= b; i++){
            while (a % i == 0 && b % i == 0){
                gcd *= i;
                a /= i;
                b /= i;
            }
        }
        return gcd;
    }
}
