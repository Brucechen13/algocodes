package algo.other;

import java.util.Arrays;

public class BestTimeStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(k >= len)return maxProfit2(prices);
        int[] localProfits = new int[k+1];
        int[] globalProfits = new int[k+1];

        for(int i = 1; i < len; i ++){
            int diff = prices[i] - prices[i-1];
            for(int j = k; j > 0; j --){
               localProfits[j] = Math.max(globalProfits[j-1], localProfits[j] + diff);
               globalProfits[j] = Math.max(globalProfits[j], localProfits[j]);
            }
        }
        return globalProfits[k];
    }

    public int maxProfit2(int[] prices){
        int res = 0;
        for(int i = 1; i < prices.length; i ++){
            if(prices[i] > prices[i-1])res += prices[i] - prices[i-1];
        }
        return res;
    }

    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if(k == 0 || n < 2) return 0;
        if(k >= n / 2){
            int res = 0;
            for(int i = 1; i < prices.length; i++)
                res += Math.max(0, prices[i] - prices[i - 1]);
            return res;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for(int p : prices){
            for(int i = 1; i <= k; i++){
                sell[i] = Math.max(sell[i], buy[i] + p);
                buy[i] = Math.max(buy[i], sell[i - 1] - p);
            }
        }
        return sell[k];
    }
}
