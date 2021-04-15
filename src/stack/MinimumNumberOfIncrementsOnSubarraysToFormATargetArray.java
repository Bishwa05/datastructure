package stack;

import java.util.Stack;

/**
 *
 * Leetcode 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 *
 * Given an array of positive integers target and an array initial of same size with all zeros.
 *
 * Return the minimum number of operations to form a target array from initial if you are allowed to do the following operation:
 *
 * Choose any subarray from initial and increment each value by one.
 *
 *
 * Example 1:
 *
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * Explanation: We need at least 3 operations to form the target array from the initial array.
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * [1,2,2,2,1] increment 1 at index 2.
 * [1,2,3,2,1] target array is formed.
 * Example 2:
 *
 * Input: target = [3,1,1,2]
 * Output: 4
 * Explanation: (initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target).
 *
 *
 */
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray
{

    public int minNumberOperations(int[] target) {
        int res =0;
        Stack<Integer> stack = new Stack<>();

        for(int i =0; i<target.length; i++){
            int tmp =0;
            if(!stack.isEmpty() && stack.peek()> target[i]){
                tmp = stack.peek() - target[i];
                while(!stack.isEmpty() && stack.peek()> target[i])
                    stack.pop();
            }
            res+=tmp;
            stack.push(target[i]);
        }
        if(!stack.isEmpty()) res+=stack.peek();
        return res;
    }


    public int minNumberOperations2(int[] target)
    {
        int sum = 0;
        int curr = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] > curr) {
                sum += (target[i] - curr);
            }
            curr = target[i];
        }
        return sum;
    }


    public static void main(String arg[]){
        MinimumNumberOfIncrementsOnSubarraysToFormATargetArray m = new
            MinimumNumberOfIncrementsOnSubarraysToFormATargetArray();

        //int []nums = {1,2,3,2,1};
        int []nums = {3,1,1,2};
        System.out.println(m.minNumberOperations2(nums));
    }
}
