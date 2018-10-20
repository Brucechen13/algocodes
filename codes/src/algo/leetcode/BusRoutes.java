package algo.leetcode;


import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T)return 0;

        Map<Integer, Set<Integer>> busCenters = new HashMap<>();
        for(int i = 0; i < routes.length; i ++){
            for(int j = 0; j < routes[i].length; j ++){
                if(!busCenters.containsKey(routes[i][j])){
                    busCenters.put(routes[i][j], new HashSet<>());
                }
                busCenters.get(routes[i][j]).add(i);
            }
        }

        List<Integer> visited = new ArrayList<>();
        Queue<Integer> busLines = new PriorityQueue<>();
        for (int busLine:
             busCenters.get(S)) {
            busLines.add(busLine);
        }

        return getTargetBus(busLines, T, 1, visited, busCenters, routes);
    }

    public int getTargetBus(Queue<Integer> busLines, int T, int step,
                            List<Integer> visited, Map<Integer, Set<Integer>> busCenters,
                            int[][] routes){

        Queue<Integer> nextBusLines = new PriorityQueue<>();
        while (!busLines.isEmpty()){
            int line = busLines.poll();
            visited.add(line);
            for(int i = 0; i < routes[line].length; i ++){
                if(routes[line][i] == T)return step;

                for (int busLine:
                        busCenters.get(routes[line][i])) {
                    if(!visited.contains(busLine)){
                        nextBusLines.add(busLine);
                    }
                }
            }
        }
        if(nextBusLines.isEmpty())return -1;
        return getTargetBus(nextBusLines, T, step+1, visited, busCenters, routes);
    }

    public static void main(String[] args){
        int[][] routes = {{1,2, 7}, {3, 6, 5}};

        System.out.print(new BusRoutes().numBusesToDestination(routes, 1, 1));
    }


    public int numBusesToDestination2(int[][] routes, int S, int T) {
        int maxStop = 0;
        for (int[] route : routes) {
            for (int stop : route) {
                maxStop = Math.max(stop, maxStop);
            }
        }
        maxStop++;
        int[] counts = new int[maxStop];
        Arrays.fill(counts, maxStop);
        counts[S] = 0;
        boolean changed = true;
        for (int i = 0; changed && i < routes.length; i++) {
            changed = false;
            for (int[] route : routes) {
                int min = maxStop - 1;
                for (int stop : route) {
                    min = Math.min(min, counts[stop]);
                }
                min += 1;
                for (int stop : route) {
                    if (counts[stop] > min) {
                        counts[stop] = min;
                        changed = true;
                    }
                }
            }
        }
        return counts[T] == maxStop ? -1 : counts[T];
    }


}
