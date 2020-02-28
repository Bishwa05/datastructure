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
            int subSum = sum - node.getData();

            if(subSum ==0 && node.getLeft() == null && node.getRight() == null) {
                return true;
            }

            if(node.getLeft() != null) {
                ans = ans || hasSumPath(node.getLeft(), subSum);
            }
            if(node.getRight() != null) {
                ans = ans || hasSumPath(node.getRight(), subSum);
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

        left.setLeft(left11);
        left.setRight(right11);
        right.setLeft(left21);
        right.setRight(right21);
        root.setLeft(left);
        root.setRight(right);

        return root;
    }

}
