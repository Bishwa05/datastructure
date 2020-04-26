package stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will
 * be an integer in the range [30, 100].
 *
 */
public class DailyTemperature {

    public int[] dailyTemperatures(int[] T) {
        int arr[] = new int[T.length];

        Stack<Integer> s = new Stack();
        s.add(0);

        for (int i =1; i< T.length; i++){
            int stIndex= s.peek();
            while(!s.isEmpty() && T[i]>T[stIndex]){
                arr[stIndex]=i-stIndex;
                s.pop();
                if(!s.isEmpty()) {
                    stIndex= s.peek();
                }

            }
            s.add(i);

        }

      return arr;

    }


    public static void main(String arg[]) {
        int []arr= {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperature d = new DailyTemperature();
        int[] x = d.dailyTemperatures(arr);

        for(int i: x){
            System.out.println(i);
        }
    }
}
