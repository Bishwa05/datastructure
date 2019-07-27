package heap.crud;

import heap.Heap;

public class MaxHeap {

    /**
     * copy the first element into a variable
     * copy the last element into first element
     * percolateDown the first element
     * @return
     */
    public int deleteMax(Heap h) {
        if(h.count ==0)
            return -1;
        int data = h.array[0];
        h.array[0] = h.array[h.count-1];
        h.count--;
        percolateDown(h,0);
        return data;
    }

    /**
     * Heapify the element at location i (top to bottom)
     */
    public void percolateDown(Heap h, int i){
        int l, r, max, temp;
        l = h.leftChild(i);
        r = h.rightChild(i);

        if(i!= -1 && h.array[l] > h.array[i])
            max = l;
        else
            max = i;
        if(r != -1 && h.array[r] > h.array[max])
            max = r;

        //Swap arr[i] and arr[max]
        if(max != i) {
            temp = h.array[i];
            h.array[i] = h.array[max];
            h.array[max] = temp;
        }

        percolateDown(h, max);
    }


    /**
     *  Time complexity is O(logn)
     * @param h
     * @param data
     */
    public void insert(Heap h, int data) {
        int i;
        if(h.count == h.capacity)
            resizeHeap(h);
        h.count++;
        i = h.count-1;
        percolateUp(h,i, data);
    }

    public void percolateUp(Heap h, int pos, int data) {
        while(pos>=0 && data > h.array[(pos-1)/2]){
            h.array[pos] = h.array[(pos-1)/2];
            pos = (pos-1)/2;
        }
        h.array[pos]= data;
    }

    public void resizeHeap(Heap h) {
        int[] arrOld = new int[h.capacity];
        System.arraycopy(h.array, 0, arrOld,0,h.count-1);
        h.array = new int[h.capacity *2];
        if(h.array == null) {
            System.out.println("Memory allocation error");
            return;
        }
        for(int i=0; i<h.capacity; i++)
            h.array[i] = arrOld[i];
        h.capacity *=2;
    }

    public void destroyHeap(Heap h) {
        h.count =0;
        h.array = null;
    }


    public void buildHeap(Heap h, int[] A, int n){
        if(h == null)
            return;
        while(n > h.capacity)
            resizeHeap(h);

        for(int i =0; i<n; i++)
            h.array[i] = A[i];

        h.count = n;

        for(int i = (n-1)/2; i>=0; i++)
            percolateDown(h,i);
    }

    public void heapSort(int[] A, int n){
        Heap h = new Heap(n,0);
        int oldSize, i, temp;
        buildHeap(h, A, n);
        oldSize = h.count;

        for(i = n-1; i>0; i--){
            temp = h.array[0];
            h.array[0] = h.array[h.count-1];
            h.count--;
            percolateDown(h,0);
        }

        h.count = oldSize;
    }

}
