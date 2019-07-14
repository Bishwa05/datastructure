package tree.binarysearchtree;

public class Searching {
    /**
     * Find an element in Binary Search Tree
     * Time O(n) (when BST is a skew tree)
     * Space O(n)
     */
    public BSTNode findRec(BSTNode root, int data) {
        if (root == null)
            return null;
        if(data < root.getData())
            return findRec(root.getLeft(), data);
        else if(data > root.getData())
            return findRec(root.getRight(), data);
        return root;
    }

    /**
     * Time O(n) (when BST is a skew tree)
     * Space O(1)
     * @param root
     * @param data
     * @return
     */
    public BSTNode findItr(BSTNode root, int data) {
        if (root == null)
            return null;
        while(root != null){
            if(data == root.getData())
                return root;
            else if(data > root.getData())
                root = root.getRight();
            else
                root = root.getLeft();
        }
        return root;
    }

    /**
     * Find minimum element in BST
     * Time O(n), in worst case only for skew tree
     * Space O(n)
     */
    public BSTNode findMinRec(BSTNode root) {
        if(root == null)
            return null;
        else if(root.getLeft() == null)
            return root;
        else
            return findMinRec(root.getLeft());
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(1)
     * @param root
     * @return
     */
    public BSTNode findMinItr(BSTNode root) {
        if(root == null)
            return null;
        while(root.getLeft() != null)
            root = root.getLeft();
        return root;
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(n)
     * @param root
     * @return
     */
    public BSTNode findMaxRec(BSTNode root) {
        if(root == null)
            return null;
        else if(root.getRight() == null)
            return root;
        else
            return findMaxRec(root.getRight());
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(1)
     * @param root
     * @return
     */
    public BSTNode findMax(BSTNode root) {
        if(root == null)
            return null;
        while(root.getRight() != null)
            root= root.getRight();
        return root;
    }

}
