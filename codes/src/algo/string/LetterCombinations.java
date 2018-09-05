package algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0){
            return new ArrayList<String>();
        }
        String[][] vals = new String[][]{
                {"-"},{"0"}, {"a", "b", "c"},{"d","e","f"},{"g","h","i"},
                {"j","k","l"},{"m","n","o"}, {"p","q","r","s"},
                {"t","u", "v"}, {"w","x","y","z"}
        };
        List<String> res = new ArrayList<String>();
        int len = digits.length();
        for (int i = 0 ;i < len; i ++){
            int index = digits.charAt(i)-'0';
            if(index < 2)
                return new ArrayList<String>();
            if(res.size() == 0){
                res.addAll(Arrays.asList(vals[index]));
            }else{
                List<String> res2 = new ArrayList<String>();
                for (String rs:
                        res) {
                    for (String val:
                            vals[index]) {
                        res2.add(rs+val);
                    }
                }
                res = res2;
            }
        }
        return res;
    }
}
