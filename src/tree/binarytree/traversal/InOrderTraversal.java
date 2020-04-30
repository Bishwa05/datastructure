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
			inOrderRec(root.left);
			System.out.println(root.data);
			inOrderRec(root.right);
		}
	}
	
	public void inOrderItr(BinaryTreeNode root) {
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		BinaryTreeNode currNode = root;
		boolean done =false;
		
		while(!done) {
			if(currNode != null) {
				s.push(currNode);
				currNode = currNode.left;
			}else {
				if(s.isEmpty())
					done= true;
				else {
					currNode = s.pop();
					System.out.println(currNode.data);
					currNode = currNode.right;
				}
			}
		}
	}
}
