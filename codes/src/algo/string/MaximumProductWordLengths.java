package algo.string;

public class MaximumProductWordLengths {
    public int maxProduct(String[] words) {
        int[] flags = new int[words.length];
        for(int i = 0; i < words.length; i ++){
            String w = words[i];
            int flag = 0;
            for (int j = 0; j < w.length(); j ++){
                flag |= (1 << (w.charAt(j) - 'a'));
            }
            flags[i] = flag;
        }

        int maxLen = 0;
        for(int i = 0; i < words.length; i ++){
            for(int j = i+1; j < words.length; j ++){
                if((flags[i] & flags[j]) == 0){
                    maxLen = Math.max(maxLen, words[i].length()*words[j].length());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args){
        String[] a = {"a","ab","abc","d","cd","bcd","abcd"};
        System.out.println(new MaximumProductWordLengths().maxProduct(a));
    }
}
