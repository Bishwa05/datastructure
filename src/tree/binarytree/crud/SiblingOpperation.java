package tree.binarytree.crud;

import java.util.LinkedList;
import java.util.Queue;

import tree.binarytree.SiblingBinaryTreeNode;

public class SiblingOpperation {
	
	/*
	 * Fill nextSibling pointer
	 */
	public static void fillNextSiblings1(SiblingBinaryTreeNode root) {
		SiblingBinaryTreeNode tmp = null;
		
		if(root == null)
			return;
		Queue<SiblingBinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		
		while(!q.isEmpty()){
			tmp = q.poll();
			if(tmp != null) {
				tmp.setNextSibling(q.peek());
				if(tmp.left != null)
					q.offer(tmp.left);
				if(tmp.right !=null)
					q.offer(tmp.right);
			} else {
				if(!q.isEmpty())
					q.offer(null);
			}
		}
	}
	
	//Method 2
	public static void fillNextSiblings2(SiblingBinaryTreeNode root) {
		if(root == null)
			return;
		if(root.left != null)
			root.left.setNextSibling(root.right);
		if(root.right != null)
			if(root.getNextSibling() != null)
				root.right.setNextSibling(root.getNextSibling().left);
			else
				root.right.setNextSibling(null);
		fillNextSiblings2(root.left);
	}


	/**
	 * Just one step further.
	 * Connect all adjacent nodes at same level
	 */
	public void linkLevelNodes(SiblingBinaryTreeNode root) {
		if(root == null)
			return;

		SiblingBinaryTreeNode rightMostNode = null;
		SiblingBinaryTreeNode nextHead = null;
		SiblingBinaryTreeNode temp = root;

		while(temp!= null){
			if(temp.left!= null) {
				if(rightMostNode == null) {
					rightMostNode = temp.left;
					nextHead = temp.left;
				} else {
					rightMostNode.setNextSibling(temp.left);
					rightMostNode = rightMostNode.getNextSibling();
				}
			}

			if(temp.right!= null) {
				if(rightMostNode == null) {
					rightMostNode = temp.right;
					nextHead = temp.right;
				} else {
					rightMostNode.setNextSibling(temp.right);
					rightMostNode = rightMostNode.getNextSibling();
				}
			}
			temp = temp.getNextSibling();
		}
		//connect(nextHead);
	}


}
