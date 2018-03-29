package algo.string;

import java.util.ArrayList;
import java.util.List;

public class MiniParser {
    class NestedInteger {
        int value;
        List<NestedInteger> nests = new ArrayList<>();
        boolean isInt;
        public NestedInteger(){

        }
        public NestedInteger(int value){
            this.value = value;
            this.isInt = true;
        }
        public boolean isInteger(){
            return isInt;
        }
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){
            return  value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value){
            this.value = value;
            this.isInt = true;
        }
        public void add(NestedInteger ni){
            nests.add(ni);
        }
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){
            return nests;
        }

        public void print(){
            if(isInteger()){
                System.out.println(getInteger());
            }else{
                for (NestedInteger nest : getList()){
                    nest.print();
                }
            }
        }
    }

    public NestedInteger deserialize(String s) {
        NestedInteger res = new NestedInteger();
        if(s.contains("[")){
            res = setNested(s);
        }else{
            res.setInteger(Integer.parseInt(s));
        }
        return res;
    }

    public int findNext(String s, int startIndex){
        int leftFlag = 0;
        for(int i = startIndex; i < s.length()-1; i ++){
            if(leftFlag == 0 && s.charAt(i) == ','){
                return i;
            }else if(s.charAt(i) == '['){
                leftFlag++;
            }else if(s.charAt(i) == ']'){
                leftFlag--;
            }
        }
        return s.length()-1;
    }

    public NestedInteger setNested(String s){
        NestedInteger cur = new NestedInteger();
        if(s == null || s.equals("") || s.equals("[]")){
            return cur;
        }
        if(!s.startsWith("[")){
            cur.setInteger(Integer.parseInt(s));
            return cur;
        }
        int start = 1;
        while (true){
            int next = findNext(s, start);
            String sub = s.substring(start, next);
            cur.add(setNested(sub));
            start = next+1;
            if(start >= s.length()){
                break;
            }
        }
        return cur;
    }
    public static void main(String[] args){
        NestedInteger res = new MiniParser().deserialize("[123,[456,[789]]]");
        res.print();
    }

}
