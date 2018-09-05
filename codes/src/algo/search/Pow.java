package algo.search;

public class Pow {
    public double myPow(double x, int n) {
        double ret = 1;
        int an = Math.abs(n);
        while (an != 0){
            if((an&1) == 1){
                ret *= x;
            }
            if(Math.abs(Double.MAX_VALUE/x) < Math.abs(x)){
                return 0;
            }
            x = x*x;
            an = an>>1;
        }
        return (n>0?ret:1/ret);
    }
}
