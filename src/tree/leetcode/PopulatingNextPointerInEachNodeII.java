package tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PopulatingNextPointerInEachNodeII
{
    public Node connect(Node root){
        if(root == null) return root;


        Node lastHead = root; // previous levels head
        Node lastCurrent = null; // previous level pointer
        Node currentHead = null; // current level head
        Node current = null; // current level pointer

        while(lastHead != null){
            lastCurrent = lastHead;

            while(lastCurrent != null){
                //left child is not null
                if(lastCurrent.left != null){
                    if(currentHead == null){
                        currentHead = lastCurrent.left;
                        current = lastCurrent.left;
                    } else{
                        current.next = lastCurrent.left;
                        current = current.next;
                    }
                }

                //right child is not null
                if(lastCurrent.right != null){
                    if(currentHead == null){
                        currentHead = lastCurrent.right;
                        current = lastCurrent.right;
                    } else{
                        current.next = lastCurrent.right;
                        current = current.next;
                    }
                }

                lastCurrent = lastCurrent.next;
            }
            //update last head
            lastHead = currentHead;
            currentHead = null;
        }
        return root;
    }

    public Node connectSimple(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return root;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        Node ans = root;
        while(!q.isEmpty()){
            int size = q.size();
            Node curr = q.peek();
            for(int i =0; i<size; i++){
                curr = q.poll();
                curr.next = q.peek();

                if(curr.left != null){
                    q.offer(curr.left);
                }
                if(curr.right != null){
                    q.offer(curr.right);
                }
            }
            curr.next = null;
        }
        return ans;
    }

    public static void main(String arg[]){
        PopulatingNextPointerInEachNodeII p = new PopulatingNextPointerInEachNodeII();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        Node r = p.connectSimple(root);
        System.out.println(r);
    }

}
