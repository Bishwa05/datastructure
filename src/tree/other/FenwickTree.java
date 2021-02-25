package tree.other;

public class FenwickTree
{
    /**
     * To get parent
     * 1. 2's complement to get minus of index
     * 2. AND this with index
     * 3. subtract that from index
     */
    private int getParent(int index) {
        return index - (index & -index);
    }

    /**
     * To get next
     * 1. 2's complement to get minus of index
     * 2. AND this with index
     * 3. add it to index
     */
    private int getNext(int index){
        return index + (index & -index);
    }

    public int getSum(int binaryIndexedTree[], int index){
        index = index +1;
        int sum = 0;
        while(index >0){
            sum += binaryIndexedTree[index];
            index = getParent(index);
        }
        return sum;
    }

    public void updateBinaryIndexTree(int binaryIndexedTree[],
                                      int val, int index){

        while(index < binaryIndexedTree.length){
            binaryIndexedTree[index] += val;
            index = getNext(index);
        }
    }

    public int[] createTree(int input[]){
        int binaryIndexedTree[] = new int[input.length+1];

        for(int i =1; i<= input.length; i++){
            updateBinaryIndexTree(binaryIndexedTree, input[i-1], i);
        }
        return binaryIndexedTree;
    }


    public static void main(String args[]){
        int input[] = {1,2,3,4,5,-5, 6,7,8,9};
        FenwickTree f = new FenwickTree();
        int[] binaryIndexedTree = f.createTree(input);
        System.out.println(1== f.getSum(binaryIndexedTree, 0));
        System.out.println(10 == f.getSum(binaryIndexedTree, 3));
        System.out.println(10 == f.getSum(binaryIndexedTree, 5));
    }

}
