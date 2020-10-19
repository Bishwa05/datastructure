package stack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * push/pop/peek/get min/get max operations in O(1).
 */
class StackNode{
    int val;
    int min;
    int max;

    StackNode(int val, int min, int max){
        this.val = val;
        this.min = min;
        this.max = max;
    }
}


public class MinMaxStack
{

    List<StackNode> list;
    // int index;
    int min;
    int max;
    MinMaxStack(){
        list = new ArrayList<>();
        // index = -1;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    public void push(int val){
        // index++;
        if(val>max) max = val;
        if(val<min) min = val;
        StackNode s = new StackNode(val, min, max);
        list.add(s);
    }

    public int pop(){
        StackNode s1 = list.get(list.size()-1);
        list.remove(list.size()-1);
        StackNode s = list.get(list.size()-1);
        min = s.min;
        max = s.max;
        return s1.val;
    }

    public int peek(){
        StackNode s = list.get(list.size()-1);
        return s.val;
    }

    public int getMin(){
        StackNode s = list.get(list.size()-1);
        return s.min;
    }

    public int getMax(){
        StackNode s = list.get(list.size()-1);
        return s.max;
    }


    public static void main(String arg[]){
        MinMaxStack m = new MinMaxStack();
        m.push(2);
        m.push(5);
        System.out.println(m.peek()+"::"+m.getMax()+"::"+m.getMin());
        m.push(7);
        m.push(9);
        System.out.println(m.peek()+"::"+m.getMax()+"::"+m.getMin());
        m.pop();
        m.push(1);
        System.out.println(m.peek()+"::"+m.getMax()+"::"+m.getMin());
    }
}
