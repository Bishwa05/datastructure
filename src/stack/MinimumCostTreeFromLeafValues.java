package stack;

import java.util.Stack;

/**
 * Leetcode
 * 1130. Minimum Cost Tree From Leaf Values
 */
public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : arr) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }

    public static void main(String arg[]) {
        int [] arr = {6,2,4};
        MinimumCostTreeFromLeafValues m = new MinimumCostTreeFromLeafValues();
        System.out.println(m.mctFromLeafValues(arr));
    }
}
