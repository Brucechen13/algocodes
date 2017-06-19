package algo.array;

/**
 * Created by chenc on 2017/6/19.
 */
public class SellStockII {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 1){
            return 0;
        }
        int sum= 0;
        int min = -1, max = -1;
        for(int i = 0; i < prices.length; i ++){
            if(min == -1){
                min = prices[i];
            }else if(max == -1){
                if(prices[i] < min){
                    min = prices[i];
                }else{
                    max = prices[i];
                }
            }else{
                if(max > prices[i]){
                    sum += (max-min);
                    min = prices[i];
                    max = -1;
                }else{
                    max = prices[i];
                }
            }
        }
        if(min!=-1&&max!=-1){
            sum += (max-min);
        }
        return sum;
    }
}
