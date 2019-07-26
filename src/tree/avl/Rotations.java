package tree.avl;

public class Rotations {

    public int getHeight(AVLTreeNode root) {
        if(root == null)
            return 0;

        int leftHeight = getHeight(root.getLeft());
        int rightHeight = getHeight(root.getRight());
        return (leftHeight> rightHeight)? leftHeight+1: rightHeight+1;
    }

    /**
     *                  x                              w
     *              w       c       -->          a          x
     *           a      b                               b       c
     *  single left rotation
     *
     */
    public AVLTreeNode rotateLeft(AVLTreeNode x) {
        AVLTreeNode w = x.getLeft();
        x.setLeft(w.getRight());
        w.setRight(x);
        x.setHeight(Math.max(getHeight(x.getLeft()),
                getHeight(x.getRight()))+1);
        w.setHeight(Math.max(getHeight(w.getLeft()),
                getHeight(x))+1);
        return w;
    }


    /**
     *                  w                              x
     *              a       x       -->            w        c
     *                   b     c               a       b
     *  single right rotation
     *
     */
    public AVLTreeNode rotateRight(AVLTreeNode w) {
        AVLTreeNode x = w.getRight();
        w.setRight(x.getLeft());
        x.setLeft(w);
        w.setHeight(Math.max(getHeight(w.getLeft()),
                getHeight(w.getRight()))+1);
        x.setHeight(Math.max(getHeight(w),
                getHeight(x.getRight()))+1);
        return w;
    }


    /**
     *                                              right rotation at y
     *                  z                                   z
     *          x               d     -->           y               d
     *      a       y                           x       c
     *          b       c                    a      b
     *                                                     |
     *                                                     |
     *                            y                     <--
     *                     x               z
     *                  a      b        c       b
     *                  left rotation at z
     *   LR Rotation (Left Right Rotation)
     */
    public AVLTreeNode lrRotation(AVLTreeNode z) {
        z.setLeft(rotateRight(z.getLeft()));
        return rotateLeft(z);
    }



    public AVLTreeNode rlRotation(AVLTreeNode z) {
        z.setRight(rotateLeft(z.getRight()));
        return rotateRight(z);
    }


}
