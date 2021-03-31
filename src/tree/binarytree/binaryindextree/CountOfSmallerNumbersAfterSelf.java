package tree.binarytree.binaryindextree;
/**
 *
 * Leetcode 315. Count of Smaller Numbers After Self
 *
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Node{
    Node left, right;
    int val, sum, dup =1;

    public Node(int v, int s){
        val = v;
        sum = s;
    }
}
public class CountOfSmallerNumbersAfterSelf
{

    private Node insert(int num, Node node, Integer[] ans, int i, int preSum){
        if(node == null){
            node = new Node(num, 0);
            ans[i]= preSum;
        } else if(node.val == num){
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if(node.val>num){
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else{
            node.right = insert(num, node.right, ans, i, preSum+node.dup + node.sum);
        }
        return node;
    }

    // Time limit exceeded in Leetcode
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for(int i = nums.length-1; i>=0;i--){
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }

    //Optimized
    public List<Integer> countSmaller2(int[] nums) {

        List<Integer> res = new ArrayList<>(nums.length);
        List<Integer> sortedList = new ArrayList<>();

        if (nums == null || nums.length == 0)
            return res;

        res.add(0);
        sortedList.add(nums[nums.length-1]);

        for (int i = nums.length - 2; i >= 0; i--) {
            int index = binarySearch(sortedList, 0, res.size()-1, nums[i]);
            res.add(0, index+1);
            sortedList.add(index+1, nums[i]);
        }

        return res;
    }
    private int binarySearch(List<Integer> sorted, int left, int right, int target) {
        while(left+1<right) {
            int mid = (left+right)/2;
            if(sorted.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if(sorted.get(right) < target) return right;
        if(sorted.get(left) < target) return left;
        return -1;
    }

    /**
     * ***********************
     * Merge sort approach
     */
    int[] count;
    public List<Integer> countSmaller3(int[] nums) {
        List<Integer> res=new ArrayList<>();
        count=new int[nums.length];
        int indexes[]=new int[nums.length];
        for(int i=0;i<nums.length;i++)
            indexes[i]=i;
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
            res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
        if(end <= start){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
        int mid = (start + end) / 2;
        int left_index = start;
        int right_index = mid+1;
        int rightcount = 0;
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;
        while(left_index <= mid && right_index <= end){
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index];
                rightcount++;
                right_index++;
            }else{
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightcount;
            left_index++;
            sort_index++;
        }
        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for(int i = start; i <= end; i++){
            indexes[i] = new_indexes[i - start];
        }
    }




    public static void main(String arg[]){
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        int[] nums = {5,2,6,1};
        c.countSmaller2(nums).forEach(e -> System.out.println(e));
    }

}
