package queue;

/**
 *
 * A more efficient way is to use a circular queue.
 * Specifically, we may use a fixed-size array and two pointers to indicate the
 * starting position and the ending position. And the goal is to reuse the wasted
 * storage we mentioned previously.
 *
 * Queue empty condition : head == tail (pointers)
 * Queue full condition : head == tail+1
 *
 */
public class MyCircularQueue {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    int []arr;
    int front;
    int rear;
    public MyCircularQueue(int k) {
        arr = new int[k];
        front =-1;
        rear=0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }

        arr[rear++] = value;

        if(front == -1) {
            front = 0;
        }
        if(rear==arr.length){
            rear =0;
        }
        return true;
    }


    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }

        front++;
        if(front == arr.length){
            front=0;
        }

        if(front == rear){
            front=-1;
            rear =0;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(!isEmpty())
        return arr[front];
        return -1;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(!isEmpty()){
            int index = rear-1;
            if(index == -1) {
                index = arr.length-1;
            }
            return arr[index];
        }

        return -1;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return front == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return front==rear;
    }

    public static void main(String arg[]){
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3

        System.out.println(circularQueue.enQueue(1));  // return true
        System.out.println(circularQueue.enQueue(2));  // return true
        System.out.println(circularQueue.enQueue(3));  // return true
        System.out.println(circularQueue.enQueue(4));  // return false, the queue is full
        System.out.println(circularQueue.Rear());  // return 3
        System.out.println(circularQueue.isFull());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.enQueue(4));  // return true
        System.out.println(circularQueue.Rear());  // return 4
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());


//        System.out.println(circularQueue.enQueue(1));  // return true
//        System.out.println(circularQueue.enQueue(2));  // return true
//        System.out.println(circularQueue.enQueue(3));
//        System.out.println(circularQueue.deQueue());
//        System.out.println(circularQueue.deQueue());
//        System.out.println(circularQueue.deQueue());
//        System.out.println(circularQueue.enQueue(3));
//        System.out.println(circularQueue.Rear());
//        System.out.println(circularQueue.isFull());



    }
}
