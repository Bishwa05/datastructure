package tree.binarytree.views;

import tree.binarytree.BinaryTreeNode;

public class FindCountOfSingleValuedSubTree
{
    int []count = {0};
    public int countSingle(BinaryTreeNode root){
        countSingleRec(root, count);
        return count[0];
    }

    public boolean countSingleRec(BinaryTreeNode root, int[] count){

        if(root == null) return true;

        boolean left = countSingleRec(root.left, count);
        boolean right = countSingleRec(root.right, count);

        if(left == false || right == false) return false;

        if(root.left != null && root.data != root.left.data) return false;

        if(root.right != null && root.data != root.right.data) return false;

        count[0]++;
        return true;
    }

    public static void main(String arg[]){
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(5);


        FindCountOfSingleValuedSubTree f = new FindCountOfSingleValuedSubTree();

        System.out.println(f.countSingle(root));
    }

}
