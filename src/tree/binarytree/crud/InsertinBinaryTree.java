package tree.binarytree.crud;

import java.util.LinkedList;
import java.util.Queue;

import tree.binarytree.BinaryTreeNode;

public class InsertinBinaryTree {
	
	public BinaryTreeNode insertInBinaryTreeLevelOrderItr(BinaryTreeNode root, int data) {
		if (root == null)
			return null;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr != null) {
				if(curr.getLeft() != null)
					q.offer(curr.getLeft());
				else {
					curr.setLeft(new BinaryTreeNode(data));
					return root;
				}
				if(curr.getRight() != null)
					q.offer(curr.getRight());
				else {
					curr.setRight(new BinaryTreeNode(data));
					return root;
				}
			}
		}
		return root;
	}

	
	public void insertLevelOrderRec(BinaryTreeNode root, int data) {
		if (root == null) {
			root = new BinaryTreeNode(data);
		}else {
			insertHelperRec(root, data);
		}
	}


	private void insertHelperRec(BinaryTreeNode root, int data) {
		// TODO Auto-generated method stub
		if(root.getLeft() == null) {
			root.setLeft(new BinaryTreeNode(data));
		} else {
			insertHelperRec(root.getLeft(), data);
		}
		if(root.getRight() == null) {
			root.setRight(new BinaryTreeNode(data));
		} else {
			insertHelperRec(root.getRight(), data);
		}
	}
}
