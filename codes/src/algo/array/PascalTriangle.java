package algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenc on 2017/6/20.
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 1; i <= numRows; i ++){
            List<Integer> ans = new ArrayList<Integer>();
            ans.add(1);
            if(i != 1) {
                List<Integer> pre = res.get(i-2);
                for (int j = 1; j < i - 1; j++) {
                    ans.add(pre.get(j-1) + pre.get(j));
                }
                ans.add(1);
            }
            res.add(ans);
        }
        return res;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<Integer>();
        List<Integer> cur = new ArrayList<Integer>();
        pre.add(1);
        for(int i = 1; i <= rowIndex+1 && rowIndex!=0; i ++){
            pre = cur;
            cur = new ArrayList<Integer>();
            cur.add(1);
            for(int j = 1;j < i-1; j ++){
                cur.add(pre.get(j-1)+pre.get(j));
            }
            cur.add(1);
        }
        return cur.size()==0?pre:cur;
    }
}
