package algo.competition;

public class LeetNumbersAtMostN {
    public int atMostNGivenDigitSet(String[] D, int N) {
        String target = String.valueOf(N);
        int res = 0;
        for(String c : D){
            res += dfs(D, target, c.charAt(0), 0);
        }
        return res;
    }

    public int dfs(String[] D, String target, char cur, int index){
        int res = 0;
        int len = target.length();
        if(index >= len)return 0;
        if(cur < target.charAt(index)){
            while (len - index - 1 >= 0) {
                res += Math.pow(D.length, len - index - 1);
                index++;
            }
        }else if(cur > target.charAt(index)){
            while (len - index - 2 >= 0) {
                res += Math.pow(D.length, len - index - 2);
                index++;
            }
        }else{
            res += 1;
            for(String c : D){
                res += dfs(D, target, c.charAt(0), index+1);
            }
        }
        return res;
    }

    public boolean checkBigger(String origin, String target){
        if(origin.length() > target.length())return true;
        for(int i = 0; i < origin.length(); i ++){
            if(origin.charAt(i) > target.charAt(i))return true;
            else if(origin.charAt(i) < target.charAt(i))return false;
        }
        return false;
    }
    public static void main(String[] args){
        String[] D = {"3","4", "8"};// {"1","3","5","7"};
        System.out.println(new LeetNumbersAtMostN().atMostNGivenDigitSet(D, 4));
    }
}
