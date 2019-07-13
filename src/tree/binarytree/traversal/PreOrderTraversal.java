package tree.binarytree.traversal;

import java.util.Stack;

import tree.binarytree.BinaryTreeNode;

/**
 * 
 * @author i501895
 * Root--> Left-->Right
 */
public class PreOrderTraversal {
	
	public void preOrderRec(BinaryTreeNode root) {
		if(root != null) {
			System.out.println(root.getData());
			preOrderRec(root.getLeft());
			preOrderRec(root.getRight());
		}
	}
	
	public void preOrderItr(BinaryTreeNode root) {
		if(root == null) {
			System.out.println("[]");
		}else {
			Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
			s.push(root);
			
			while(!s.isEmpty()) {
				BinaryTreeNode tmp = s.pop();
				System.out.println(tmp);
				if(tmp.getRight() != null)
					s.push(tmp.getRight());
				if(tmp.getLeft() != null)
					s.push(tmp.getLeft());
				
			}
		}
	}

}
