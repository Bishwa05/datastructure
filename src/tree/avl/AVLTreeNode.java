package tree.avl;

import tree.binarysearchtree.BSTNode;

public class AVLTreeNode extends BSTNode {
    private int height;

    public AVLTreeNode() {

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
