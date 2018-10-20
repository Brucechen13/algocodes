package algo.leetcode;

public class CountTheRepetitions {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] reps = new int[102];
        int[] rests = new int[102];
        int posRest=0, repTime=0;
        int i=0, k=0;
        if(n1 <= 0) return 0;
        while(k==i) {
            i++;
            for(int j=0; j<s1.length(); j++) {
                if(s2.charAt(posRest) == s1.charAt(j)) {
                    posRest++;
                    if(posRest == s2.length()) {
                        repTime++;
                        posRest=0;
                    }
                }
            }
            if(i >= n1)
                return repTime / n2;
            for(k=0; k<i; k++){
                if(posRest == rests[k])
                    break;
            }
            reps[i] = repTime;
            rests[i] = posRest;
        }
        int interval = i-k;
        int repeatCount = (n1-k) / interval;
        int repeatTimes = repeatCount * (reps[i]-reps[k]);
        int remainTimes = reps[(n1-k) % interval + k];
        return (repeatTimes + remainTimes) / n2;
    }

}
