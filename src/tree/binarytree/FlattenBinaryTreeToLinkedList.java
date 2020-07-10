package tree.binarytree;

import java.util.Stack;

/**
 * Leetcode
 * 114. Flatten Binary Tree to Linked List
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class FlattenBinaryTreeToLinkedList
{
    public void flatten(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode p = root;

        while(p != null || !stack.empty()){

            if(p.right != null){
                stack.push(p.right);
            }

            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                BinaryTreeNode temp = stack.pop();
                p.right=temp;
            }

            p = p.right;
        }
    }

    public static BinaryTreeNode generateTree() {

        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode left = new BinaryTreeNode(2);
        BinaryTreeNode right = new BinaryTreeNode(5);

        BinaryTreeNode left11 = new BinaryTreeNode(3);
        BinaryTreeNode right11 = new BinaryTreeNode(4);

        BinaryTreeNode right21 = new BinaryTreeNode(6);

        left.left = left11;
        left.right = right11;
        right.right = right21;
        root.left = left;
        root.right = right;

        return root;
    }


    public static void main(String arg[]) {

        BinaryTreeNode root = generateTree();
        FlattenBinaryTreeToLinkedList p = new FlattenBinaryTreeToLinkedList();
        //System.out.println(p.hasSumPath(root, sum));

        p.flatten(root);

        System.out.println(root);


    }
}
