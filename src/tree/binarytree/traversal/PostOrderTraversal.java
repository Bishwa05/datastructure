package tree.binarytree.traversal;

import java.util.Stack;

import tree.binarytree.BinaryTreeNode;

public class PostOrderTraversal {
	
	public void postOrderRec(BinaryTreeNode root) {
		if(root != null) {
			postOrderRec(root.getRight());
			postOrderRec(root.getLeft());
			System.out.println(root.getData());
		}
	}
	
	public void postOrderItr(BinaryTreeNode root) {
		if(root == null) {
			System.out.println("[]");
		}else {
			Stack<BinaryTreeNode> s = new Stack<>();
			s.push(root);
			BinaryTreeNode prev = null;
			
			while(!s.isEmpty()) {
				BinaryTreeNode curr = s.peek();
				if(prev==null || prev.getLeft() == curr || prev.getRight() == curr) {
					if(curr.getLeft() != null)
						s.push(curr.getLeft());
					else if(curr.getRight() != null)
						s.push(curr.getRight());		
				} else if(curr.getLeft() ==prev) {
					if(curr.getRight() != null)
						s.push(curr.getRight());
				} else {
					System.out.println(curr.getData());
					s.pop();
				}
				prev = curr;
			}
		}
	}

}
