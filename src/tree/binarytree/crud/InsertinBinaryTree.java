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
				if(curr.left != null)
					q.offer(curr.left);
				else {
					curr.left = new BinaryTreeNode(data);
					return root;
				}
				if(curr.right != null)
					q.offer(curr.right);
				else {
					curr.right = new BinaryTreeNode(data);
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
		if(root.left == null) {
			root.left = new BinaryTreeNode(data);
		} else {
			insertHelperRec(root.left, data);
		}
		if(root.right == null) {
			root.right = new BinaryTreeNode(data);
		} else {
			insertHelperRec(root.right, data);
		}
	}
}
