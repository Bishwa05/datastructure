package tree.binarysearchtree.crud;

import linklist.singly.ListNode;
import tree.binarysearchtree.BSTNode;

public class Creation {
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
        return constructRec(head, 0, len-1);

    }

    public BSTNode constructRec(ListNode head, int start, int end) {
        if(start>end) {
            return null;
        }
        int mid = start + (end - start)/2;

        BSTNode root = new BSTNode(head.getData());
        BSTNode left = constructRec(head,start,mid-1);
        root.setLeft(left);

        if(head.getNext() != null) {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
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
            head=head.getNext();
        }
        return head.getData();
    }

}