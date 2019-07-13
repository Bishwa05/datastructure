package tree.binarytree.traversal;

import java.util.Stack;

import tree.binarytree.BinaryTreeNode;

/**
 * 
 * @author i501895
 *	Left --> Right--> Root
 */
public class InOrderTraversal {

	public void inOrderRec(BinaryTreeNode root) {
		if(root != null) {
			inOrderRec(root.getLeft());
			System.out.println(root.getData());
			inOrderRec(root.getRight());
		}
	}
	
	public void inOrderItr(BinaryTreeNode root) {
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		BinaryTreeNode currNode = root;
		boolean done =false;
		
		while(!done) {
			if(currNode != null) {
				s.push(currNode);
				currNode = currNode.getLeft();
			}else {
				if(s.isEmpty())
					done= true;
				else {
					currNode = s.pop();
					System.out.println(currNode.getData());
					currNode = currNode.getRight();
				}
			}
		}
	}
}
