package algo.leetcode;

/**
 *
 * 一个奇怪的打印机打印时遵守以下两个特殊的条件：
 每次只能打印同一个字符组成的连续序列。
 每次打印可以在任何位置开始，在任何位置结束，打印的字符会将原来已有的字符覆盖。
 给定一个只包含小写字母的字符串，你的任务是计算用该打印机打印出这个字符串所需的最少打印次数。
 字符串长度不超过100。
 */
public class PrintNum {
    /**
     *算法思路：
     * 使用区间动态规划
     * 从小区间到大区间
     * 每次计算i-j之间字符打印的最小次数
     * @param values 字符串
     * @return 最小打印次数
     */
    public int minPrintCount(char[] values){
        if(values==null || values.length < 1){
            return 0;
        }
        int[][] f = new int[values.length][values.length];
        for(int i = 0; i < values.length; i ++){
            f[i][i] = 1;
        }
        for(int i = 2; i <= values.length; i ++){
            for(int j = 0; i+j <= values.length; j ++){
                int l = i + j - 1;
                f[j][l] = 1 + f[j+1][l];
                for(int k = j+1; k < l; k ++){
                    if(values[j] == values[k]){
                        f[j][l] = Math.min(f[j][l],
                                f[j+1][k] + f[k+1][l]);
                    }
                }
                if(values[j] == values[l]){
                    f[j][l] = Math.min(f[j][l], f[j+1][l]);
                }
            }
        }
        return f[0][values.length-1];
    }
}
