package tree.binarytree.views;

import tree.binarytree.BinaryTreeNode;

import javax.swing.tree.TreeNode;
import java.util.*;

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
 *   The above approach didn't worked in letcoode submission which failed in test case
 *      BinaryTreeNode root = new BinaryTreeNode(1);
 *      root.left = new BinaryTreeNode(2);
 *      root.right = new BinaryTreeNode(3);
 *      root.left.left = new BinaryTreeNode(4);
 *
 *   Another approach is do the level order traversal and the last node of each level to the list
 *
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(BinaryTreeNode root) {


        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        List<Integer> interimList = new ArrayList<>();
        Queue<BinaryTreeNode> q= new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            BinaryTreeNode n =q.poll();
            if(n!= null) {
                interimList.add(n.data);

                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
            } else{
                if(!q.isEmpty()){
                    q.add(null);
                }
                result.add(interimList.get(interimList.size()-1));
                interimList.clear();
            }

        }
        return result;

    }


    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView2(BinaryTreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(BinaryTreeNode root, int lv) {
        if (root == null) return;

        if (lv >= res.size()) res.add(root.data);
        // else res.set(lv, root.val); // if dfs left first

        dfs(root.right, lv + 1);
        dfs(root.left, lv + 1);
    }


    public static void main(String arg[]){
//1,2,3,4,5,6,7

//        BinaryTreeNode root = new BinaryTreeNode(1);
//        root.left = new BinaryTreeNode(2);
//        root.right = new BinaryTreeNode(3);
//        //root.left.left = new BinaryTreeNode(4);
//        root.left.right = new BinaryTreeNode(5);
//        //root.right.left = new BinaryTreeNode(6);
//        root.right.right = new BinaryTreeNode(4);


        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        //root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(4);

        BinaryTreeRightSideView c = new BinaryTreeRightSideView();
//        List<Integer> res = c.rightSideView(root);
//        res.forEach(e -> System.out.println(e));


        List<Integer> res = c.rightSideView2(root);
        res.forEach(e -> System.out.println(e));

    }

}
