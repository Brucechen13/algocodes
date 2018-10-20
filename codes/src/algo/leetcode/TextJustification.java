package algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        List<String> cur = new ArrayList<>();
        int len = 0;
        for (int i = 0; i < words.length; i ++) {
            if (len+words[i].length() > maxWidth) {
                int space_size = cur.size()>1?(cur.size()-1):1;
                len = len - cur.size();
                int right_len = (maxWidth - len) % space_size;
                int space_len = (maxWidth - len - right_len) / space_size;
                for (int j = 0; j < cur.size(); j++) {
                    sb.append(cur.get(j));
                    if(j == cur.size()-1 && cur.size() > 1)break;
                    for (int k = 0; k < (j == cur.size()-1 ? right_len : space_len); k++) {
                        sb.append(" ");
                    }
                }
                res.add(sb.toString());
                sb = new StringBuilder();
                cur = new ArrayList<>();
                len = 0;
            }
            len += words[i].length() + 1;
            cur.add(words[i]);
        }
        if(cur.size() > 0){
            for (int j = 0; j < cur.size(); j++) {
                sb.append(cur.get(j) + " ");
            }
            for(int j = sb.length(); j < maxWidth; j ++){
                sb.append(" ");
            }
            res.add(sb.toString());
        }
        return res;
    }


    public List<String> fullJustify2(String[] words, int L) {
        List<String> lines = new ArrayList<String>();

        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }


        return lines;
    }
}
