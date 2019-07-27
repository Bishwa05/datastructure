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
		root.setLeft(removeHalfNodes(root.getLeft()));
		root.setRight(removeHalfNodes(root.getRight()));
		if(root.getLeft() == null && root.getRight() == null)
			return root;
		if(root.getLeft() == null)
			return root.getRight();
		if(root.getRight() == null)
			return root.getLeft();
		return root;
	}


	public static void main(String arg[]){
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.setLeft(new BinaryTreeNode(4));
		root.setRight(new BinaryTreeNode(6));
		root.getRight().setRight(new BinaryTreeNode(8));
		root.getRight().setLeft(new BinaryTreeNode(7));
		root.getRight().getRight().setRight(new BinaryTreeNode(9));
		root = removeHalfNodes(root);
		System.out.println(root);
	}

	/**
	 * Remove the leaves of Binary Tree
	 */
	public BinaryTreeNode removeLeaves(BinaryTreeNode root) {
		if(root != null) {
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			} else {
				root.setLeft(removeLeaves(root.getLeft()));
				root.setRight(removeLeaves(root.getRight()));
			}
		}
		return root;
	}

}
