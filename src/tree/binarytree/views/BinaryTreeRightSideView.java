package tree.binarytree.views;

import tree.binarytree.BinaryTreeNode;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 199. Binary Tree Right Side View
 *
 * The approach would be using BFS
 *    1. Put the root node to that queue
 *    2. remove node from queue
 *      2.1.  Add the node to the output list
 *      2.2. If the node has right child put the right child to queue.
 *      2.3. If the node does not have right child put the left child to the queue.
 *   3. repeat the step 2 until the queue becomes empty.
 *
 *
 *   The above approach didn't worked in letcoode submission
 *
 *   Another approach is do the level order traversal and the last node of each level to the list
 *
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(BinaryTreeNode root) {


        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<BinaryTreeNode> q= new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()){
            BinaryTreeNode n =q.poll();
            result.add(n.data);

            if(n.right != null){
                q.offer(n.right);
            } else if(n.left != null){
                q.offer(n.left);
            }

        }
        return result;

    }


    public static void main(String arg[]){
//1,2,3,4,5,6,7

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        //root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        //root.right.left = new BinaryTreeNode(6);
        //root.right.right = new BinaryTreeNode(4);
        BinaryTreeRightSideView c = new BinaryTreeRightSideView();
        c.rightSideView(root);
    }

}
