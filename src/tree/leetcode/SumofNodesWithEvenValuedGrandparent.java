package tree.leetcode;

/**
 *
 * 1315. Sum of Nodes with Even-Valued Grandparent
 *
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 *
 */
public class SumofNodesWithEvenValuedGrandparent
{
    int sum =0;
    public int sumEvenGrandparent(TreeNode root) {

        findSum(root, null, null);
        return sum;
    }

    public void findSum(TreeNode curr, TreeNode parent,
                        TreeNode gp){

        if(curr.left != null){
            if(parent != null){
                findSum(curr.left, curr, parent);
            } else {
                findSum(curr.left, curr, null);
            }
        }

        if(gp != null && gp.val%2==0){
            sum += curr.val;
        }

        if(curr.right != null){
            if(parent != null){
                findSum(curr.right, curr, parent);
            } else {
                findSum(curr.right, curr, null);
            }
        }
    }
}
