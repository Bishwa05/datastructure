package tree.binarytree;

import java.util.*;

public class CommonFunction {

	/**
	 * to become cousin
	 * 1. The nodes to be in same level
	 * 2. They should not sibling
	 */
	public boolean ifCousin(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b){
		if(root == null || a == null || b == null){
			return false;
		}

		if(getLevelOfNode(root, a.data, 1) != getLevelOfNode(root, b.data, 1)){
			return false;
		}

		return !isSibling(root, a.data, b.data);
	}

	public int getLevelOfNode(BinaryTreeNode node, int val, int level) {
		if(node == null) return 0;

		if(node.data == val){
			return level;
		}

		int l = getLevelOfNode(node.left, val, level+1);
		if(l !=0){
			return l;
		}
		l = getLevelOfNode(node.right, val, level+1);
		return l;
	}

	public boolean isSibling(BinaryTreeNode root , int x, int y) {
		if(root == null){
			return false;
		}

		boolean a = false, b = false, c = false;
		if(root.left != null && root.right != null)
			a = ( (root.left.data == x && root.right.data == y) ||
					(root.left.data == y && root.right.data == x) );
		if(root.left != null)
			b = isSibling(root.left, x, y);
		if(root.right != null)
			c = isSibling(root.right, x, y);
		return (a || b || c);
	}

	
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

	/**
	 *Accepted in leetcode
	 */
	public int maxDepth(BinaryTreeNode root) {
		return maxDepth(root, 1);

	}
	public int maxDepth(BinaryTreeNode node, int i) {
		if(node == null){
			return i-1;
		}
		if(node.left == null && node.right == null){
			return i;
		}

		return Math.max(maxDepth(node.left, i+1),maxDepth(node.right, i+1));

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

	public List<List<Integer>> verticalTraversal(BinaryTreeNode root) {
		if (root == null) return new ArrayList<List<Integer>>();

 /*
 find the location of leftmost valid node
           4
          /
        1
        in this case, offset is 1
 */
		int offset = dfs(root, 0);

		// level order of queue and its index
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		Queue<Integer> indices = new LinkedList<>();

		queue.offer(root);
		indices.offer(0);

		List<List<Integer>> result= new ArrayList<>();

		// add empty array list
		for (int i=0; i<offset; i++) {
			result.add(new ArrayList<>());
		}

		while (!queue.isEmpty()) {
			int size = queue.size();

     /*
     for each level, if there are 2 nodes that has same place
     we want to add them in sorted order.
     - but we can't just sort the original result array
     */
			Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

			// standard level order traversal:
			for (int i=0; i<size; i++) {

				BinaryTreeNode curr = queue.poll();
				int index = indices.poll();
				int updatedIndex = index + offset;

				if (result.size() < updatedIndex+1) {
					result.add(new ArrayList<>());
				}

				map.putIfAbsent(updatedIndex, new PriorityQueue<>());
				map.get(updatedIndex).add(curr.data);

				if (curr.left != null) {
					queue.offer(curr.left);
					indices.offer(index-1);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
					indices.offer(index+1);
				}
			}

			// after each level traversal, we want to add "sorted items" into our result array.
			for (Map.Entry<Integer, PriorityQueue<Integer>> entry: map.entrySet()) {
				int index = entry.getKey();
				PriorityQueue<Integer> items = entry.getValue();
				while (!items.isEmpty()) {
					result.get(index).add(items.poll());
				}
			}
		}
		return result;
	}

	private int dfs(BinaryTreeNode root, int depth) {
		if (root == null) return depth-1;
		int left = dfs (root.left, depth+1);
		int right = dfs(root.right, depth-1);
		return Math.max(left, right);
	}

    /**
     * Invert a binary Tree
     * @param root
     * @return
     */
	public static BinaryTreeNode invertBinaryTree(BinaryTreeNode root){

		if(root != null){
			swapChild(root);
			invertBinaryTree(root.left);
			invertBinaryTree(root.right);
		}
		return root;
	}

	public static void swapChild(BinaryTreeNode root){
		BinaryTreeNode t = root.left;
		root.left = root.right;
		root.right = t;
	}

    public static BinaryTreeNode invertBinaryTreeItr(BinaryTreeNode root){
	    Queue<BinaryTreeNode> q = new LinkedList<>();
	    q.offer(root);
	    while(!q.isEmpty()){
	        BinaryTreeNode t = q.poll();
	        if(t == null) continue;
            swapChild(t);
            q.offer(t.left);
            q.offer(t.right);
        }
        return root;
    }

	public static void display(BinaryTreeNode root){
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.add(root);

		while(!q.isEmpty()){
			BinaryTreeNode n = q.poll();
			System.out.println(n.data);
			if(n.left!= null){
				q.offer(n.left);
			}
			if(n.right != null){
				q.offer(n.right);
			}
		}
	}


	public boolean isSymmetric(BinaryTreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(BinaryTreeNode root1, BinaryTreeNode root2){

		if(root1 == null && root2 == null) return true;
		if(root1 == null && root2 != null ||
		root1 != null && root1 == null) return false;

		return (root1.data == root2.data) &&
			isMirror(root1.left, root2.right) &&
			isMirror(root1.right, root2.left);
	}


	public boolean isSymmetricItr(BinaryTreeNode root) {
		//return isMirror(root, root);
		Queue<BinaryTreeNode> q = new LinkedList<>();

		q.offer(root);
		q.offer(root);

		while(!q.isEmpty()){
			BinaryTreeNode root1 = 	q.poll();
			BinaryTreeNode root2 = 	q.poll();

			if (root1 == null && root2 == null) continue;
			if (root1 == null || root2 == null) return false;

			if(root1.data != root2.data) return false;

			q.offer(root1.left);
			q.offer(root2.right);
			q.offer(root1.right);
			q.offer(root2.left);
		}
		return true;
	}


	public static void main(String arg[]){
//1,2,3,4,5,6,7

		/**
		 * 						1
		 * 			2							3
		 * 		4		5				6				7
		 */
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.left = new BinaryTreeNode(2);
		root.right = new BinaryTreeNode(3);
		root.left.left = new BinaryTreeNode(4);
		root.left.right = new BinaryTreeNode(5);
		root.right.left = new BinaryTreeNode(6);
		root.right.right = new BinaryTreeNode(7);
		CommonFunction c = new CommonFunction();
		// c.verticalTraversal(root);
		BinaryTreeNode d = c.invertBinaryTreeItr(root);
		c.display(d);
	}


}
