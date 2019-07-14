package tree.binarysearchtree.crud;

import tree.binarysearchtree.BSTNode;

public class Insertion {

    /**
     * Insert a node in BST in iterative way
     * @param root
     * @param data
     */
    public void insertItr(BSTNode root, int data) {
        BSTNode prevRoot = root;
        if(root == null) {
            root = new BSTNode(data);
        } else {
           while(data>root.getData()) {
               prevRoot = root;
               root = root.getRight();
           }
           while (data < root.getData()) {
               prevRoot = root;
               root = root.getLeft();
           }
           BSTNode newNode = new BSTNode(data);
           if(data<prevRoot.getData())
               prevRoot.setLeft(newNode);
           else
               prevRoot.setRight(newNode);

        }


        /**
         *Insert a node in BST in recursively way
         */

    }
}
