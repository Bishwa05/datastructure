package tree.binarytree;

/**
 *
 *                      10
 *               8              6
 *          3          5    9       4
 */

public class FindHasPathSumFromRoot {

    public static boolean hasSumPath(BinaryTreeNode node, int sum)  {
        boolean ans =false;
        if(node == null)
            return sum == 0;
        else {
            int subSum = sum - node.data;

            if(subSum ==0 && node.left == null && node.right == null) {
                return true;
            }

            if(node.left != null) {
                ans = ans || hasSumPath(node.left, subSum);
            }
            if(node.right != null) {
                ans = ans || hasSumPath(node.right, subSum);
            }
        return ans;
        }
    }



    public static void main(String arg[]) {

        int sum = 25; //10+6+7

        BinaryTreeNode root = generateTree();
        System.out.println(hasSumPath(root, sum));


    }
    public static BinaryTreeNode generateTree() {

        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode left = new BinaryTreeNode(8);
        BinaryTreeNode right11 = new BinaryTreeNode(3);
        BinaryTreeNode left11 = new BinaryTreeNode(5);
        BinaryTreeNode right = new BinaryTreeNode(6);
        BinaryTreeNode left21 = new BinaryTreeNode(9);
        BinaryTreeNode right21 = new BinaryTreeNode(4);

        left.left = left11;
        left.right = right11;
        right.left =left21;
        right.right = right21;
        root.left = left;
        root.right = right;

        return root;
    }

}
