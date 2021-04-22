package tree.binarytree.views;


import tree.binarytree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *              12
 *          7           1
 *       4          10      5
 *
 S: 11
 Output: 2

 Explaination: Here are the two paths with sum '11':7 -> 4 . and 1 -> 10.



 */
public class CountPathsForAsum
{
    //int pathCount = 0;
    public int countPaths(BinaryTreeNode root, int S){
        List<Integer> currentPath = new LinkedList<>();

        return dfs(root, S, currentPath);
    }

    public int dfs(BinaryTreeNode node, int S, List<Integer>currentPath){

        if(node == null) return 0;

        currentPath.add(node.data);

        int pathCount=0, pathSum =0;

        ListIterator<Integer> pathItr = currentPath.listIterator(currentPath.size());

        while(pathItr.hasPrevious()){
            pathSum+=pathItr.previous();

            if(pathSum == S){
                pathCount++;
            }

        }

        pathCount+=dfs(node.left, S, currentPath);
        pathCount+=dfs(node.right, S, currentPath);

        currentPath.remove(currentPath.size() -1);

        return pathCount;

    }

    public static void main(String arg[]){
//        BinaryTreeNode root = new BinaryTreeNode(12);
//        root.left = new BinaryTreeNode(7);
//        root.right = new BinaryTreeNode(1);
//        root.left.left = new BinaryTreeNode(4);
//        root.left.right = new BinaryTreeNode(10);
//        root.right.right = new BinaryTreeNode(5);

        BinaryTreeNode root = new BinaryTreeNode(12);
        root.left = new BinaryTreeNode(7);
        root.right = new BinaryTreeNode(1);
        root.left.left = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(10);
        root.right.right = new BinaryTreeNode(5);


        CountPathsForAsum c = new CountPathsForAsum();

        System.out.println(c.countPaths(root, 11));
    }

}
