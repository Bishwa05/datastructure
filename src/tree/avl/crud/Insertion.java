package tree.avl.crud;

import tree.avl.AVLTreeNode;
import tree.avl.Operations;
import tree.avl.Rotations;
import tree.binarysearchtree.BSTNode;

public class Insertion {

    /**
     * The insertion is similar to BST insertion.
     * After inserting an element if there is any height imbalance
     * Then do appropriate rotation.
     *
     * Time Complexity O(n), Space Complexity O(logn)
     *
     */
    public AVLTreeNode  insert(AVLTreeNode root,AVLTreeNode parent, int data) {
        if(root == null) {
            root = new AVLTreeNode();
            root.setData(data);
            root.setHeight(0);
            root.setLeft(null);
            root.setRight(null);
        } else if(data <root.getData()) {
            root.setLeft(insert(root.getLeft(),root,data));
            if(Math.abs(root.getLeft().getHeight() -
                    root.getRight().getHeight()) ==2){
                if(data <root.getLeft().getData())
                    root = Rotations.rotateLeft(root);
                else
                    root = Rotations.lrRotation(root);
            }
        } else if(data > root.getData()) {
            root.setRight(insert(root.getRight(),root,data));
            if(Math.abs(root.getRight().getHeight() -
                    root.getLeft().getHeight()) ==2){
                if(data <root.getRight().getData())
                    root = Rotations.rotateRight(root);
                else
                    root = Rotations.rlRotation(root);
            }
        }

        // Else data is in the tree already, we will do nothing
        root.setHeight(Math.max(root.getLeft().getHeight(),
                root.getRight().getHeight())+1);
        return root;
    }

    /**
     *
     * HBO is full binary tree, in full Binary Tree number of nodes with h
     * are 2^(h+1)-1.
     * Time complexity O(n).
     * Space complexity O(logn). logn indicates maximum stack size which is
     * equal to height of the tree.
     */
    int count =0;
    public BSTNode buildHBO(int h){
        BSTNode temp;
        if(h ==0)
            return null;

        temp = new BSTNode();
        temp.setLeft(buildHBO(h-1));
        temp.setData(count++);
        temp.setRight(buildHBO(h-1));
        return temp;
    }

    /**
     * Instead of height we can take range and build the tree.
     * Time complexity O(n).
     * Space complexity O(logn).
     */
    public BSTNode buildHBO(int l, int r){
        BSTNode temp;
        int mid = l+ (r-l)/2;
        if(l>r)
            return null;
        temp = new BSTNode(mid);
        temp.setLeft(buildHBO(l,mid-1));
        temp.setRight(buildHBO(mid+1,r));
        return temp;
    }

    /**
     * Given a height h, give an algorithm for generating an AVL Tree
     * with minimum number of nodes
     */
     //count=0;
    public AVLTreeNode generateAVLTree(int h) {
        AVLTreeNode temp;
        if(h==0)
            return null;
        temp = new AVLTreeNode();
        temp.setLeft(generateAVLTree(h-1));
        temp.setData(count++);
        temp.setRight(generateAVLTree(h-2));
        temp.setHeight(temp.getLeft().getHeight()+1);
        return temp;
    }

    /**
     * Given an AVL Tree with n integer items and 2 integers a and b.
     * Where a<= b.
     * Give an algorithm to find number of nodes in the range [a,b]
     */
    public int rangeCount(AVLTreeNode root, int a, int b) {
        if(root == null)
            return 0;
        else if(root.getData()> b)
            return rangeCount(root.getLeft(), a,b);
        else if(root.getData() < a)
            return rangeCount(root.getRight(), a, b);
        else if(root.getData()>=a && root.getData()<= b)
            return rangeCount(root.getLeft(),a,b)+
                    rangeCount(root.getRight(),a,b)+1;
        return 0;
    }
}
