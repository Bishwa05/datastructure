import java.util.LinkedList;

public class GraphAdjListRep {

    private int numVertices;
    private LinkedList<Integer> adjLists[];

    GraphAdjListRep(int vertices)
    {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList();
    }

    void addEdge(int src, int dest)
    {
        adjLists[src].add(dest);
    }

    public static void main(String args[])
    {
        GraphAdjListRep g = new GraphAdjListRep(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
    }
}
