package disjointset;

import java.util.HashMap;
import java.util.Map;

/**
 * Uses: To find cycle in a undirected graph.
 *
 * Kruskal's Algorithm uses Disjoint Sets.
 *
 *
 * MakeSet : prepare a set atleast with 1 elemnet
 * Union : Takes 2 different sets and merge them into 1 set.
 * FindSet: An operation to return an identity of a set which usually
 * an element in a set which acts as representative of that set.
 */

class Node {
    int rank;
    long data;
    Node parent;

    Node(long data){
        this.data = data;
    }
}
public class DisJointSetImpl {
    Map<Long, Node> map = new HashMap<>();

    /**
     * Create a set with only 1 element.
     */
    public void makeSet(long i) {
        Node newnode = new Node(i);
        newnode.rank = 0;
        newnode.parent = newnode;
        map.put(i, newnode);

    }

    /**
     *
     * Merge 2 set to 1.
     * Does union by rank.
     */
    public void union(long data1, long data2){
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if(parent1.data == parent2.data){
            return;
        }

        if(parent1.rank >= parent2.rank){
            // Increment the rank if both the set have same rank

            parent1.rank = (parent1.rank == parent2.rank)? parent1.rank+1: parent1.rank;
            parent2.parent = parent1;
        } else{
            parent1.parent = parent2;
        }
    }

    public Node findSet(Node node){
        Node parent = node.parent;
        if(node == parent){
            return parent;
        }
        //Path compression with setting the parent
        node.parent = findSet(node.parent);
        return node.parent;
    }

    public long findSet(long data){
        return findSet(map.get(data)).data;
    }

    public static void main(String[] args)
    {
        DisJointSetImpl d = new DisJointSetImpl();

        d.makeSet(1);
        d.makeSet(2);
        d.makeSet(3);
        d.makeSet(4);
        d.makeSet(5);
        d.makeSet(6);
        d.makeSet(7);

        d.union(1,2);
        d.union(2,3);
        d.union(4,5);
        d.union(6,7);
        d.union(5,6);
        //d.union(3,7);

        System.out.println(d.findSet(1));
        System.out.println(d.findSet(2));
        System.out.println(d.findSet(3));
        System.out.println(d.findSet(4));
        System.out.println(d.findSet(5));
        System.out.println(d.findSet(6));
        System.out.println(d.findSet(7));


    }
}
