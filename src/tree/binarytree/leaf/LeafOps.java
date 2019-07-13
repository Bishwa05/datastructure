package tree.binarytree.leaf;

import java.util.LinkedList;
import java.util.Queue;

import tree.binarytree.BinaryTreeNode;

public class LeafOps {

	public int numberOfLeavesLvlOrder(BinaryTreeNode root) {
		int count =0;
		if(root == null)
			return count;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if(tmp.getLeft() == null && tmp.getRight() == null)
				count++;
			if(tmp.getLeft() != null)
				q.offer(tmp.getLeft());
			if(tmp.getRight() != null)
				q.offer(tmp.getRight());
		}
		return count;
	}
	
	public int numberOfFullNodesLvlOrder(BinaryTreeNode root) {
		int count =0;
		if(root == null)
			return count;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if(tmp.getLeft() != null && tmp.getRight() !=null)
				count++;
			if(tmp.getLeft() != null)
				q.offer(tmp.getLeft());
			if(tmp.getRight() != null)
				q.offer(tmp.getRight());
		}
		return count;
	}
	
	public int numberOfHalfNodesLvlOrder(BinaryTreeNode root) {
		int count =0;
		if(root == null)
			return count;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if((tmp.getLeft() == null && tmp.getRight() !=null)||
					(tmp.getLeft() != null && tmp.getRight() ==null))
				count++;
			if(tmp.getLeft() != null)
				q.offer(tmp.getLeft());
			if(tmp.getRight() != null)
				q.offer(tmp.getRight());
		}
		return count;
	}
	
	//print all the paths from root to leaf in a tree
	public void printPaths(BinaryTreeNode root) {
		int[] path = new int[256];
		printPaths(root,path,0);
	}

	private void printPaths(BinaryTreeNode root, int[] path, int pathLen) {
		if(root == null)
			return;
		//append this node to path array
		path[pathLen] = root.getData();
		pathLen++;
		
		// its a leaf. So print the path that led to here
		if(root.getLeft() == null && root.getRight() == null) {
			printArray(path, pathLen);
		} else {
			// otherwise try both subtrees
			printPaths(root.getLeft(), path, pathLen);
			printPaths(root.getRight(), path, pathLen);
		}
	}
	
	public void printArray(int[] ints, int len) {
		for(int i=0; i<len; i++) {
			System.out.println(ints[i]+" ");
		}
		System.out.println();
	}
	
	
	//Check the existence of path with given sum
	public boolean hasPathSum(BinaryTreeNode root, int sum) {
		if(root == null)
			return false;
		if(root.getLeft() == null && root.getRight() == null && root.getData() == sum)
			return true;
		else
			return hasPathSum(root.getLeft(), sum-root.getData()) || hasPathSum(root.getRight(), sum -root.getData());
	}
	
	// find sum of all elements in a binary tree
	public int addBTRec(BinaryTreeNode root) {
		if(root == null)
			return 0;
		else
			return(root.getData()+addBTRec(root.getLeft())+addBTRec(root.getRight()));
	}
	
	// find sum of all elements in a binary tree itr
	public int addBTItr(BinaryTreeNode root) {
		int sum =0;
		if(root == null)
			return sum;
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr != null) {
				sum+= curr.getData();
				if(curr.getLeft() != null)
					q.offer(curr.getLeft());
				if(curr.getRight() != null)
					q.offer(curr.getRight());
			}
		}
		return sum;
	}
	
	
	/**
	 * Print all ancestors of a node in a BinaryTree 
	 */
	public static boolean printAllAncestors(BinaryTreeNode root, BinaryTreeNode node) {
		if(root == null)
			return false;
		if(root.getLeft() == node || root.getRight() == node ||
				printAllAncestors(root.getLeft(), node)|| printAllAncestors(root.getRight(), node)) {
			System.out.println(root.getData());
			return true;
		}
		return false;
	}
	
	/**
	 * Least Common Ancestors
	 */
	public BinaryTreeNode LCA(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
		BinaryTreeNode left, right;
		if(root == null)
			return root;
		if(root ==a || root==b)
			return root;
		left = LCA(root.getLeft(), a, b);
		right = LCA(root.getRight(), a, b);
		if(left != null && right != null)
			return root; //nodes are each on a separate branch
		else
			return(left != null? left:right);
		//Either 1 node is on 1 branch or none was found in any of the branches
	}
}
