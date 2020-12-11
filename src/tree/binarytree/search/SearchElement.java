package tree.binarytree.search;

import java.util.LinkedList;
import java.util.Queue;

import com.sun.source.tree.BinaryTree;
import tree.binarytree.BinaryTreeNode;

public class SearchElement {
	
	public int maxInBinaryTreeRec(BinaryTreeNode root) {
		int max = Integer.MIN_VALUE;
		
		if(root != null) {
			int leftMax = maxInBinaryTreeRec(root.left);
			int rightMax = maxInBinaryTreeRec(root.right);
			
			if(leftMax>rightMax)
				max = leftMax;
			else
				max = rightMax;
			
			if(root.data>max)
				max = root.data;
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
			if(curr.data>max)
				max = curr.data;
			if(curr != null) {
				if(curr.left != null)
					q.offer(curr.left);
				if(curr.right != null)
					q.offer(curr.right);
			}
		}
		return max;
	}

	/**
	 * Find an element in binary tree in recursive manner
	 * @param root
	 * @param data
	 * @return
	 */
	
	public boolean findInBinaryTreeRec(BinaryTreeNode root, int data) {
		if(root == null)
			return false;
		if(root.data == data)
			return true;
		return findInBinaryTreeRec(root.left, data)|| findInBinaryTreeRec(root.right, data);
	}

	/**
	 * Find an element in Binary tree, Using iterative
	 * @param root
	 * @param data
	 * @return
	 */
	public boolean findInBinaryTreeItr(BinaryTreeNode root, int data) {
		if(root == null)
			return false;
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if(curr.data == data)
				return true;
			if(curr != null) {
				if(curr.left != null)
					curr= curr.left;
				if(curr.right != null)
					curr = curr.right;

				((LinkedList<BinaryTreeNode>) q).push(curr);
			}
		}
		return false;
	}

	/**
	 * Find the Deepest Node in the binary tree.
	 * @param root
	 * @return
	 */
	public BinaryTreeNode deepestNodeInBT(BinaryTreeNode root) {
		 if(root == null)
			 return null;
		 BinaryTreeNode tmp = null;
		 Queue<BinaryTreeNode> q = new LinkedList<>();
		 q.offer(root);
		 while(!q.isEmpty()) {
			 tmp = q.poll();
			 if(tmp.left != null)
				 q.offer(tmp.left);
			 if(tmp.right != null)
				 q.offer(tmp.right);		 
		}
		return tmp;
	}

	/**
	 * Find the level of tree with maximum sum.
	 * @param root
	 * @return
	 */
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
				currentSum += curr.data;
				if(curr.left != null)
					q.offer(curr.left);
				if (curr.right != null)
					q.offer(curr.right);
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


	/**
	 * Check whether the given two trees are isomorphic or not.
	 * root1 and root2 is isomorphic if they have same structure.
	 */
	public boolean isIsomorphic(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null && root2 == null) {
			return true;
		}
		if((root1 == null && root2 !=null) || (root1 != null && root2 == null))
			return false;
		return (isIsomorphic(root1.left,root2.left) &&
				isIsomorphic(root1.right, root2.right));
	}

	/**
	 * Check whether trees are quasi-isomorphic to each other
	 * the trees root1 and root 2 are quasi-isomorphic, if root1 can be transformed
	 * into root2 by swapping the left and right children of root1.
	 */
	public boolean quasiIsomorphic(BinaryTreeNode root1, BinaryTreeNode root2) {
		if(root1 == null && root2 == null){
			return true;
		}
		if((root1 == null && root2 !=null) || (root1 != null && root2 == null))
			return false;
		return  ((quasiIsomorphic(root1.left, root2.left)
		&& quasiIsomorphic(root1.right, root2.right)) ||(
				quasiIsomorphic(root1.right, root2.left) &&
						quasiIsomorphic(root1.left, root2.right)));

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
        left = LCA(root.left, a, b);
        right = LCA(root.right, a, b);
        if(left != null && right != null)
            return root; //nodes are each on a separate branch
        else
            return(left != null? left:right);
        //Either 1 node is on 1 branch or none was found in any of the branches
    }

}
