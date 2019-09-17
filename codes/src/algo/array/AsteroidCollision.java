package algo.array;

import java.util.Stack;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int a : asteroids){
            if(a > 0){
                stack.add(a);
            }else{
                while (!stack.isEmpty()){
                    if(stack.peek() < a){
                        stack.pop();
                    }else if(stack.peek() == a){
                        stack.pop();
                        break;
                    }else{
                        break;
                    }
                }
            }
        }
        int[] res = new int[stack.size()];
        for(int i = 0; i < res.length; i ++){
            res[i] = stack.pop();
        }
        return res;
    }
}
