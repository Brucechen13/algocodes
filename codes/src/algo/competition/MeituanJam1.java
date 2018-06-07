package algo.competition;

import java.util.Scanner;

public class MeituanJam1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {//注意while处理多个case
            int n = sc.nextInt();
            int m = sc.nextInt();
            double totalSum = 0, freeSum = 0;
            for (int i = 0; i < n; i++) {
                int price = sc.nextInt();
                int sale = sc.nextInt();
                totalSum += price;
                freeSum += price * (sale == 1 ? 0.8 : 1.0);
            }
            double maxCut = 0;
            for (int i = 0; i < m; i++) {
                int price = sc.nextInt();
                int cut = sc.nextInt();
                if (price <= totalSum) {
                    maxCut = Math.max(cut, maxCut);
                }
            }
            totalSum -= maxCut;
            totalSum = totalSum < freeSum ? totalSum : freeSum;
            System.out.println(String.format("%.2f", totalSum));
        }
    }

    public int findSubMid(int[] array, int start, int end, int mid){

        int i = PARTITION(array, start, end);
        if(i == mid)return array[i];
        else if(i > mid)return findSubMid(array, start, i-1, mid);
        else return findSubMid(array, i+1, end, mid-i);
    }

    public int PARTITION(int[] array, int start, int end){
        int i = start, j = end;
        int index = array[start];
        while (i < j){
            while (array[i++] < index);
            while (array[j--] > index);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        array[i] = index;
        return i;
    }
}
