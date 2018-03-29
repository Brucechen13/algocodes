package algo.string;

import java.util.*;

public class ReorganizeString {
    public String reorganizeString(String S) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> maps = new HashMap<>();
        for(int i = 0; i < S.length(); i ++){
            maps.put(S.charAt(i), maps.getOrDefault(S.charAt(i), 0)+1);
        }
        while (true) {
            List<Map.Entry<Character, Integer>> chars = new ArrayList<>(maps.entrySet());
            chars.sort(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
                //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
            });
            int len = chars.get(0).getValue();
            if(len == 0){
                break;
            }
            char[] vals = new char[len*2];
            for(int i = 0; i < len; i ++){
                vals[i*2] = chars.get(0).getKey();
            }
            maps.put(chars.get(0).getKey(), 0);
            int last = 0;
            int last_idx = 1;
            while (last < len && last_idx < chars.size()){
                int sub_len = chars.get(last_idx).getValue();
                if((len-last) <= sub_len){
                    sub_len = (len-last);
                }
                for(int i = last; i < last+sub_len; i ++){
                    vals[2*i+1] = chars.get(last_idx).getKey();
                }
                last += sub_len;
                maps.put(chars.get(last_idx).getKey(), chars.get(last_idx).getValue()-sub_len);
                last_idx ++;
            }
            if(last >= len-1){
                sb.append(new String(vals));
                if(sb.charAt(sb.length()-1) < 'a' || sb.charAt(sb.length()-1) > 'z'){
                    sb = sb.deleteCharAt(sb.length()-1);
                }
            }
        }

        if(sb.toString().length() == S.length()){
            return sb.toString();
        }
        return "";
    }
    public static void main(String[] args){
        System.out.println(new ReorganizeString().reorganizeString("aab"));
    }
}
