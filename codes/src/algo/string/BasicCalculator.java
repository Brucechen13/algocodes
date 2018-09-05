package algo.string;

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> values = new Stack<>();
        int index = 0;
        int lastIndex = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
            } else {
                if (lastIndex < index) {
                    int s2 = Integer.parseInt(s.substring(lastIndex, index));
                    if (ops.size()>0 && (ops.peek() == '*' || ops.peek() == '/')) {
                        int s1 = values.pop();
                        char op = ops.pop();
                        values.push(op == '*' ? s1 * s2 : (s1 / s2));
                    } else{
                        values.push(s2);
                    }
                }
                if (s.charAt(index) != ' ') {
                    if(ops.size() > 0 && (ops.peek() == '+' || ops.peek() == '-')
                            &&(s.charAt(index) == '+' || s.charAt(index) == '-')){
                        int s2 = values.pop();
                        int s1 = values.pop();
                        char op = ops.pop();
                        values.push((op == '+' ? (s1 + s2) : (s1 - s2)));
                    }
                    ops.push(s.charAt(index));
                }
                index++;
                lastIndex = index;
            }
        }
        if (lastIndex < index) {
            values.push(Integer.parseInt(s.substring(lastIndex, index)));
        }
        while (ops.size() > 0) {
            int s2 = values.pop();
            int s1 = values.pop();
            char op = ops.pop();
            values.push(op == '*' ? s1 * s2 : op == '/' ? (s1 / s2)
                    : (op == '+' ? (s1 + s2) : (s1 - s2)));
        }
        return values.pop();
    }

    public static void main(String[] args){
        System.out.println(new BasicCalculator().calculate("14/3*2"));
    }
}
