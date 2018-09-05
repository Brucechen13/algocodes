package algo.array;

/**
 * Created by chenc on 2017/7/2.
 */
public class TeemoAttacking {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries == null || timeSeries.length < 1){
            return 0;
        }
        int totalSum = 0;
        int lastPosiTime = timeSeries[0] + duration;
        for(int i = 1; i < timeSeries.length; i ++){
            if(lastPosiTime < timeSeries[i]){
                totalSum += lastPosiTime-timeSeries[i-1];
            }else{
                totalSum += timeSeries[i] - timeSeries[i-1];
            }
            lastPosiTime = timeSeries[i] + duration;
        }
        return totalSum + duration;
    }
}
