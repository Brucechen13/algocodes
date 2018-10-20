package algo.leetcode;

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        return isSubScramble(s1, s2);
    }

    public boolean isSubScramble(String s1, String s2){
        if(s1.length() != s2.length())return false;
        if(s1.length() <= 2){
            return s1.equals(s2) || s1.equals(new StringBuilder(s2).reverse().toString());
        }
        int mid = s1.length()/2;
        if(s1.length() % 2 == 0)
            return isSubEqual(s1.substring(0, mid), s1.substring(mid),
                s2.substring(0, mid), s2.substring(mid));
        else
            return isSubEqual(s1.substring(0, mid), s1.substring(mid),
                s2.substring(0, mid), s2.substring(mid)) || isSubEqual(s1.substring(0, mid+1),
                s1.substring(mid+1), s2.substring(0, mid+1), s2.substring(mid+1)) ||
                    isSubEqual(s1.substring(0, mid+1),s1.substring(mid+1),
                            s2.substring(0, mid), s2.substring(mid))||
                    isSubEqual(s1.substring(0, mid),s1.substring(mid),
                            s2.substring(0, mid+1), s2.substring(mid+1));
    }

    public boolean isSubEqual(String left1, String right1, String left2, String right2){
        return (isSubScramble(left1, left2) && isSubScramble(right1, right2)) ||
                (isSubScramble(left1, right2) && isSubScramble(right1, left2));
    }



    public boolean isScramble2(String s1, String s2) {
        if (s1.equals(s2)) return true;

        int[] letters = new int[26];
        for (int i=0; i<s1.length(); i++) {
            letters[s1.charAt(i)-'a']++;
            letters[s2.charAt(i)-'a']--;
        }
        for (int i=0; i<26; i++) if (letters[i]!=0) return false;

        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
                    && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
        }
        return false;
    }


    public boolean isScramble3(String s1, String s2) {
        if (s1 == null && s2 == null) return true ;
        if (s1 == null || s2 == null) return false ;
        if (s1.length() != s2.length()) return false ;
        if (s1.equals(s2)) return true ;
        int N = s1.length () ;
        long a = 1, b = 1 , c = 1 ;
        for (int i = 0 ; i < N ; ++ i) {
            if ( i > 0 && a == c &&
                    isScramble(s1.substring(0,i), s2.substring(0,i) )
                    && isScramble(s1.substring(i)  , s2.substring(i)   )  )
                return true ;
            if ( i > 0 && a == b &&
                    isScramble(s1.substring(0,i), s2.substring(N-i))
                    && isScramble(s1.substring(i), s2.substring(0,N-i)))
                return true ;
            a*=s1.charAt(i);
            b*=s2.charAt(N-1-i);
            c*=s2.charAt(i);
        }
        return false ;
    }
}
