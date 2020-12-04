package tree.other;

/**
 *
 * A segment tree is a binary tree where each node represents an interval.
 * Generally a node would store one or more properties of an interval which can be
 * queried later.
 *
 *
 * Many problems require that we give results based on query over a range or
 * segment of available data. This can be a tedious and slow process, especially
 * if the number of queries is large and repetitive. A segment tree let's us process
 * such queries efficiently in logarithmic order of time.
 *
 *
 *
 * arr[] = { 18, 17, 13, 19, 15, 11, 20, 12, 33, 25 };
 *
 * tree[] = { 183, 82, 101, 48, 34, 43, 58, 35, 13, 19, 15, 31, 12, 33, 25, 18, 17,
 * 0, 0, 0, 0, 0, 0, 11, 20, 0, 0, 0, 0, 0, 0 };
 */

public class SegmentTree {

    TreeNode root = null;

    public SegmentTree(int[] nums){
        if(nums==null || nums.length ==0)
            return;
        this.root = buildTree(nums, 0, nums.length-1);
    }

    void update(int i, int val){
        updateHelper(root, i, val);
    }

    void updateHelper(TreeNode root, int i, int val){
        if(root == null)
            return;

        int mid = root.start + (root.end - root.start)/2;

        if(i <=mid){
            updateHelper(root.leftChild, i, val);
        } else{
            updateHelper(root.rightChild, i, val);
        }

        if(root.start == root.end && root.start ==i){
            root.sum = val;
            return;
        }
        root.sum = root.leftChild.sum+root.rightChild.sum;
    }

    public int sumRange(int i, int j){
        return sumRangeHelper(root, i, j);
    }
    public int sumRangeHelper(TreeNode root, int i, int j){
        if(root == null || j< root.start || i> root.end || i>j){
            return 0;
        }

        if(i<= root.start && j>=root.end){
            return root.sum;
        }
        int mid = root.start + (root.end - root.start)/2;
        int result = sumRangeHelper(root.leftChild, i, Math.min(mid, j))
            + sumRangeHelper(root.rightChild, Math.max(mid+1, i), j);
        return result;
    }

    public TreeNode buildTree(int[] nums, int i, int j){
        if(nums==null || nums.length==0 || i>j)
            return null;
        if(i ==j){
            return new TreeNode(i, j, nums[i]);
        }

        TreeNode current = new TreeNode(i, j);
        int mid = i+(j-i)/2;
        current.leftChild = buildTree(nums, i, mid);
        current.rightChild = buildTree(nums, mid+1, j);
        current.sum = current.leftChild.sum + current.rightChild.sum;
        return current;
    }

}
