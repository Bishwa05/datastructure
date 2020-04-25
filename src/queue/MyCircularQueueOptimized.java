package queue;

public class MyCircularQueueOptimized {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    int[] q;
    int start=0, end=0, len, count=0;
    public MyCircularQueueOptimized(int k) {
        q=new int[k];
        len=k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if((end+1)%len == start) return false;
        if(count==0) {
            q[end]=value;
        }
        else{
            end=(end+1)%len;
            q[end] = value;
        }
        count++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(count==0) return false;
        count--;
        if(count!=0) start++;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(count==0) return -1;
        return q[start];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(count==0) return -1;
        return q[end];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(count==0) return true;
        return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if(count==len) return true;
        return false;
    }
}
