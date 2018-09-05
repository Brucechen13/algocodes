package algo.other;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        String res = "";
        int[] leftSize = new int[128];
        boolean[] isIn = new boolean[128];
        for(int i = 0; i < count; i ++){
            leftSize[t.charAt(i) - 'A'] += 1;
            isIn[t.charAt(i) - 'A'] = true;
        }
        int i = -1;//指示当前下标
        int j = 0;//指示子串开始下标
        while (i < s.length() && j < s.length()){
            if(count > 0){
                i++;
                if(i >= s.length())break;
                leftSize[s.charAt(i) - 'A']--;
                if(isIn[s.charAt(i)-'A'] && leftSize[s.charAt(i) - 'A'] >= 0){
                    count--;
                }
            }else{
                int min = i - j + 1;
                if(min < minLen){
                    minLen = min;
                    res = s.substring(j, i+1);
                }
                leftSize[s.charAt(j) - 'A']++;
                if(isIn[s.charAt(j) - 'A'] && leftSize[s.charAt(j) - 'A'] > 0){
                    count++;
                }
                j ++;
            }
        }
        return res;
    }
    public static void main(String[] args){
        System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC","ABC"));
    }
}
