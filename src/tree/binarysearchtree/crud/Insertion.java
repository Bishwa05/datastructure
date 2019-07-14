package tree.binarysearchtree.crud;

import tree.binarysearchtree.BSTNode;

public class Insertion {

    /**
     * Insert a node in BST in iterative way
     * Space O(1), time O(n)
     * @param root
     * @param data
     */
    public void insertItr(BSTNode root, int data) {
        BSTNode prevRoot = root;
        if (root == null) {
            root = new BSTNode(data);
        } else {
            while (data > root.getData()) {
                prevRoot = root;
                root = root.getRight();
            }
            while (data < root.getData()) {
                prevRoot = root;
                root = root.getLeft();
            }
            BSTNode newNode = new BSTNode(data);
            if (data < prevRoot.getData())
                prevRoot.setLeft(newNode);
            else
                prevRoot.setRight(newNode);

        }
    }

        /**
         *Insert a node in BST in recursively way
         * Space O(n), time O(n)
         */
        public BSTNode insertRec(BSTNode root, int data){
            BSTNode prevRoot = root;
            if (root == null) {
                root = new BSTNode(data);
            } else {
                if(data < root.getData()) {
                    root.setLeft(insertRec(root.getLeft(), data));
                }else if(data> root.getData()){
                    root.setRight(insertRec(root.getRight(), data));
                }

            }
            return root;
        }

}
