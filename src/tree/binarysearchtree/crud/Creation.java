package tree.binarysearchtree.crud;

import linklist.singly.ListNode;
import tree.binarysearchtree.BSTNode;
import tree.binarysearchtree.TreeNode;

public class Creation {
    /**
     * Convert a sorted doubly linked list to balanced binary tree
     * also works for singly linked list
     */
    public BSTNode sortedListToBST(ListNode head) {

        int len =0;
        ListNode currentNode = head;
        while(currentNode != null){
            len++;
            currentNode = currentNode.next;
        }
        return constructRec(head, 0, len-1);

    }

    public BSTNode constructRec(ListNode head, int start, int end) {
        if(start>end) {
            return null;
        }
        int mid = start + (end - start)/2;


        BSTNode left = constructRec(head,start,mid-1);
        BSTNode root = new BSTNode(head.getVal());
        root.setLeft(left);

        if(head.next != null) {
            head.setVal(head.next.getVal());
            head.next = head.next.next;
        }

        root.setRight(constructRec(head, mid+1, end));
        return root;
    }


    /**
     * Convert a sorted array to BST
     * Time complexity O(n) and Space Complexity O(n)
     */
    public BSTNode buildBST(int arr[], int start, int end) {
        BSTNode newNode;
        if(start > end)
            return null;
        newNode = new BSTNode();

        if(start == end){
            newNode.setData(arr[start]);
            newNode.setLeft(null);
            newNode.setRight(null);
        } else {
            int mid = (start + end)/2;
            newNode.setData(arr[mid]);
            newNode.setLeft(buildBST(arr,start, mid-1));
            newNode.setRight(buildBST(arr,mid+1, end));
        }
        return newNode;
    }

    /**
     * Convert a sorted singly linked list to height balanced BST
     * Time complexity O(nlogn) space complexity O(n)
     */
    public BSTNode sortedListToBST(ListNode head, int start, int end) {
        if(start > end)
            return null;
        int mid = start + (end- start)/2;
        BSTNode leftChild = sortedListToBST(head,start, mid-1);
        BSTNode parent = new BSTNode(getListDataByPosition(head,mid));
        parent.setLeft(leftChild);
        BSTNode rightChild = sortedListToBST(head,mid+1, end);
        parent.setRight(rightChild);
        return parent;

    }

    public int getListDataByPosition(ListNode head, int mid) {
        int i=0;
        while(i<mid){
            i++;
            head=head.next;
        }
        return head.getVal();
    }

    public  BSTNode constructBST(int[] preorder, int start, int end)
    {
        // base case
        if (start > end) {
            return null;
        }


        BSTNode node = new BSTNode(preorder[start]);

        int i;
        for (i = start; i <= end; i++) {
            if (preorder[i] > node.getData()) {
                break;
            }
        }

        // recursively construct the left sub-tree
        node.setLeft(constructBST(preorder, start + 1, i - 1));

        // recursively construct the right sub-tree
        node.setRight(constructBST(preorder, i, end));

        // return current node
        return node;
    }

    public BSTNode bstFromPreorder(int[] preorder) {
        BSTNode root = constructBST(preorder, 0, preorder.length - 1);
        return root;
    }


    /**
     * Convert a sorted List to BST
     * Leetcode 109
     * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
     */

    private ListNode head;

    private int findSize(ListNode head){
        ListNode ptr = head;
        int c=0;
        while(ptr != null){
            ptr = ptr.next;
            c+=1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        //Invalid case
        if(l> r) return null;

        int mid = (l+r)>>1;

        TreeNode left = convertListToBST(1, mid-1);

        //process the head once left half traversed
        TreeNode node = new TreeNode(head.val);
        node.left = left;
        head = head.next;

        node.right = convertListToBST(mid+1, r);
        return node;
    }
    //Singly l list
    public TreeNode sortedListToBST2(ListNode head){
        int size = findSize(head);
        this.head = head;
        return convertListToBST(0, size-1);
    }

}
