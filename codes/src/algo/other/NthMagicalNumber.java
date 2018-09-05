package algo.other;

public class NthMagicalNumber {
    public int gcd(int x, int y) {
        while(y > 0){
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }

    public int lcm(int x, int y) {
        return x * y/gcd(x,y);
    }
    public int nthMagicalNumber(int N, int A, int B) {
        int val = lcm(A, B);
        int count = val/A + val/B - 1;
        int c = N/count;

        return leftVal(N - c*count, A, B, val/A*c, val/B*c);
    }

    public int leftVal(int left, int A, int B, int ca, int cb){
        long res = (long)A * ca;
        while (left -- > 0){
            long a1 = (long)A * (ca+1);
            long a2 = (long)B * (cb+1);
            if(a1 > a2){
                cb++;
                res = (long)B * cb;
            }else{
                ca ++;
                res = (long)A * ca;
            }
        }
        return (int)(res % (((int)1e9)+7));
    }

    public static void main(String[] args){
        System.out.print(new NthMagicalNumber().nthMagicalNumber(1000000000, 40000, 40000));
    }
}
