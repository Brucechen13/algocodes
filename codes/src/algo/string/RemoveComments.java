package algo.string;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean isblock = false;
        String pre = "";
        for(int i = 0; i < source.length; i ++) {
            boolean filter = true;
            String sub = source[i];
            if (isblock) {
                if (sub.contains("*/")) {
                    sub = sub.substring(sub.indexOf("*/") + 2);
                    isblock = false;
                    if(sub.contains("//")) {
                        sub = sub.substring(0, sub.indexOf("//"));
                    }
                    sub = pre + sub;
                    pre = "";
                }else{
                    continue;
                }
            } else {
                if (sub.contains("//")) {
                    sub = sub.substring(0, sub.indexOf("//"));
                }
                if (sub.contains("/*")) {
                    pre = sub.substring(0, sub.indexOf("/*"));
                    isblock = true;
                    if (sub.contains("*/")) {
                        sub = sub.substring(sub.indexOf("*/") + 2);
                        sub = pre + sub;
                        pre = "";
                        isblock = false;
                    }else{
                        continue;
                    }
                }
            }
            System.out.println(sub);
            if (sub.trim().length() > 0) {
                res.add(sub);
            }
        }
        return res;
    }

    public static void main(String[] args){
        String[] lines = {"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        List<String> res = new RemoveComments().removeComments(lines);
        System.out.println(res);
    }
}
