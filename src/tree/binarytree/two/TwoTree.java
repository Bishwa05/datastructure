package tree.binarytree.two;

import tree.binarytree.BinaryTreeNode;

public class TwoTree {

	public boolean checkStructurallySame(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null && root2 == null)
			return true;
		if(root1 ==null || root2 == null)
			return false;
		return checkStructurallySame(root1.getLeft(), root2.getRight()) &&
				checkStructurallySame(root1.getRight(), root2.getLeft());
	}
	
	//Convert a tree to its mirror tree
	public BinaryTreeNode mirrorOfBinaryTree(BinaryTreeNode root) {
		BinaryTreeNode tmp;
		if(root != null) {
			mirrorOfBinaryTree(root.getLeft());
			mirrorOfBinaryTree(root.getRight());
			//Swap the pointers in this node
			tmp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(tmp);
		}
		return root;
	}
	
	//Whether provided 2 trees are mirrors to each other
	public boolean areMirrors(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null & root2 == null)
			return true;
		if(root1 == null || root2== null)
			return false;
		if(root1.getData() != root2.getData())
			return false;
		else
			return areMirrors(root1.getLeft(), root2.getRight()) && areMirrors(root1.getRight(), root2.getLeft());
	}
}
