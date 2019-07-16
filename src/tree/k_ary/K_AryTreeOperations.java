package tree.k_ary;

public class K_AryTreeOperations {
    int index =0;
    /**
     * Build a K_AryTree
     *
     */
    public K_AryTreeNode buildK_AryTree(int A[], int k, int n){


        if(n <=0)
            return null;
        K_AryTreeNode newNode = new K_AryTreeNode(k);

        if(newNode == null){
            System.out.println("Memory Error");
            return null;
        }
        newNode.setData(A[index]);

        for(int i=0; i<k; i++){
            if(k*index+i<n){
                index++;
                newNode.setChild(index, buildK_AryTree(A, n, k));
            } else {
                newNode.setChild(index,null);
            }
        }
        return newNode;

    }
}
