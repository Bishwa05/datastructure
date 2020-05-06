package tree.binarytree;

public class BinaryTreeNode {
	public int data;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	BinaryTreeNode() {}

	BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
          this.data = val;
          this.left = left;
          this.right = right;
	}
//	public int getVal() {
//		return data;
//	}
//
//	public void setVal(int data) {
//		this.data = data;
//	}
//
//	public BinaryTreeNode getLeft() {
//		return left;
//	}
//
//	public void setLeft(BinaryTreeNode left) {
//		this.left = left;
//	}
//
//	public BinaryTreeNode getRight() {
//		return right;
//	}
//
//	public void setRight(BinaryTreeNode right) {
//		this.right = right;
//	}

}
