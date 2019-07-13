package tree.binarytree.threaded;

/**
 * Issues with regular Binary Tree Traversal
 *  -> The storage space required for stack and queue is very large
 *  -> The majority of pointers in binary tree are null and these are waste of memory.
 *  -> It is difficult to find successor node(preorder, inorder and postorder succesors) for a given node
 *
 * Left threaded Binary Tree -> If we store predecessor information only in NULL left pointers
 * Right threaded Binary Tree -> If we store successor information in NULL right pointers
 * Fully threaded Binary Tree or Simply threaded Binary Tree -> If we store both the above information
 *
 *
 * PreOrderThreadedBinaryTree -> NULL left pointer will contain preorder predecessor information
 * and NULL right pointer will contain preorder successor information
 *
 * InOrder ThreadedBinaryTree ->  NULL left pointer will contain inorder predecessor information
 * and NULL right pointer will contain inorder successor information
 *
 * PostOrder ThreadedBinaryTree ->  NULL left pointer will contain postorder predecessor information
 * and NULL right pointer will contain postorder successor information
 *
 */

public class ThreadedBinaryTreeNode {

    private ThreadedBinaryTreeNode left;
    private int lTag;
    private int data;
    private int rTag;
    private ThreadedBinaryTreeNode right;

    public ThreadedBinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedBinaryTreeNode left) {
        this.left = left;
    }

    public int getlTag() {
        return lTag;
    }

    public void setlTag(int lTag) {
        this.lTag = lTag;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getrTag() {
        return rTag;
    }

    public void setrTag(int rTag) {
        this.rTag = rTag;
    }

    public ThreadedBinaryTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedBinaryTreeNode right) {
        this.right = right;
    }
}
