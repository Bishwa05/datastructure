package tree.binarysearchtree.crud;

import tree.binarysearchtree.BSTNode;
import tree.binarysearchtree.Searching;

public class Deletion {

    /**
     * 3 scenarios
     *  1. If the element to be deleted is a leaf node.
     *         -- return null to its parent
     *  2. If the element to be deleted has 1 child.
     *         -- return current nodes child to its parent node.
     *  3. If the element to be deleted has 2 child.
     *         -- Here minimum 2 deletion required .
     *              a. replace the current node with right most node of left subtree
     *                  or left most node of right subtree
     *              b. the node which will act a replacement that gets deleted this
     *                  deletion may fall under any of the above 3 category. It may
     *                  go for a cycle for sometime.
     *
     *              The above point provides a genuine reason that we should go for the
     *              solution in a recursive manner.
     *
     *
     *   NOTE: The below code might not consider all the scenarios mentioned above.
     *   We will enhance it.
     *
     */

    public BSTNode delete(BSTNode root, int data) {
        BSTNode temp;
        if(root == null){
            System.out.println("Element is not present");
        }
        if(data < root.getData()){
            root.setLeft(delete(root.getLeft(), data));
        } else if (data> root.getData()) {
            root.setRight(delete(root.getRight(), data));
        } else {
            if(root.getLeft()!=null && root.getRight()!= null){
                //Replace with largest in left sub tree,
                // This logic is not true always if we have no right node in left sub tree
                temp = Searching.findMax(root.getLeft());
                root.setData(temp.getData());
                root.setLeft(delete(root.getLeft(), root.getData()));
            } else {
                //One child
                temp = root;
                if(root.getLeft() == null)
                    root = root.getRight();
                if(root.getRight() == null)
                    root = root.getLeft();
                System.out.println("deleted node : "+temp);
            }
        }
        return root;
    }

    /**
     * Given a BST and 2 integer the ask is to delete the nodes which are not in the
     * range of the given 2 integers
     *
     *               49                                    49
     *          37        89                          37        53
     *       13     41  53                        25     41         71
     *     7    19          71                                   60
     *              25    60    82                  prune(24, 71)
     *
     *        prune(53,79)              prune(17, 41)
     *            53                        37
     *                  71              19        41
     *              60                      25
     */
    public BSTNode pruneBST(BSTNode root, int a, int b) {
        if(root == null)
            return null;
        root.setLeft(pruneBST(root.getLeft(), a, b));
        root.setRight(pruneBST(root.getRight(), a, b));

        if(a <= root.getData() && root.getData()<= b)
            return root;
        if(root.getData()<a)
            return root.getRight();
        if(root.getData()>b)
            return root.getLeft();
        return null; // This will not be reachable in logic.
    }
}
