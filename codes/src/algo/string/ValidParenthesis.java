package algo.string;

import java.util.Stack;

public class ValidParenthesis {
    public boolean checkValidString2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int leftBound = 0;
        int rightBound = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBound++;
                rightBound++;
            } else if (c == ')') {
                leftBound = Math.max(0, leftBound - 1);
                rightBound--;
            } else {
                leftBound = Math.max(0, leftBound - 1);
                rightBound++;
            }
            if(rightBound < 0) {
                return false;
            }
        }
        return leftBound == 0;
    }
    public boolean checkValidString(String s) {
        int res = subCheck(s.toCharArray(), 0, 0);
        return res==0;
    }

    public int subCheck(char[] vals, int start, int cur){
        if(cur < 0){
            return -1;
        }
        for(int i = start; i < vals.length; i ++){
            if(vals[i] == '*'){
                //System.out.println(start + " " + cur);
                return subCheck(vals, i+1, cur+1)==0?0:
                        subCheck(vals, i+1, cur+0)==0?0:
                        subCheck(vals, i+1, cur-1)==0?0:1;
            }else {
                cur += (vals[i] == '(' ? 1 : -1);
            }
        }
        //System.out.println("ret" + start + " " + cur);
        return cur;
    }
    public static void main(String[] args){
        System.out.println(new ValidParenthesis().checkValidString("*((((*))"));
    }
}
