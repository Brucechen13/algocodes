package algo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTheRepetitions {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] reps = new int[102];
        int[] rests = new int[102];
        int posRest=0, repTime=0;
        int i=0, k=0;
        if(n1 <= 0) return 0;
        while(k==i) {
            i++;
            for(int j=0; j<s1.length(); j++) {
                if(s2.charAt(posRest) == s1.charAt(j)) {
                    posRest++;
                    if(posRest == s2.length()) {
                        repTime++;
                        posRest=0;
                    }
                }
            }
            if(i >= n1)
                return repTime / n2;
            for(k=0; k<i; k++){
                if(posRest == rests[k])
                    break;
            }
            reps[i] = repTime;
            rests[i] = posRest;
        }
        int interval = i-k;
        int repeatCount = (n1-k) / interval;
        int repeatTimes = repeatCount * (reps[i]-reps[k]);
        int remainTimes = reps[(n1-k) % interval + k];
        return (repeatTimes + remainTimes) / n2;
    }

    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        if(s1.length()*n1<s2.length()*n2) return 0;
        int i_2=0;
        int rep=0;
        while(i_2<s2.length()&&rep<1){
            int pre_2=i_2;
            int pre_rep=rep;
            for(int i=0;i<s1.length();i++){
                if(s1.charAt(i)==s2.charAt(i_2)) {
                    i_2++;
                    if(i_2==s2.length()){
                        i_2=0;
                        rep++;
                    }
                }

            }
            if(pre_rep==rep&&pre_2==i_2) return 0;//the pre_2 element can't be matched by s1
        }
        List<String> order=new ArrayList<>();
        Map<String,Integer> rem=new HashMap<>();
        int[] loop=new int[2];
        //int rank=0;
        int index=0;
        help(s1,"",s2,rem,order,index,loop);

        int pre_sum=0;
        int loop_sum=0;
        for(int i=0;i<loop[0];i++){
            String temp=order.get(i);
            pre_sum+=rem.get(temp);
        }
        for(int i=loop[0];i<=loop[1];i++){
            String temp=order.get(i);
            loop_sum+=rem.get(temp);
        }
        int n_s2=0;

        if(n1<=pre_sum+loop_sum){
            //no loop at all
            for(int i=0;i<order.size();i++){
                n1-=rem.get(order.get(i));
                if(n1<0) break;
                n_s2++;
            }
        }
        else{
            n1-=pre_sum;
            n_s2+=loop[0];
            int loop_num=n1/loop_sum;
            n_s2+=loop_num*(loop[1]-loop[0]+1);
            n1-=loop_num*loop_sum;
            int i=loop[0];
            while(n1-rem.get(order.get(i))>=0){
                n1-=rem.get(order.get(i));
                i++;
                n_s2++;
            }
        }

        return n_s2/n2;
    }
    public void help(String s1,String pre_rem,String s2, Map<String,Integer> rem,List<String> order,int index,int[] loop){
        int i=0,j=0;
        int rep_s1=0;
        order.add(pre_rem);
        String curr=pre_rem;
        if(curr==null||curr.length()==0){
            curr=s1;
            rep_s1++;
        }
        while(j<s2.length()){
            if(curr.charAt(i)==s2.charAt(j)){
                j++;
            }
            i++;
            if(j<s2.length()&&i>=curr.length()){
                i=0;
                curr=s1;
                rep_s1++;
            }
        }
        String curr_rem=curr.substring(i,curr.length());
        rem.put(pre_rem,rep_s1);

        if(!rem.containsKey(curr_rem)){
            help(s1,curr_rem,s2,rem,order,index+1,loop);
        }
        else{
            for(int k=0;k<order.size();k++){
                if(order.get(k).equals(curr_rem)) loop[0]=k;
                if(order.get(k).equals(pre_rem)){
                    loop[1]=k;
                    break;
                }

            }
        }
        return;

    }

}
