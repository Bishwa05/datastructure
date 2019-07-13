package tree.binarytree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import tree.binarytree.BinaryTreeNode;

public class OtherTraversals {
	
	public ArrayList<ArrayList<Integer>> levelOrder(BinaryTreeNode root){
		ArrayList<ArrayList<Integer>> lTree = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return lTree;
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);
		
		ArrayList<Integer>currlvl = new ArrayList<Integer>();
		
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr !=  null) {
				currlvl.add(curr.getData());
				if(curr.getLeft() != null)
					q.add(curr.getLeft());
				if (curr.getRight() != null)
					q.add(curr.getRight());
			} else {
				ArrayList<Integer> copyCurrLvl = new ArrayList<Integer>();
				copyCurrLvl.addAll(currlvl);
				lTree.add(copyCurrLvl);
				currlvl.clear();
				if(q.isEmpty())
					q.offer(null);
			}
		}
		return lTree;
	}
	
	/**
	 * 
	 * @param root
	 * 
	 * 				1
	 * 			2		3
	 * 		4		5 6		7
	 * 
	 * --> 4 5 6 7 2 3 1
	 */
	public static void levelorderTraversalInReverse(BinaryTreeNode root) {
		if(root == null)
			return;
		
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		Queue<BinaryTreeNode> q= new LinkedList<>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr.getLeft() != null)
				q.offer(curr.getLeft());
			if(curr.getRight() != null)
				q.offer(curr.getRight());
			s.push(curr);
		}
		
		while(!s.isEmpty())
			System.out.println(s.pop().getData()+"");
	}
	
	public ArrayList<ArrayList<Integer>> zigZagLvlOrder(BinaryTreeNode root){
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (root == null)
			return result;
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);
		boolean leftToRight = true;
		ArrayList<Integer> currList = new ArrayList<>();
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr != null) {
				currList.add(curr.getData());
				if(curr.getLeft() != null)
					q.offer(curr.getLeft());
				if(curr.getRight() != null)
					q.offer(curr.getRight());
			} else {
				if(leftToRight) {
					ArrayList<Integer> cCurrList = new ArrayList<>(currList);
					result.add(cCurrList);
					currList.clear();
				}else {
					Stack<Integer> s= new Stack<>();
					s.addAll(currList);
					ArrayList<Integer> cCurrList = new ArrayList<>();
					while(!s.isEmpty()) {
						cCurrList.add(s.pop());
					}
					result.add(cCurrList);
					currList.clear();
				}
				
				if(!q.isEmpty()) {
					q.offer(null);
					leftToRight = !leftToRight;
				}
			}
		}
		return result;
	}

}
