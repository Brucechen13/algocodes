package algo.other;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if(s.equals("") || p.equals("")){
            for(int i = 0; i < p.length();i ++){
                if(p.charAt(i) != '*')return false;
            }
            return s.length() == 0;
        }
        int slen = s.length();
        int plen = p.length();
        int[][] dp = new int[slen][plen];
        boolean isMeet = false;
        for(int j = 0; j < plen; j ++){
            if(!isMeet && (s.charAt(0) == p.charAt(j) || p.charAt(j) == '?')){
                isMeet = true;
                dp[0][j] = 1;
            }
            if(p.charAt(j) == '*' && (j==0 || dp[0][j-1]==1)){
                dp[0][j] = 1;
            }else {
                isMeet = true;
            }
        }
        for(int i = 1; i < slen; i ++){
            for(int j = 0; j <plen; j ++) {
                if (j > 0 && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?' ||
                        p.charAt(j) == '*')) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }
                if (p.charAt(j) == '*') {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                    if(j > 0) {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                    }
                }
            }
        }
        return dp[slen-1][plen-1] == 1;
    }



    public boolean isMatch2(String s1, String p1) {
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        int i = 0, j = 0;
        int m = s.length, n = p.length;
        int last_match = -1, starj = -1;
        while (i < m){
            if (j < n && (s[i] == p[j] || p[j] == '?')) {
                i++; j++;
            }
            else if (j < n && p[j] == '*') {
                starj = j;
                j++;
                last_match = i;
            }
            else if (starj != -1) {
                j = starj + 1;
                last_match++;
                i = last_match;
            }
            else return false;
        }

        while (j <n && p[j] == '*')
            j++;
        return j == n;
    }

    public static void main(String[] args){
        System.out.println(new WildcardMatching().isMatch("ab",
                "*?*?*"));

    }
}
