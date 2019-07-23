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


}
