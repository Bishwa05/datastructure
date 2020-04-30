package tree.binarytree;

import java.util.*;

public class CommonFunction {
	
	public int sizeRec(BinaryTreeNode root) {
		int leftCount =root.left ==null?0:sizeRec(root.left);
		int rightCount = root.right == null?0:sizeRec(root.right);
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
			if(curr.left != null)
				q.offer(curr.left);
			if(curr.right != null)
				q.offer(curr.right);
		}
		return count;
	}
	
	public int maxDepthRec(BinaryTreeNode root) {
		if (root == null)
			return 0;
		int leftDepth = maxDepthRec(root.left);
		int rightDepth = maxDepthRec(root.right);
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
			if(prev == null|| prev.left == curr ||  prev.right== curr) {
				if(curr.left != null) s.push(curr.left);
				else if(curr.right != null) s.push(curr.right);
				
			} else if(curr.left == prev) {
				if(curr.right != null) s.push(curr.right);
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
				if(curr.left == null && curr.right == null) {
					return count;
				}
				if(curr.left != null) {
					q.offer(curr.left);
				}
				if(curr.right != null) {
					q.offer(curr.right);
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
				if(curr.left == null && curr.right == null) {
					return count;
				}
				if(curr.left != null) {
					q.offer(curr.left);
				}
				if(curr.right != null) {
					q.offer(curr.right);
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
		int len1 = height(root.left)+ height(root.right)+3;
		
		// the path does not pass through root
		int len2 = diameter(root.left)+ diameter(root.right);
		
		return Math.max(len1, len2);
	}
	
	public int height(BinaryTreeNode root) {
		if(root == null)
			return 0;
		
		//compute the depth of each subtree
		int leftDepth = height(root.left);
		int rightDepth = height(root.right);
		return (leftDepth> rightDepth)? leftDepth+1: rightDepth+1;
	}



	class Height{
		int h = Integer.MIN_VALUE;
	}

	public int height(BinaryTreeNode root, Height x) {
		if(root == null)
			return 0;

		int leftH, rightH;
		leftH = height(root.left, x);
		rightH = height(root.right, x);

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
				return width(root.left, depth-1)+ width(root.right, depth-1);
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
		if(root.left != null)
			vSum(hash,root.left, c-1);
		if(root.right != null)
			vSum(hash,root.right, c+1);
		int data = 0;
		if(hash.containsKey(c))
			data = hash.get(c);
		hash.put(c, root.data +data);
	}
	
	public static void verticalSum(BinaryTreeNode root) {
		HashMap<Integer, Integer> hash = new HashMap<>();
		vSum(hash, root, 0);
		System.out.println();

		for(int k: hash.keySet())
			System.out.println(" key(pos): "+k+ " sum : "+hash.get(k)+" ");
	}

	public static void verticalTraversal(Map<Integer,List> h, BinaryTreeNode curr, int c){
		if(curr.left != null){
			verticalTraversal(h,curr.left,c-1);
		}
		if(curr.right != null) {
			verticalTraversal(h, curr.right, c+1);
		}
		if(h.containsKey(c)){
			List<Integer> x = h.get(c);
			x.add(curr.data);
			h.put(c,x);
		} else {
			List<Integer> x =new ArrayList();
			x.add(curr.data);
			h.put(c, x);
		}
	}


	public static void verticalTraversal(BinaryTreeNode root) {
		HashMap<Integer, List> hash = new HashMap<>();
		verticalTraversal(hash, root, 0);
		System.out.println();
		List<List> result = new ArrayList<>();

		for(int k: hash.keySet()) {
			result.add(hash.get(k));
		}
		result.forEach(e-> e.forEach(y -> System.out.println(y)));

	}

	public static void main(String arg[]){
//1,2,3,4,5,6,7

		BinaryTreeNode root = new BinaryTreeNode(1);
		root.left = new BinaryTreeNode(2);
		root.right = new BinaryTreeNode(3);
		root.left.left = new BinaryTreeNode(4);
		root.left.right = new BinaryTreeNode(5);
		root.right.left = new BinaryTreeNode(6);
		root.right.right = new BinaryTreeNode(7);
		verticalTraversal(root);
	}


}
