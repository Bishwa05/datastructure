package tree.binarytree.crud;

import java.util.ArrayList;

import tree.binarytree.BinaryTreeNode;

public class CreateTree {
	
	/**
	 * Construct a binary tree from Inorder and PreOrder traversals
	 *  Inorder sequence : D B E A F C
	 *  Preorder sequence : A B D E C F, Root A
	 */
	public BinaryTreeNode buildBinaryTree(int[] preOrder, int[] inOrder) {
		
		if(preOrder.length ==0|| inOrder.length != preOrder.length)
			return null;
		return buildBT(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1);
	}

	private BinaryTreeNode buildBT(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {

		if(preStart>preEnd || inStart>inEnd)
			return null;
		int data = preOrder[preStart];
		int offset = inStart;
		BinaryTreeNode curr = new BinaryTreeNode(data);
		for(; offset <inEnd; offset++) {
			if(inOrder[offset]== data)
				break;
		}
		curr.left = buildBT(preOrder, preStart+1, preStart+offset-inStart, inOrder, inStart, offset-1);
		curr.right =buildBT(preOrder, preStart+offset-inStart+1, preEnd, inOrder, offset+1, inEnd);
		return curr;
	}
	
	/**
	 * Construct a binary tree from inOrder and postOrder traversals
	 */
	public BinaryTreeNode buildBinaryTree2(int[] inOrder, int[] postOrder) {
		if(postOrder.length ==0|| inOrder.length != postOrder.length)
			return null;
		return buildBT2(postOrder, 0, postOrder.length-1, inOrder,0, inOrder.length-1);
	}

	private BinaryTreeNode buildBT2(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd) {

		if(postStart>postEnd || inStart>inEnd)
			return null;
		int data = postOrder[postEnd];
		int offset = inStart;
		BinaryTreeNode curr = new BinaryTreeNode(data);
		for(;offset <inEnd; offset++) {
			if(inOrder[offset]== data)
				break;
		}
		
		curr.left = buildBT2(postOrder,postStart,postStart+offset-inStart-1, inOrder,inStart,offset-1);
		curr.right = buildBT2(postOrder, postStart+offset-inStart, postEnd-1, inOrder, offset+1, inEnd);
		return curr;
	}
	
	/**
	 * The following combination can uniquely identify a tree
	 * Inorder and preorder
	 * Inorder and postOrder
	 * Inorder and LevelOrder
	 */

	
	/*
	 * How many different trees possible from n nodes
	 * 2^n -n
	 */
	public int noOfBSTs(int n) {
		int[] count = new int[n+1];
		count[0] = 1;
		count[1] = 1;
		for(int i=2; i<= n; i++) {
			for(int j=0; j<i; j++) {
				count[i] = count[j] * count[i-j-1];
			}
		}
		return count[n];
	}
	
	public ArrayList<BinaryTreeNode> generateTrees(int n){
		if(n == 0)
			return generateTrees(1,0);
		return generateTrees(1,n);
	}

	private ArrayList<BinaryTreeNode> generateTrees(int start, int end) {
		ArrayList<BinaryTreeNode> subTrees = new ArrayList<>();
		
		if(start>end) {
			subTrees.add(null);
			return subTrees;
		}
		
		for(int i= start; i<= end; i++) {
			for(BinaryTreeNode  left: generateTrees(start, i-1)) {
				for(BinaryTreeNode right: generateTrees(i+1, end)) {
					BinaryTreeNode tmp = new BinaryTreeNode(i);
					tmp.left= left;
					tmp.right =right;
					subTrees.add(tmp);
				}
			}
		}
		return subTrees;
	}
	
	
}
