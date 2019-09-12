package algo.array;

import java.util.LinkedList;

public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        int count = 0;
        int max = 0;

        LinkedList<String> ls = new LinkedList<>();
        LinkedList<Integer> li = new LinkedList<>();
        LinkedList<Integer> lmi = new LinkedList<>();

        int preIndex = 0;

        for(int i = 0; i < input.length()-1; i ++){
            if(input.charAt(i) == '\n'){
                String path = input.substring(preIndex, i);
                while (li.size() != 0  && li.peekLast() >= count){
                    li.pollLast();
                    ls.pollLast();
                    lmi.pollLast();
                }
                if(li.size() == 0 || li.peekLast() < count){
                    ls.add(path);
                    li.add(count);
                    int pre = lmi.size() == 0 ? 0 : lmi.peekLast() + 1;
                    lmi.add(pre + path.length());

                    if(path.contains(".")) {
                        max = Math.max(max, pre + path.length());
                    }
                }
                count = 0;
                preIndex = i+1;
            }
            if(input.charAt(i) == '\t'){
                count++;
                preIndex = i+1;
            }
        }
        while (li.size() != 0  && li.peekLast() >= count){
            li.pollLast();
            ls.pollLast();
            lmi.pollLast();
        }
        String path = input.substring(preIndex);
        int pre = lmi.size() == 0 ? 0 : lmi.peekLast() + 1;
        if(path.contains(".")) {
            max = Math.max(max, pre + path.length());
        }
        return max;
    }

    public static void main(String[] args){
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath(
                "a"));
    }
}
