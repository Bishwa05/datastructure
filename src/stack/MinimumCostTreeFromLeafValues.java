package stack;

import java.util.Stack;

/**
 * Leetcode
 * 1130. Minimum Cost Tree From Leaf Values
 *
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left
 * and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values
 * of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 *
 *
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
