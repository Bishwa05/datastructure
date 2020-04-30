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
			System.out.println(root.data);
			preOrderRec(root.left);
			preOrderRec(root.right);
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
				if(tmp.right != null)
					s.push(tmp.right);
				if(tmp.left != null)
					s.push(tmp.left);
				
			}
		}
	}

}
