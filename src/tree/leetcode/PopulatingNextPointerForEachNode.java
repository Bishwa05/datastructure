package tree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class PopulatingNextPointerForEachNode
{
    public Node connect(Node root) {

        Queue<Node> q = new LinkedList<>();
        Queue<Node> a = new LinkedList<>();
        if(root == null) return null;
        q.add(root);
        q.add(null);
        root.next = null;
        while(!q.isEmpty()){
            Node n = q.poll();

            if(n != null){
                a.add(n);
                if(n.left != null)
                    q.add(n.left);
                if(n.right != null)
                    q.add(n.right);

            } else{
                Node first = null;
                if(!a.isEmpty()){
                    first = a.poll();
                }
                while(!a.isEmpty()){
                    Node x = a.poll();
                    first.next =x;
                    first = x;
                }

                if(!q.isEmpty()){
                    q.add(null);
                }
            }
        }
        return root;

    }

    /**
     * Faster approach
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        helper(root);
        return root;
    }

    private void helper(Node node) {
        if (node == null) return;
        if (node.left != null) node.left.next = node.right;
        Node lastChild = (node.right != null) ? node.right : node.left;
        if (lastChild != null) {
            lastChild.next = (node.next != null) ? (node.next.left != null) ? node.next.left : node.next.right : null;
        }
        helper(node.left);
        helper(node.right);
    }

}
