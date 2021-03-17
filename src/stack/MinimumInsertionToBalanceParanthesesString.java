package stack;
/**
 * Leetcode
 * 1541. Minimum Insertions to Balance a Parentheses String
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')' at the end of the string to be "(())))" which is balanced.
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 */

import java.util.Stack;

public class MinimumInsertionToBalanceParanthesesString
{
    public int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        int count =0;
        int n = s.length();

        for(int i=0; i<s.length();){
            char c = s.charAt(i);

            if(c =='(') {
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    if(i +1<n && s.charAt(i)== s.charAt(i+1)){
                        count++;
                        i++;
                    } else if(i+1<n && s.charAt(i)!= s.charAt(i+1)){
                        count +=2;
                    } else if(i+1 == n){
                        count+=2;
                    }
                } else {
                    if(i+1<n && s.charAt(i) == s.charAt(i+1)){
                        stack.pop();
                        i++;
                    } else if(i+1 <n && s.charAt(i) != s.charAt(i+1)){
                        count++;
                        stack.pop();
                    } else if(i+1 == s.length()){
                        count++;
                        stack.pop();
                    }
                }
            }
            i++;
        }

        if(stack.size() != 0){
            count += stack.size()*2;
        }
        return count;
    }

    public static void main(String arg[]){
        String s = ")))))))";
        MinimumInsertionToBalanceParanthesesString m = new MinimumInsertionToBalanceParanthesesString();
        System.out.println(m.minInsertions(s));
    }
}
