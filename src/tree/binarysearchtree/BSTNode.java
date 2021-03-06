package tree.binarysearchtree;

/**
 * The properties are
 *  1. The left subtree of a node contains only nodes with keys less than the nodes key.
 *  2. The right subtree of a node contains only nodes with keys greater than the nodes key.
 *  3. Both the left and right subtrees must also be Binary Search Trees.
 */
public class BSTNode {
    public int data;
    public BSTNode left;
    public BSTNode right;

    public BSTNode () {
    }

    public BSTNode (int data) {
        this.data= data;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
