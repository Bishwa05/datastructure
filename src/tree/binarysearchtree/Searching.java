package tree.binarysearchtree;

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
            return left
        }

    }


    /**
     * Convert a sorted doubly linked list to balanced binary tree
     *
     */
    public BSTNode sortedListToBST(ListNode head) {

        int len =0;
        ListNode currentNode = head;
        while(currentNode != null){
            len++;
            currentNode = currentNode.getNext();
        }
        return constrctRec(head, 0, len-1);

    }

    public BSTNode constructRec(ListNode head, int start, int end) {
        if(start>end) {
            return null;
        }
        int mid = start + (end - start)/2;

        BSTNode root = new BST(head.getData());
        BSTNode left = constructRec(ListNode head,start,mid-1);
        root.setLeft(left);

        if(head.getNext() != null) {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }

        root.setRight(constructRec(ListNode head, mid+1, end));
        return root;
    }


}
