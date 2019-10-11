package graph.kruskal;

import java.util.*;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String toString() {
        return "(" +src+ " , " +dest+ " , " +weight+ " )";
    }
}

class DisjointSet
{

    Map<Integer, Integer> parent = new HashMap<>();

    // N Disjoint set 1 for each vertex
    public void makeSet(int N)
    {

        for(int i=0; i<N; i++)
            parent.put(i,i);
    }

    // find the root of the set in which element k belongs.
    private int find(int k)
    {
        if(parent.get(k) == k)
            return k;

        return find(parent.get(k));
    }

    //perform union of 2 subsets
    private void union(int a, int b)
    {
        int x = find(a);
        int y = find(b);
        parent.put(x,y);
    }


    //Construct MST using kruskal's algorithm
    public static List<Edge> kruskalsAlgo (List<Edge> edges, int vertex)
    {
        List<Edge> mst = new ArrayList<>();

        DisjointSet ds = new DisjointSet();
        ds.makeSet(vertex);

        int index =0;

        //MST contains exactly v-1 edges
        while(mst.size() != vertex-1) {

            //Consider the next edge with minimum weight from graph
            Edge nextEdge = edges.get(index++);

            //Find the root of the sets to which two endpont
            // vertices of next edge belongs
            int x = ds.find(nextEdge.src);
            int y = ds.find(nextEdge.dest);

            // If both the endpoints have different parents, they
            // belong to different connected components and can be
            // included in mst.
            if (x != y){
                mst.add(nextEdge);
                ds.union(x,y);
            }

        }
        return mst;
    }

}
public class Kruskals {

    public static void main(String arg[]){
        // (u, v, w) tiplet represent undirected edge from
        // vertex u to vertex v having weight w
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 7), new Edge(1, 2, 8),
                new Edge(0, 3, 5), new Edge(1, 3, 9),
                new Edge(1, 4, 7), new Edge(2, 4, 5),
                new Edge(3, 4, 15), new Edge(3, 5, 6),
                new Edge(4, 5, 8), new Edge(4, 6, 9),
                new Edge(5, 6, 11)
        );

        // sort edges by increasing weight
        Collections.sort(edges, (a, b) -> a.weight - b.weight);

        // Number of vertices in the graph
        final int N = 7;

        // construct graph
        List<Edge> e = DisjointSet.kruskalsAlgo(edges, N);

        for (Edge edge: e) {
            System.out.println(edge);
        }
    }

}
