package stack;

/**
 *
 * Leetcode 503. Next Greater Element II
 *
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 *
 */

import java.util.Stack;

public class NextGreaterElementII
{
    public int[] nextGreaterElements(int[] nums) {

        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for(int i =2* nums.length -1; i>=0; --i){
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i% nums.length]){
                stack.pop();
            }
            res[i%nums.length] = stack.empty()?-1: nums[stack.peek()];
            stack.push(i%nums.length);
        }
        return res;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack();
        for(int i = n - 1; i >= 0; i--){
            stack.push(i);
        }

        int[] res = new int[n];
        for(int i = n - 1; i >= 0; i--){
            res[i] = -1;
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]){
                stack.pop();
            }

            if(!stack.isEmpty()) res[i] = nums[stack.peek()];
            stack.push(i);
        }
        return res;
    }

}
