package stack;

import java.util.Stack;

/**
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack();

        for(String str : tokens){

            if(str.equals("+")){
                int a = s.pop();
                int b = s.pop();
                s.push(a+b);
            } else if(str.equals("-")){
                int a = s.pop();
                int b = s.pop();
                s.push(a-b);
            }else if(str.equals("*")){
                int a = s.pop();
                int b = s.pop();
                s.push(a*b);
            }else if(str.equals("/")){
                int a = s.pop();
                int b = s.pop();
                s.push(a/b);
            } else{
                s.push(Integer.parseInt(str));
            }
        }
        return s.peek();
    }

    public static void main(String arg[]){
        EvaluateReversePolishNotation e = new EvaluateReversePolishNotation();
        String[] tokens ={"2", "1", "+", "3", "*"};
        System.out.println(e.evalRPN(tokens));
    }
}
