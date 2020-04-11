package tree.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CommonFunction {
	
	public int sizeRec(BinaryTreeNode root) {
		int leftCount =root.getLeft() ==null?0:sizeRec(root.getLeft());
		int rightCount = root.getRight() == null?0:sizeRec(root.getRight());
		return 1+leftCount+rightCount;
	}

	public int sizeItr(BinaryTreeNode root) {
		int count =0;
		if (root == null)
			return count;
		Queue <BinaryTreeNode> q = new LinkedList();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			count++;
			if(curr.getLeft() != null)
				q.offer(curr.getLeft());
			if(curr.getRight() != null)
				q.offer(curr.getRight());
		}
		return count;
	}
	
	public int maxDepthRec(BinaryTreeNode root) {
		if (root == null)
			return 0;
		int leftDepth = maxDepthRec(root.getLeft());
		int rightDepth = maxDepthRec(root.getRight());
		return (leftDepth > rightDepth) ? leftDepth+1: rightDepth+1;
	}
	
	public int maxDepthItr(BinaryTreeNode root) {
		int maxDepth =0;
		if(root == null)
			return maxDepth;
		Stack<BinaryTreeNode> s = new Stack<>();
		s.push(root);
		BinaryTreeNode prev = null;
		while(!s.isEmpty()) {
			BinaryTreeNode curr = s.peek();
			if(prev == null|| prev.getLeft() == curr ||  prev.getRight()== curr) {
				if(curr.getLeft() != null) s.push(curr.getLeft());
				else if(curr.getRight() != null) s.push(curr.getRight());
				
			} else if(curr.getLeft() == prev) {
				if(curr.getRight() != null) s.push(curr.getRight());
			}else {
				s.pop();
			}
			prev = curr;
			if(s.size()> maxDepth)
				maxDepth = s.size();	
		}
		return maxDepth;
	}
	
	public int maxDepthLvlOrder(BinaryTreeNode root) {
		int count =0;
		if(root == null)
			return count;
		
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		count++;
		
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			
			if(curr != null) {
				if(curr.getLeft() == null && curr.getRight() == null) {
					return count;
				}
				if(curr.getLeft() != null) {
					q.offer(curr.getLeft());
				}
				if(curr.getRight() != null) {
					q.offer(curr.getRight());
				}
			}else {
				if(!q.isEmpty()) {
					count++;
					q.offer(null);
				}
			}
		}
		return count;	
	}
	
	public int minDepth(BinaryTreeNode root) {
		int count =0;
		if (root == null)
			return count;
		
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		count++;
		
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			
			if(curr != null) {
				if(curr.getLeft() == null && curr.getRight() == null) {
					return count;
				}
				if(curr.getLeft() != null) {
					q.offer(curr.getLeft());
				}
				if(curr.getRight() != null) {
					q.offer(curr.getRight());
				}
			}else {
				if(!q.isEmpty()) {
					count++;
					q.offer(null);
				}
			}
		}
		return count;
	}

	
	public int diameter(BinaryTreeNode root) {
		if(root ==null) return 0;
		
		// the path goes through root
		int len1 = height(root.getLeft())+ height(root.getRight())+3;
		
		// the path does not pass through root
		int len2 = diameter(root.getLeft())+ diameter(root.getRight());
		
		return Math.max(len1, len2);
	}
	
	public int height(BinaryTreeNode root) {
		if(root == null)
			return 0;
		
		//compute the depth of each subtree
		int leftDepth = height(root.getLeft());
		int rightDepth = height(root.getRight());
		return (leftDepth> rightDepth)? leftDepth+1: rightDepth+1;
	}



	class Height{
		int h = Integer.MIN_VALUE;
	}

	public int height(BinaryTreeNode root, Height x) {
		if(root == null)
			return 0;

		int leftH, rightH;
		leftH = height(root.getLeft(), x);
		rightH = height(root.getRight(), x);

		x.h = Math.max(x.h,1+leftH+rightH);

		return 1+ Math.max(leftH, rightH);
	}
	//This approach is correct as solution passes  thorough in leetcode.
	public int diameterOfBinaryTree(BinaryTreeNode root) {
		if(root == null)
			return 0;
		Height obj = new Height();
		height(root,obj);

		return obj.h-1;
	}
	
	public int maxWidthRec(BinaryTreeNode root) {
		int max =0;
		int height = maxDepthRec(root);
		
		for(int k=0; k<=height; k++) {
			int tmp = width(root,k);
			if(tmp>max) max=tmp;
		}
		return max;
	}
	
	public int width(BinaryTreeNode root, int depth) {
		if(root == null)
			return 0;
		else
			if(depth == 0)
				return 1;
			else
				return width(root.getLeft(), depth-1)+ width(root.getRight(), depth-1);
	}
	
	/*
	 * Find vertical sum of a binary tree
	 * 					1
	 * 			2				3
	 * 		4		5		6		7
	 * 
	 * Here tree has 5 vertical lines, 4,2, 12, 3, 7
	 */
	
	public static void vSum(HashMap<Integer, Integer> hash, BinaryTreeNode root, int c) {
		if(root.getLeft() != null)
			vSum(hash,root.getLeft(), c-1);
		if(root.getRight() != null)
			vSum(hash,root.getRight(), c+1);
		int data = 0;
		if(hash.containsKey(c))
			data = hash.get(c);
		hash.put(c, root.getData()+data);
	}
	
	public static void verticalSum(BinaryTreeNode root) {
		HashMap<Integer, Integer> hash = new HashMap<>();
		vSum(hash, root, 0);
		System.out.println();
		
		for(int k: hash.keySet())
			System.out.println(" key(pos): "+k+ " sum : "+hash.get(k)+" ");
	}
	

}
