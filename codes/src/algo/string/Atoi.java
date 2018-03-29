package algo.string;

public class Atoi {
    public int myAtoi(String str) {
        if(str == null ||  str.length() <= 0){
            return 0;
        }
        int res = 0;
        long jinzhi = 1l;
        int start = 0;
        while (str.charAt(start) == ' '){
            start++;
        }
        boolean posi = true;
        boolean overnum = false;
        if(str.charAt(start) == '+' || str.charAt(start) == '-'){
            posi = str.charAt(start) == '+';
            start++;
        }
        for(int i = str.length()-1; i >=start ; i --){
            int num = str.charAt(i) - '0';
            if(num<0 ||  num >9){
                res = 0;
                jinzhi = 1;
                overnum = false;
                continue;
            }
            long lr = res + (long)jinzhi*num;
            if(lr > Integer.MAX_VALUE){
                overnum = true;
                res = 0;
            }else{
                res += jinzhi*num;
                jinzhi*=10;
            }
        }
        return overnum?(posi?Integer.MAX_VALUE:Integer.MIN_VALUE):(res*(posi?1:-1));
    }
}
