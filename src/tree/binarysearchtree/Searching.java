package tree.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

public class Searching {
    /**
     * Find an element in Binary Search Tree
     * Time O(n) (when BST is a skew tree)
     * Space O(n)
     */
    public BSTNode findRec(BSTNode root, int data) {
        if (root == null)
            return null;
        if(data < root.getData())
            return findRec(root.getLeft(), data);
        else if(data > root.getData())
            return findRec(root.getRight(), data);
        return root;
    }

    /**
     * Time O(n) (when BST is a skew tree)
     * Space O(1)
     * @param root
     * @param data
     * @return
     */
    public BSTNode findItr(BSTNode root, int data) {
        if (root == null)
            return null;
        while(root != null){
            if(data == root.getData())
                return root;
            else if(data > root.getData())
                root = root.getRight();
            else
                root = root.getLeft();
        }
        return root;
    }

    /**
     * Find minimum element in BST
     * Time O(n), in worst case only for skew tree
     * Space O(n)
     */
    public BSTNode findMinRec(BSTNode root) {
        if(root == null)
            return null;
        else if(root.getLeft() == null)
            return root;
        else
            return findMinRec(root.getLeft());
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(1)
     * @param root
     * @return
     */
    public BSTNode findMinItr(BSTNode root) {
        if(root == null)
            return null;
        while(root.getLeft() != null)
            root = root.getLeft();
        return root;
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(n)
     * @param root
     * @return
     */
    public BSTNode findMaxRec(BSTNode root) {
        if(root == null)
            return null;
        else if(root.getRight() == null)
            return root;
        else
            return findMaxRec(root.getRight());
    }

    /**
     * Time O(n), in worst case only for skew tree
     * Space O(1)
     * @param root
     * @return
     */
    public static BSTNode findMax(BSTNode root) {
        if(root == null)
            return null;
        while(root.getRight() != null)
            root= root.getRight();
        return root;
    }

    /**
     * Find the LeastCommonAncestor
     */
    public BSTNode lca(BSTNode root,BSTNode t1, BSTNode t2) {
        if(root == null){
            return root;
        }
        if(root == t1 || root ==t2)
            return root;
        if(Math.max(t1.getData(), t2.getData())< root.getData())
            return lca(root.getLeft(),t1,t2);
        if(Math.min(t1.getData(), t2.getData())> root.getData())
            return lca(root.getRight(), t1, t2);
        else
            return root;

    }

    /**
     * Check the given tree is BST or not.
     * parent =0
     */
    public boolean isBST(BSTNode root, int parent){
        if(root == null)
            return true;
        if(!isBST(root.getLeft(),parent))
            return false;
        if(root.getData() < parent)
            return false;
        parent = root.getData();
        return isBST(root.getRight(), parent);
    }

    /**
     * Another simple approach is do inorder traversal and put the elements in a list
     * After that loop through the list and check if that is sorted.
     */


    /**
     * Convert a BST to Circular Linked list
     * Space O(1),
     */
    public BSTNode bstToDll(BSTNode root, BSTNode ltail){
        BSTNode left, ltail, right, rtail;

        if(root == null){
            ltail = null;
            return null;
        }
        left = bstToDll(root.getLeft(),ltail);
        right = bstToDll(root.getRight(),rtail);
        root.setLeft(ltail);
        root.setRight(rtail);

        if(right == null)
            ltail = root;
        else {
            right.setLeft(root);
            ltail = rtail;
        }

        if(left == null)
            return root;
        else {
            ltail.setRight(root);
            return left;
        }

    }

    /**
     * Find kth smallest element in a BST.
     * Compare while doing Inorder traversal
     * Time Complexity O(n) Space Complexity O(n)
     */
    public BSTNode kthSmallestNode(BSTNode root, int k, int count) {
        if(root == null)
            return null;
        BSTNode left = kthSmallestNode(root.getLeft(), k, count);
        if(left != null)
            return left;
        if(++count == k)
            return root;
        return kthSmallestNode(root.getRight(),k,count);
    }

    /**
     * Find floor and ceil of a number present in BST
     * [1,2,4,7,10,15], X = 8 floor 7 ceil 10
     */
    public BSTNode floorInBSTUtil(BSTNode root, BSTNode prev, int data){
        if(root == null)
            return null;
        if(floorInBSTUtil(root.getLeft(), prev, data) !=null)
            return 0;
        if(root.getData() == data)
            return root;
        if(root.getData()> data)
            return prev;
        prev = root;
        return floorInBSTUtil(root.getRight(),prev,data);

    }

    public BSTNode ceilingInBSTUtil(BSTNode root, BSTNode prev, int data){
        if(root == null)
            return null;
        if(ceilingInBSTUtil(root.getRight(), prev, data) !=null)
            return 0;
        if(root.getData() == data)
            return root;
        if(root.getData()< data)
            return prev;
        prev = root;
        return ceilingInBSTUtil(root.getLeft(),prev,data);

    }

    /**
     * Print all the elements of a BST between 2 numbers(say k1 and k2)
     */
    public void rangePrint(BSTNode root, int k1, int k2) {
        if(root == null)
            return;
        if(root.getData() >=k1)
            rangePrint(root.getLeft(), k1, k2);
        if(root.getData() >=k1 && root.getData() <=k2)
            System.out.println(root.getData());
        if(root.getData()<= k2)
            rangePrint(root.getRight(), k1, k2);
    }

    /**
     * Another approach of above problem level order traversal,
     * adding elements to queue.
     */
    public void rangePrint2(BSTNode root, int k1, int k2) {
        BSTNode temp;
        Queue<BSTNode> q = new LinkedList<>();
        if(root == null)
            return;
        ((LinkedList<BSTNode>) q).push(root);
        while(!q.isEmpty()){
            temp = ((LinkedList<BSTNode>) q).pop();
            if(temp.getData()>= k1 && temp.getData() <= k2)
                System.out.println(temp.getData());
            if(temp.getLeft() != null && temp.getData()>=k1)
                ((LinkedList<BSTNode>) q).push(temp.getLeft());
            if(temp.getRight() != null && temp.getData() <=k1)
                ((LinkedList<BSTNode>) q).push(temp.getRight());

        }
    }
}
