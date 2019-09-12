package algo.search;

import java.util.Arrays;
import java.util.Collections;

public class CoinChange {
    int minCount = Integer.MAX_VALUE;
    public  int  coinChange(int[] coins, int amount) {

        Arrays.sort(coins);
        count(amount, coins.length -1, coins, 0);
        return minCount == Integer.MAX_VALUE? -1: minCount;
    }

    void count(int amount, int index, int[] coins, int count){
        if(amount % coins[index] == 0){
            minCount = Math.min(minCount, count + amount / coins[index]);
            return ;
        }

        if(index ==0) return ;
        for(int i = amount/ coins[index]; i >= 0; i--){
            int newAmount = amount  - i * coins[index];
            int newCount = count +i;
            int nextCoin = coins[index -1];
            if(newCount +(newAmount + nextCoin -1)/nextCoin >= minCount) return;

            count(newAmount, index -1, coins, newCount);

        }
    }

    public static void main(String[] args){
        int[] a = {186,419,83,408};
        System.out.println(new CoinChange().coinChange(a, 6249));
    }
}
