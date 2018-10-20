package algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {
    class SeqmentTree{
        public int minW;
        public int maxW;
        public int minH;
        public int maxH;
        public int count;
        public boolean isEnv;
        public SeqmentTree(int w, int h){

        }

        public void updateSegmentTree(int w, int h){

        }
    }


    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int[] dp = new int[envelopes.length+1];

        int max = 0;
        for(int i = 0; i < envelopes.length; i ++){
            dp[i + 1] = 1;
            for(int j = i-1; j>=0; j --){
                if(envelopes[i][0] > envelopes[j][0] &&
                        envelopes[i][1] > envelopes[j][1]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j + 1] + 1);
                }
            }
            max = Math.max(dp[i + 1], max);
        }
        return max;
    }
}
