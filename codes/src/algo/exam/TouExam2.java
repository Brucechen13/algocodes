package algo.exam;

import java.util.*;

/**
P为给定的二维平面整数点集。定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），
        则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）

        如下图：实心点为满足条件的点的集合。请实现代码找到集合 P 中的所有 ”最大“ 点的集合并输出。
 **/

public class TouExam2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] points = new int[n][2];
        Queue<int[]> queue1 = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        Queue<int[]> queue2 = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1];
            }
        });
        Queue<int[]> res = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        for(int i = 0; i < n; i ++){
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();
            queue1.add(points[i]);
            queue2.add(points[i]);
        }
        while (!queue1.isEmpty()) {
            int[] val = queue1.poll();
            res.add(val);
            List<String> lefts = new LinkedList<>();
            while (!queue1.isEmpty()) {
                int[] nt = queue1.poll();
                if (nt[1] >= val[1]) {
                    lefts.add(nt[0] + " " + nt[1]);
                }
            }
            int[] val2 = queue2.poll();
            if (val[0] == val2[0] && val[1] == val2[1]) {
                queue2.clear();
            } else {
                res.add(val2);
            }
            while (!queue1.isEmpty()) {
                int[] nt = queue1.poll();
                if (nt[0] < val2[0]) {
                    lefts.remove(nt[0] + " " + nt[1]);
                }
            }
            for (String s :
                    lefts) {
                int[] v = new int[2];
                v[0] = Integer.parseInt(s.split(" ")[0]);
                v[1] = Integer.parseInt(s.split(" ")[1]);
                queue1.add(v);
                queue2.add(v);
            }
        }
        for(int[] v : res) {
            System.out.println(v[0] + " " + v[1]);
        }
    }
}
