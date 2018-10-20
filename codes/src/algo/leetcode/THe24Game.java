package algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class THe24Game {
    public boolean judgePoint24(int[] nums) {
        List<Double> vals = new ArrayList<>();
        for (int val:
                nums) {
            vals.add((double)val);
        }
        return dfs(vals);
    }

    public boolean dfs(List<Double> lefts){
        if(lefts.size() == 1){
            for (double val:
                 lefts) {
                if(checkVal(val))return true;
            }
            return false;
        }
        for(int i = 0; i < lefts.size(); i ++){
            for(int j = i + 1; j < lefts.size(); j ++){
                List<Double> vals = generatePossible(lefts.get(i), lefts.get(j));
                for(double val : vals) {
                    List<Double> newLefts = new ArrayList<>();
                    newLefts.add(val);
                    for (int k = 0; k < lefts.size(); k++) {
                        if (k == i || k == j) continue;
                        newLefts.add(lefts.get(k));
                    }
                    if(dfs(newLefts))return true;
                }
            }
        }
        return false;
    }

    public List<Double> generatePossible(double a, double b){
        List<Double> vals = new ArrayList<>();
        vals.add(a+b);
        vals.add(a-b);
        vals.add(a*b);
        vals.add(a/b);
        vals.add(b-a);
        vals.add(b/a);
        return vals;
    }

    public boolean checkVal(double cur){
        if(Math.abs(cur - 24) < 1e-9){
            return true;
        }
        return false;
    }


    public boolean get(double[] nums){
        if(nums.length == 1)return Math.abs(nums[0] - 24) <= 0.001;
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                double[] newNum = new double[nums.length - 1];
                int pos = 0;
                for(int k = 0; k < newNum.length - 1; k++){
                    while(pos == i || pos == j)pos++;
                    newNum[k] = nums[pos++];
                }
                for(double w : getPossiblityies(nums[i], nums[j])){
                    newNum[newNum.length - 1] = w;
                    if(get(newNum))return true;
                }
            }
        }
        return false;
    }
    public boolean judgePoint242(int[] nums) {
        double[] ret = new double[nums.length];
        for(int i = 0 ; i < nums.length; i++)
            ret[i] = nums[i];
        return get(ret);
    }
    double[] getPossiblityies(double x, double y){
        return new double[]{x + y, x - y, x * y, x/y, y - x, y/x};
    }

    public static void main(String[] args){
        int[] vals = {1,2,1,2};
        System.out.println(new THe24Game().judgePoint24(vals));
    }
}
