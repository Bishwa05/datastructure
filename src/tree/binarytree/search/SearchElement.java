package tree.binarytree.search;

import java.util.LinkedList;
import java.util.Queue;

import tree.binarytree.BinaryTreeNode;

public class SearchElement {
	
	public int maxInBinaryTreeRec(BinaryTreeNode root) {
		int max = Integer.MIN_VALUE;
		
		if(root != null) {
			int leftMax = maxInBinaryTreeRec(root.getLeft());
			int rightMax = maxInBinaryTreeRec(root.getRight());
			
			if(leftMax>rightMax)
				max = leftMax;
			else
				max = rightMax;
			
			if(root.getData()>max)
				max = root.getData();
		}
		return max;
	}

	
	public int maxInBinaryTreeItr(BinaryTreeNode root) {
		int max = Integer.MIN_VALUE;
		
		if(root == null)
			return max;
		
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr.getData()>max)
				max = curr.getData();
			if(curr != null) {
				if(curr.getLeft() != null)
					curr = curr.getLeft();
				if(curr.getRight() != null)
					curr= curr.getRight();
			}
		}
		return max;
	}
	
	public boolean findInBinaryTreeRec(BinaryTreeNode root, int data) {
		if(root == null)
			return false;
		if(root.getData() == data)
			return true;
		return findInBinaryTreeRec(root.getLeft(), data)|| findInBinaryTreeRec(root.getRight(), data);
	}
	
	public boolean findInBinaryTreeItr(BinaryTreeNode root, int data) {
		if(root == null)
			return false;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr.getData() == data)
				return true;
			if(curr != null) {
				if(curr.getLeft() != null)
					curr= curr.getLeft();
				if(curr.getRight() != null)
					curr = curr.getRight();
			}
		}
		return false;
	}
	
	public BinaryTreeNode deepestNodeInBT(BinaryTreeNode root) {
		 if(root == null)
			 return null;
		 BinaryTreeNode tmp = null;
		 Queue<BinaryTreeNode> q = new LinkedList<>();
		 q.offer(root);
		 while(!q.isEmpty()) {
			 tmp = q.poll();
			 if(tmp.getLeft() != null)
				 q.offer(tmp.getLeft());
			 if(tmp.getRight() != null)
				 q.offer(tmp.getRight());		 
		}
		return tmp;
	}
	
	
	public int findLevelWithMaxSum(BinaryTreeNode root) {
		int maxSum =0, currentSum =0;
		if(root == null)
			return 0;
		
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);
		
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr != null) {
				currentSum += curr.getData();
				if(curr.getLeft() != null)
					q.offer(curr.getLeft());
				if (curr.getRight() != null)
					q.offer(curr.getRight());
			}else {
				
				if(currentSum>maxSum)
					maxSum = currentSum;
				currentSum=0;
				if(!q.isEmpty())
					q.offer(null);
			}
		}
		return maxSum;
	}
}
