package algo.competition;

import java.util.*;

/**
 * 配送员每天都在为人们美好的生活辛勤工作。
 在某位同城配送员的时刻表上，有n个任务，表示他在某年某月某一刻必须出现在某地把包裹交到对应的顾客手上。
 配送员需要通过公共交通在城市里穿梭。在这个城市里，有m条公交线路，这些公交线路都是直达的（在起点和终点之间不存在其他站点），每天每条线路都有固定的发车和到达时间，还有搭乘它们的价格。城市里有k个关键点，所有任务的目标点以及所有线路的起终点都在这些关键点上。
 现在问，在能保证所有任务准时完成的情况下，最少需要花费多少金钱。
 输入描述:
 第一行三个正整数n, m, k分别表示配送任务数、公交线路数以及城市中包含的关键点点数。
 接下来n行，每行一个整数x和一个字符串t，表示需要在恰好t时刻把一个包裹送到下标为x的地点（顾客不会提早到达预定地点，也不能容忍配送员迟到，所以如果配送员早到的话，他需要等到时刻t）。数据不保证任务以t升序的形式给出。
 接下来m行，每行三个整数和两个字符串S, T, p, t1，t2，表示S到T有一条价格为p的公交线路，这条线路在每天的t1时刻发车，在t2时刻到达。这些线路都是单向的。
 1 <= n <= 10
 1 <= m <= 500
 1 <= k <= 100
 1 <= x <= k
 t是Year.Month.Day HH:MM:SS.SSS的形式，其中Year表示年份，Month表示月份，Day表示日期，HH表示小时，MM表示分钟，SS.SSS表示秒数。Day和HH之间是一个空格。数据保证t在2018.07.01 00:00:00.000到2018.07.07 23:59:59.999之间。假设现在的时间是2018.06.30 23:59:59.999，配送员正在1号点上。
 数据保证没有两个任务的交货时间是同时的，因此不会发生冲突。
 1 <= S, T <= k, S != T
 0 < p <= 1,000
 数据保证t1、t2在00:00:00.000到23:59:59.999之间，且都是当日出发，当日结束的行程（即t2 > t1）。
 对于每个任务，司机需要在严格小于预约时间的时刻到达，在严格大于预约时间的时刻离开。
 在这个问题中，不用考虑路上可能的延误。对于行程之间的接续，需要保证后一个行程的起始时间严格大于前一个行程的到达时间。
 我们并不在意配送员最后在哪一个地点。
 数据保证所有的时间格式合法。Month，Day，HH，MM均为长度为2的字符串，SS.SSS为长度为6的字符串，Year为长度为4的字符串。
 输出描述:
 输出一个整数表示最少需要花费的金钱。
 如果不能完成所有任务，请输出-1。
 */

public class MeituanJam2 {
    static class Item implements Comparable<Item>{
        int val;
        String time1;
        String time2;

        public Item(int k, String time1, String time2){
            this.val = k;
            this.time1 = time1;
            this.time2 = time2;
        }

        @Override
        public int compareTo(Item o) {
            return time1.compareTo(o.time1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {//注意while处理多个case
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            Item[] tasks = new Item[n];
            for(int i = 0; i < n; i ++){
                tasks[i] = new Item(sc.nextInt(), sc.next(), "");
            }
            Map<String, Queue<Item>> sches = new HashMap<>();
            for(int i = 0; i < m; i ++){
                int start = sc.nextInt()-1;
                int end = sc.nextInt()-1;
                Item item = new Item(sc.nextInt(), sc.next(), sc.next());
                String ss = ""+start + " " + end;
                if(!sches.containsKey(ss)){
                    sches.put(ss, new PriorityQueue<>(new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o1.val - o2.val;
                        }
                    }));
                }
                sches.get(ss).add(item);
            }
            Arrays.sort(tasks);

            for(int i = 0; i < tasks.length; i ++){

            }

        }
    }
}
