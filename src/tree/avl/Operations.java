package tree.avl;

import tree.binarysearchtree.BSTNode;

public class Operations {

    /**
     * Searing in avl tree becomes O(h), where h is the height of avl tree.
     */
    public int height(AVLTreeNode root) {
        if(root == null)
            return -1;
        else
            return root.getHeight();
    }

    /**
     * Given a BST, check whether it is AVLTree or not
     */
    public boolean isAVLTree(BSTNode root) {
        if(root == null)
            return true;
        return isAVLTree(root.getLeft()) &&
                isAVLTree(root.getRight()) &&
                Math.abs(getHeight(root.getLeft())-getHeight(root.getRight())) <=1;
    }


    public int getHeight(BSTNode root) {
        if(root == null)
            return 0;

        int leftHeight = getHeight(root.getLeft());
        int rightHeight = getHeight(root.getRight());
        return (leftHeight> rightHeight)? leftHeight+1: rightHeight+1;
    }

    /**
     * Construct a minimal AVL Trees of height 0,1,2,3,4 and 5.
     * What is the number of nodes in a minimal AVL Tree of height 6.
     *
     * N(0) =1
     * N(1) = 2
     * N(h) = 1+ N(h-1) + N(h-2)
     * N(2) = 1+ N(1) + N(0) = 1+2+1 = 4
     * N(3) = 1+ N(2) + N(1) = 1+4+2 = 7
     * N(4) = 1+ N(3) + N(2) = 1+7+4 = 12
     * N(5) = 1+ N(4) + N(3) = 1+12+7 = 20
     *
     */


    /**
     * How many different shapes can there be of minimal AVL Tree of height
     * NS(0) = 1
     * NS(1) = 2
     * NS(2) = 2* NS(1) * NS(0) = 2* 2* 1
     * NS(3) = 2* NS(2) * NS(1) = 2* 4* 1
     * NS(h) = 2* NS(h-1) * NS(h-2)
     *
     */


}
