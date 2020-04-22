package stack;

import java.util.Stack;

/**
 * Algorithm is
 *
 * 1. Add to stack if curr val is >=top of stack
 * 2. Otherwise keep removing from stack till a smaller or equal number found.
 * 3. Calculate Area every time you remove
 *         if stack is empty
 *              area = input[top] * i
 *         else
 *              area = input[top]* (i - stack_top -1)
 */
public class MaximumHistogramArea {

    public static int histogramArea(int []  arr){
        int area =0;
        int maxArea = 0;

        Stack<Integer> s = new Stack();

        int i =0;
         while(i<arr.length) {
             if(s.isEmpty() || arr[i]>=arr[s.peek()]){
                 s.add(i);
                 i++;
             }else {
                 //while (!s.isEmpty() && arr[i]<=arr[s.peek()]) {
                     int top = s.pop();

                     if (s.isEmpty()) {
                         area = arr[top] * i;
                     } else {
                         area = arr[top] * (i - s.peek() - 1);
                     }

                     maxArea = Math.max(maxArea, area);
                // }
             }

         }

        while (!s.isEmpty()) {
            int top = s.pop();

            if (s.isEmpty()) {
                area = arr[top] * i;
            } else {
                area = arr[top] * (i - s.peek() - 1);
            }

            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String arg[]) {
        int [] arr = {2,1,2,3,1};
        //int arr[] = {6, 2, 5, 4, 5, 1, 6 };
        System.out.println(histogramArea(arr));
    }
}
