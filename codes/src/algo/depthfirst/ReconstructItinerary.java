package algo.depthfirst;

import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        if(tickets==null || tickets.length <=0)return null;
        Map<String, List<String>> cached = new HashMap<>();
        for (String[] ticket:
                tickets) {
            if(!cached.containsKey(ticket[0])){
                cached.put(ticket[0], new ArrayList<>());
            }
            cached.get(ticket[0]).add(ticket[1]);
        }
        List<String> res = new ArrayList<>();
        res.add("JFK");
        dfs(cached, "JFK", res, tickets.length+1);
        return res;
    }

    private boolean dfs(Map<String, List<String>> cached, String start, List<String> res, int len){
        System.out.println(start + " " + res.size());
        if(res.size() == len)return true;
        if(!cached.containsKey(start))return false;
        List<String> targes = cached.get(start);
        List<String> tmp = new ArrayList<>(targes);
        Collections.sort(targes);
        for (String target:
                targes) {
            tmp.remove(target);
            cached.put(start, tmp);
            res.add(target);
            if(dfs(cached, target, res, len))return true;
            tmp.add(target);
            res.remove(res.lastIndexOf(target));
        }
        cached.put(start, targes);
        return false;
    }



    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> res = new LinkedList<>();
    public List<String> findItinerary2(String[][] tickets) {
        for(String[] ticket : tickets) {
            if(map.get(ticket[0]) == null) {
                map.put(ticket[0], new PriorityQueue<>());
            }
            map.get(ticket[0]).offer(ticket[1]);
        }
        dfs("JFK");
        return res;
    }

    public void dfs(String src) {
        PriorityQueue<String> queue = map.get(src);

        while(queue != null && queue.size() > 0) {
            String dest = queue.poll();
            dfs(dest);
        }
        res.addFirst(src);
    }

    public static void main(String[] args){
        String[][] data = {{"CBR","JFK"},{"TIA","EZE"},{"AUA","TIA"},{"JFK","EZE"},{"BNE","CBR"},{"JFK","CBR"},{"CBR","AUA"},{"EZE","HBA"},{"AXA","ANU"},{"BNE","EZE"},{"AXA","EZE"},{"AUA","ADL"},{"OOL","JFK"},{"BNE","AXA"},{"OOL","EZE"},{"EZE","ADL"},{"TIA","BNE"},{"EZE","TIA"},{"JFK","AUA"},{"AUA","EZE"},{"ANU","ADL"},{"TIA","BNE"},{"EZE","OOL"},{"ANU","BNE"},{"EZE","ANU"},{"ANU","AUA"},{"BNE","ANU"},{"CNS","JFK"},{"TIA","ADL"},{"ADL","AXA"},{"JFK","OOL"},{"AUA","ADL"},{"ADL","TIA"},{"ADL","ANU"},{"ADL","JFK"},{"BNE","EZE"},{"ANU","BNE"},{"JFK","BNE"},{"EZE","AUA"},{"EZE","AXA"},{"AUA","TIA"},{"ADL","CNS"},{"AXA","AUA"}};
        new ReconstructItinerary().findItinerary(data);
    }
}
