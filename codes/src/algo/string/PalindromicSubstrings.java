package algo.string;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int len = s.length();
        int count = 0;
        if(s == null || s.length() < 1){
            return 0;
        }
        for(int i = 0; i < len ;i ++){
            count += checkString(s.toCharArray(), i, i);//mid
            count += checkString(s.toCharArray(), i, i+1);//mid
        }
        //System.out.println(index);
        return count;
    }

    public int checkString(char[] s, int left, int right){
        int count = 0;
        while (left >= 0 && right < s.length && s[left] == s[right]){
            left --;right++;
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(new PalindromicSubstrings().countSubstrings("aaaaa"));
    }
}
