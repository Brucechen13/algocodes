package algo.other;

public class MinimumNumberRefuelingStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int[] dp = new int[target];// at posi i need least fuel to rearch target
        if(startFuel >= target)return 0;
        if(stations.length < 1 || startFuel < stations[0][0])return -1;
        return dfs(0, stations, startFuel - stations[0][0], target);
    }

    public int dfs(int index, int[][] stations, int leftFuel, int target){
        int res = 0;
        if(leftFuel > target)return 0;
        if(index == stations.length || leftFuel < 0)return Integer.MAX_VALUE;
        if(index != stations.length-1) {
            res = dfs(index + 1, stations, leftFuel - stations[index+1][0], target);
        }
        res = Math.min(res, dfs(index+1, stations,
                leftFuel-stations[index+1][0]+stations[index][0], target) + 1);
        return res;
    }
}
