package tree.generic;

public class GenericTreeOperations {

    /**
     * Find the sum of all elements of the tree
     * - traverse the tree and keep adding
     */
    public int findSum(GenericTreeNode root) {
        if(root == null) return 0;
        return root.getData() + findSum(root.getFirstChild())
                + findSum(root.getNextSibling());
    }

    /**
     * Given a node of a generic tree find the number of sibling
     */
    public int siblingCount(GenericTreeNode current) {
        int count =0;
        while(current != null){
            count++;
            current = current.getNextSibling();
        }
        return count;
    }


    /**
     * Given a node of GenericTree, count the number of children for that node
     */
    public int childCount(GenericTreeNode current){
        int count =0;
        current = current.getFirstChild();
        while(current != null){
            count++;
            current = current.getNextSibling();
        }
        return count;
    }

}
