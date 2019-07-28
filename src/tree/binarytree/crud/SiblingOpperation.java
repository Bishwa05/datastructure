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
				if(tmp.getLeft() != null)
					q.offer(tmp.getLeft());
				if(tmp.getRight() !=null)
					q.offer(tmp.getRight());
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
		if(root.getLeft() != null)
			root.getLeft().setNextSibling(root.getRight());
		if(root.getRight() != null)
			if(root.getNextSibling() != null)
				root.getRight().setNextSibling(root.getNextSibling().getLeft());
			else
				root.getRight().setNextSibling(null);
		fillNextSiblings2(root.getLeft());
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
			if(temp.getLeft()!= null) {
				if(rightMostNode == null) {
					rightMostNode = temp.getLeft();
					nextHead = temp.getLeft();
				} else {
					rightMostNode.setNextSibling(temp.getLeft());
					rightMostNode = rightMostNode.getNextSibling();
				}
			}

			if(temp.getRight()!= null) {
				if(rightMostNode == null) {
					rightMostNode = temp.getRight();
					nextHead = temp.getRight();
				} else {
					rightMostNode.setNextSibling(temp.getRight());
					rightMostNode = rightMostNode.getNextSibling();
				}
			}
			temp = temp.getNextSibling();
		}
		//connect(nextHead);
	}


}
