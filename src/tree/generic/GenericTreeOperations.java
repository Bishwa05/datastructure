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
    
}
