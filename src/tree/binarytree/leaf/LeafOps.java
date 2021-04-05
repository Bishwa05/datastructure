package tree.binarytree.leaf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
			if(tmp.left == null && tmp.right == null)
				count++;
			if(tmp.left != null)
				q.offer(tmp.left);
			if(tmp.right != null)
				q.offer(tmp.right);
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
			if(tmp.left != null && tmp.right !=null)
				count++;
			if(tmp.left != null)
				q.offer(tmp.left);
			if(tmp.right != null)
				q.offer(tmp.right);
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
			if((tmp.left == null && tmp.right !=null)||
					(tmp.left != null && tmp.right ==null))
				count++;
			if(tmp.left != null)
				q.offer(tmp.left);
			if(tmp.right != null)
				q.offer(tmp.right);
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
		path[pathLen] = root.data;
		pathLen++;
		
		// its a leaf. So print the path that led to here
		if(root.left == null && root.right == null) {
			printArray(path, pathLen);
		} else {
			// otherwise try both subtrees
			printPaths(root.left, path, pathLen);
			printPaths(root.right, path, pathLen);
		}
	}
	
	public void printArray(int[] ints, int len) {
		for(int i=0; i<len; i++) {
			System.out.println(ints[i]+" ");
		}
		System.out.println();
	}


	// Done in leetcode 257. Binary Tree Paths
	public List<String> binaryTreePaths(BinaryTreeNode root) {
		List<String> res = new ArrayList<>();

		dfs(root, new ArrayList<>(), res);
		return res;
	}

	public void dfs(BinaryTreeNode root, List<Integer> temp, List<String> res){
		temp.add(root.data);
		if(root.left == null && root.right == null){
			StringBuilder sb = new StringBuilder();
			for(int i : temp) {
				sb.append(i).append("->");
			}
			sb.delete(sb.length()-2, sb.length());
			res.add(sb.toString());

		}

		if(root.left != null){
			dfs(root.left, temp, res);
			if(temp.size()>0)
				temp.remove(temp.size()-1);
		}
		if(root.right != null){
			dfs(root.right, temp, res);
			if(temp.size()>0)
				temp.remove(temp.size()-1);
		}

	}
	
	
	//Check the existence of path with given sum
	public boolean hasPathSum(BinaryTreeNode root, int sum) {
		if(root == null)
			return false;
		if(root.left == null && root.right == null && root.data == sum)
			return true;
		else
			return hasPathSum(root.left, sum-root.data) || hasPathSum(root.right, sum -root.data);
	}
	
	// find sum of all elements in a binary tree
	public int addBTRec(BinaryTreeNode root) {
		if(root == null)
			return 0;
		else
			return(root.data+addBTRec(root.left)+addBTRec(root.right));
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
				sum+= curr.data;
				if(curr.left != null)
					q.offer(curr.left);
				if(curr.right != null)
					q.offer(curr.right);
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
		if(root.left == node || root.right == node ||
				printAllAncestors(root.left, node)|| printAllAncestors(root.right, node)) {
			System.out.println(root.data);
			return true;
		}
		return false;
	}
	


	/**
	 * 1302. Deepest Leaves Sum
	 * Leetcode.
	 */
	public int deepestLeavesSum(BinaryTreeNode root) {
		int sum =0;
		int finalSum = 0;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);

		while(!q.isEmpty()){
			BinaryTreeNode x = q.poll();

			if(x!= null){
				sum = sum+ x.data;
				if(x.left != null){
					q.offer(x.left);
				}
				if(x.right != null){
					q.offer(x.right);
				}

			}else{
				finalSum = sum;
				sum=0;
				if(!q.isEmpty()){
					q.offer(null);
				}

			}

		}

		return finalSum;

	}

	/**
	 *
	 * 129. Sum Root to Leaf Numbers
	 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
	 *
	 */

	public int sumNumbers(BinaryTreeNode root) {
		return sumNumbersRec(root, ""+root.data);
	}

	public int sumNumbersRec(BinaryTreeNode root, String val) {
		if(root ==null) return 0;

		if(root.left == null && root.right == null){
			int pathSum = Integer.parseInt(val);
			return pathSum;
		}

		if(root.left != null && root.right == null){
			return sumNumbersRec(root.left, ""+val + root.left.data);
		} else if(root.right != null && root.left == null){
			return sumNumbersRec(root.right, ""+val + root.right.data);
		} else {

			return sumNumbersRec(root.left, "" + val + root.left.data) + sumNumbersRec(root.right,
				"" + val + root.right.data);
		}
	}

	/**
	 * Another better solution
	 */
	int sum = 0;
	int val = 0;
	public int sumNumbers2(BinaryTreeNode root) {
		if(root == null)
			return 0;

		val = val*10 + root.data;

		if(root.left == null && root.right == null) {
			sum += val;
		}

		sumNumbers2(root.left);
		sumNumbers2(root.right);
		val = val/10;

		return sum;
	}
}
