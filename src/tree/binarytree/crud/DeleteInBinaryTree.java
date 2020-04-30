package tree.binarytree.crud;

import tree.binarytree.BinaryTreeNode;

public class DeleteInBinaryTree {

	public void deletebinaryTree(BinaryTreeNode root) {
		root = null;
	}

	/**
	 *				5										5
	 *		4				6		-->				4				6
	 *					7		8								7		9
	 *						9
	 * 	Remove all the half nodes which has 1 child but we should not touch the
	 * 	leaf nodes
	 *
	 * 	This can be solved with post order traversal.
	 */
	public static BinaryTreeNode removeHalfNodes(BinaryTreeNode root) {
		if(root == null)
			return null;
		root.left = removeHalfNodes(root.left);
		root.right = removeHalfNodes(root.right);
		if(root.left == null && root.right == null)
			return root;
		if(root.left == null)
			return root.right;
		if(root.right == null)
			return root.left;
		return root;
	}


	public static void main(String arg[]){
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.left = new BinaryTreeNode(4);
		root.right = new BinaryTreeNode(6);
		root.right.right = new BinaryTreeNode(8);
		root.right.left = new BinaryTreeNode(7);
		root.right.right.right = new BinaryTreeNode(9);
		root = removeHalfNodes(root);
		System.out.println(root);
	}

	/**
	 * Remove the leaves of Binary Tree
	 */
	public BinaryTreeNode removeLeaves(BinaryTreeNode root) {
		if(root != null) {
			if(root.left == null && root.right == null){
				root = null;
			} else {
				root.left= removeLeaves(root.left);
				root.right = removeLeaves(root.right);
			}
		}
		return root;
	}

}
