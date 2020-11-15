package tree.binarysearchtree;

import java.util.*;

public class Operations
{
    /**
     * 1305. All Elements in Two Binary Search Trees
     * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
     *
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> firstList = new ArrayList<>();

        getAllElementsRec(root1, firstList);
        getAllElementsRec(root2, firstList);
        Collections.sort(firstList);
        return firstList;

    }

    public void getAllElementsRec(TreeNode root1,List<Integer> elementsList){
        if(root1 != null){

            getAllElementsRec(root1.left, elementsList);
            elementsList.add(root1.val);
            getAllElementsRec(root1.right, elementsList);

        }
    }

    //Another efficient approach
    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2){
        List<Integer> out = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        helper1(root1, queue);
        helper2(root2, queue, out);
        while(!queue.isEmpty()){
            out.add(queue.poll());
        }
        return out;
    }

    public void helper2(TreeNode root2, Queue<Integer> queue, List<Integer> out){
        if(root2 == null) return;

        helper2(root2.left, queue, out);

        while(!queue.isEmpty() && root2.val>=queue.peek()){
            out.add(queue.poll());
        }
        out.add(root2.val);
        helper2(root2.right, queue, out);
    }

    public void helper1(TreeNode root1, Queue<Integer>queue){
        if(root1 == null) return;

        helper1(root1.left, queue);
        queue.offer(root1.val);
        helper1(root1.right, queue);
    }
}
