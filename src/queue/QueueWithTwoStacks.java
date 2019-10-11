package queue;

import java.util.Stack;

/**
 * Implement a queue using 2 stack
 * @param <T>
 */
public class QueueWithTwoStacks<T> {

    private Stack<T> s1 = new Stack<T>();
    private Stack<T> s2 = new Stack<T>();

    public void enqueue(T data) {
        s1.push(data);
    }

    public T deque() {
        if(s2.empty()){
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

}
