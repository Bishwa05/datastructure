package tree.binarytree.binaryindextree;

/**
 * Leetcode,
 * 307. Range Sum Query - Mutable
 *
 *  Binary Indexed Trees (BIT or Fenwick tree):
 * 	 https://www.topcoder.com/community/data-science/data-science-
 * 	 tutorials/binary-indexed-trees/
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 9 = sum([1,3,5])
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // return 8 = sum([1,2,5])
 *
 */
public class NumArray
{

    int[] nums;
    int[] BIT;
    int n;

    public NumArray(int[] nums){
        this.nums = nums;

        n = nums.length;
        BIT = new int[n+1];

        for(int i =0; i<n; i++){
            init(i, nums[i]);
        }
    }

    public void init(int i, int val){
        i++;
        while(i<=n){
          BIT[i] +=val;
          i=getNext(i);
        }
    }

    public void update(int i, int val){
        int diff = val - nums[i];
        nums[i] = val;
        init(i, diff);
    }

    public int getSum(int i){
        int sum =0;
        i++;
        while(i>0){
            sum+=BIT[i];
            i=getParent(i);
        }
        return sum;
    }

    private int sumRange(int i, int j){
        return getSum(j) - getSum(i-1);
    }


    /**
     * To get parent
     * 1. 2's complement to get minus of index
     * 2. AND this with index
     * 3. subtract that from index
     */
    private int getParent(int index) {
        return index - (index & -index);
    }

    /**
     * To get next
     * 1. 2's complement to get minus of index
     * 2. AND this with index
     * 3. add it to index
     */
    private int getNext(int index){
        return index + (index & -index);
    }

    public static void main(String arg[]){
        int nums[] = {1, 3, 5};
         NumArray numArray = new NumArray(nums);
         System.out.println(numArray.sumRange(0, 2));
         numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
