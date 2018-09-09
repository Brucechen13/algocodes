package algo.competition;

import java.util.ArrayList;
import java.util.List;

public class LeetOnlineStockSpan {
    class StockSpanner {

        private List<Integer> vals = new ArrayList<>();
        private List<Integer> counts = new ArrayList<>();

        public StockSpanner() {

        }

        public int next(int price) {

            if(vals.size() == 0 || price < vals.get(vals.size()-1)){
                vals.add(price);
                counts.add(1);
                return 1;
            }else{
                int count = 1;
                int len = vals.size();
                int pre = counts.get(counts.size()-1);
                count += pre;
                int index = len - pre;
                while (index > 0 && price >= vals.get(index-1)){
                    count += counts.get(index-1);
                    index -= counts.get(index-1);
                }
                vals.add(price);
                counts.add(count);
                return count;
            }

        }
    }
}
