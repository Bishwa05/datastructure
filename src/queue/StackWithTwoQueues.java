package queue;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues<T> {

    private Queue<T> q1 = new LinkedList<>();
    private Queue<T> q2 = new LinkedList<>();

    public void push(T data){
        if(q1.isEmpty())
            q2.offer(data);
        else
            q1.offer(data);
    }

    public T pop() {
        int i=0, size;

        if(q2.isEmpty()){
            size = q1.size();
            while(i< size-1) {
                q2.offer(q1.poll());
                i++;
            }
            return q1.poll();
        } else {
            size = q2.size();
            while(i<size-1) {
                q1.offer(q2.poll());
                i++;
            }
        }
        return q2.poll();
    }

}
