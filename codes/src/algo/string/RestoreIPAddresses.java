package algo.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        addIps(s, 0, res, new StringBuilder(), 4);
        return res;
    }
    public void addIps(String s, int startIndex, List<String> res, StringBuilder cur, int left){
        if(startIndex >= s.length() && left==0){
            res.add(cur.substring(1));
            return;
        }
        if(left == 0){
            return;
        }
        for(int i = 1; startIndex+i <= s.length() && i <= 3; i ++){
            String num = s.substring(startIndex, startIndex+i);
            if(num.length()>1 && num.startsWith("0")){
                continue;
            }
            if(Integer.parseInt(num) < 256){
                StringBuilder tmp = new StringBuilder(cur);
                tmp.append(".").append(num);
                addIps(s, startIndex+i, res, tmp, left-1);
            }
        }
    }
    public static void main(String[] args){
        System.out.println(new RestoreIPAddresses().restoreIpAddresses("010010"));
    }
}
