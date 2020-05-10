package disjointset;

public class Node {
    int rank;
    long data;
    Node parent;

    Node(long data){
        this.data = data;
    }
}
