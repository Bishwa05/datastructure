package heap;

public class Heap {
    public int[] array;
    public int count;               //Number of elements in heap
    public int capacity;            //size of the heap
    public int heapType;            //Min heap or Max heap

    public Heap(int capacity, int heapType) {
        this.heapType = heapType;
        this.count =0;
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public int parent(int i) {
        if(i<=0 || i>= this.count) {
            return -1;
        }
        return (i-1)/2;
    }

    public int leftChild(int i){
        int left = 2*i +1;
        if(left >= this.count)
            return -1;
        return left;
    }

    public int rightChild(int i){
        int right = 2*i +2;
        if(right >= this.count)
            return -1;
        return right;
    }

    //Only correct for max heap
    public int getMaximum(int i){
        if(this.count ==0)
            return -1;
        return this.array[0];
    }
}
