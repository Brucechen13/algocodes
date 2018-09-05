package algo.array;

/**
 * Created by chenc on 2017/6/19.
 */
public class SellStock {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 1)
            return 0;
        int min = prices[0], max = 0;
        for(int i = 1; i < prices.length; i ++){
            if(prices[i] < min){
                min = prices[i];
            }else if((prices[i] - min) >  max){
                max = prices[i] - min;
            }
        }
        return max;
    }
}
