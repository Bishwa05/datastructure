package tree.binarytree.two;

import tree.binarytree.BinaryTreeNode;

public class TwoTree {

	public boolean checkStructurallySame(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null && root2 == null)
			return true;
		if(root1 ==null || root2 == null)
			return false;
		return checkStructurallySame(root1.left, root2.right) &&
				checkStructurallySame(root1.right, root2.left);
	}
	
	//Convert a tree to its mirror tree
	public BinaryTreeNode mirrorOfBinaryTree(BinaryTreeNode root) {
		BinaryTreeNode tmp;
		if(root != null) {
			mirrorOfBinaryTree(root.left);
			mirrorOfBinaryTree(root.right);
			//Swap the pointers in this node
			tmp = root.left;
			root.left = root.right;
			root.right= tmp;
		}
		return root;
	}
	
	//Whether provided 2 trees are mirrors to each other
	public boolean areMirrors(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null & root2 == null)
			return true;
		if(root1 == null || root2== null)
			return false;
		if(root1.data != root2.data)
			return false;
		else
			return areMirrors(root1.left, root2.right) && areMirrors(root1.right, root2.left);
	}

}
