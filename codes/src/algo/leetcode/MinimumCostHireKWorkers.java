package algo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCostHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Queue<double[]> queue = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] doubles, double[] t1) {
                return Double.compare(doubles[2], t1[2]);
            }
        });
        int len = wage.length;
        for(int i = 0; i < len; i ++){
            queue.add(new double[]{
                quality[i], wage[i], 1.0*wage[i]/quality[i]
            });
        }
        Queue<double[]> queue1 = new PriorityQueue<>(K, new Comparator<double[]>() {
            @Override
            public int compare(double[] doubles, double[] t1) {
                return -1 * Double.compare(doubles[0], t1[0]);
            }
        });
        double minCost = 0;
        double sumQ = 0;
        double maxRate = 0;
        for(int i = 0; i < K; i ++){
            double[] val = queue.poll();
            queue1.add(val);
            maxRate = val[2];
            sumQ += val[0];
        }
        minCost = sumQ * maxRate;
        while (!queue.isEmpty()){
            double[] val = queue.poll();
            double[] pre = queue1.peek();
            if(val[0] < pre[0]){
                queue1.poll();
                queue1.add(val);
                sumQ -= pre[0];
                sumQ += val[0];
                minCost = Math.min(minCost, sumQ * val[2]);
            }
        }
        return minCost;
    }

    public static void main(String[] args){
        //int[] quality = {4,4,4,5}, wage = {13,12,13,12};
        int[] quality = {32,43,66,9,94,57,25,44,99,19},
                wage = {187,366,117,363,121,494,348,382,385,262};
        int K = 4;
        System.out.print(new MinimumCostHireKWorkers().mincostToHireWorkers(quality, wage, K));
    }
}
