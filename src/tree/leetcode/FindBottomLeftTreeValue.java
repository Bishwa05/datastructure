package tree.leetcode;

import tree.binarytree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue
{

    int res = 0;
    int depth = 0;
    public int findBottomLeftValue(BinaryTreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(BinaryTreeNode root, int d) {
        if (root == null) return;
        if (d == depth) {
            res = root.data;
            depth++;
        }
        dfs(root.left, d+1);
        dfs(root.right, d+1);
    }


    public int findBottomLeftValue2(BinaryTreeNode root) {

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.offer(root);

        if(root == null) {
            return -1;
        }

        int leftMostNode = -1;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                BinaryTreeNode trav = q.poll();
                // Keep Track of left most node at every level
                if(size == 1) {
                    leftMostNode = trav.data;
                }
                if(trav.right != null) {
                    q.offer(trav.right);
                }
                if(trav.left != null) {
                    q.offer(trav.left);
                }
                size--;
            }
        }
        return leftMostNode;
    }
}
