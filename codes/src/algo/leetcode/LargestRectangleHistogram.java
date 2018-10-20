package algo.leetcode;

import java.util.Stack;

public class LargestRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> index = new Stack<>();

        for(int i = 0; i < heights.length + 1; i++)
        {
            int curHei = i < heights.length?heights[i]:0;
            while(index.size() > 0 && heights[index.peek()] >= curHei)
            {
                int h = heights[index.pop()];

                int sidx = index.size() > 0 ? index.peek() : -1;
                if(h * (i-sidx-1) > ret)
                    ret = h * (i-sidx-1);
            }
            index.add(i);
        }

        return ret;
    }

    public int largestRectangleArea2(int[] heights) {
        if(heights==null||heights.length==0) return 0;
        return calculate(heights,0,heights.length-1);
    }
    public int calculate(int[] heights,int start,int end){
        if(start>end) return 0;
        if(start==end) return heights[start];
        boolean sorted=true;
        int min=start;
        for(int i=start+1;i<=end;i++){
            if(heights[i]<heights[i-1]) sorted=false;
            if(heights[i]<heights[min]) min=i;
        }
        if(sorted){
            int max=0;
            for(int i=start;i<=end;i++){
                max=Math.max(max,heights[i]*(end-i+1));
            }
            return max;
        }
        int l=calculate(heights,start,min-1);
        int r=calculate(heights,min+1,end);
        return Math.max(Math.max(l,r),heights[min]*(end-start+1));
    }
}
