package algo.search;

import org.omg.CORBA.MARSHAL;

import java.util.Arrays;

public class HIndex {
    public int hIndex(int[] citations) {
        int[] ary2 = new int[citations.length+1];
        for(int i = 0; i < citations.length; i ++){
            if(citations[i] >= citations.length){
                ary2[citations.length] += 1;
            }else{
                ary2[citations[i]] += 1;
            }
        }
        int t = 0;
        for (int i = citations.length; i >= 0; i--) {
            t = t + ary2[i];
            if (t >= i) {
                return i;
            }
        }
        return 0;
    }
    public int hIndex2(int[] citations) {
        if(citations == null || citations.length < 1 || citations[citations.length-1] == 0){
            return 0;
        }
        int index = 0;
        for(int i = citations.length-1; i >= 0; i --){
            if(index > citations[i]){
                return Math.min(index, citations[i+1]);
            }
            index++;
        }
        return Math.min(index, citations[0]);
    }
    public static void main(String[] args){
        System.out.println(new HIndex().hIndex2(new int[]{0,1,1}));
    }
}
