package graph.dijkstras;

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

//To store heap Nodes
class Node
{
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Graph
{
    List<List<Edge>> adjList = null;

    Graph(List<Edge> edgeList, int vertex){

        adjList = new ArrayList<>(vertex);

        for(int i=0; i<vertex; i++)
            adjList.add(i, new ArrayList<>());

        for(Edge edge : edgeList)
            adjList.get(edge.src).add(edge);

    }

}

public class Dijkstras {

    private static void printRoute(int prev[], int i)
    {
        if (i < 0)
        return;

        printRoute(prev, prev[i]);
        System.out.print(i + " ");
    }

    public static void shortestPath(Graph graph, int source, int N)
    {
        // create min heap and push source node having distance 0
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
            (lhs, rhs) -> lhs.weight - rhs.weight);

        minHeap.add(new Node(source, 0));

        // set infinite distance from source to v initially
        List<Integer> dist = new ArrayList<>(
            Collections.nCopies(N, Integer.MAX_VALUE));

        // distance from source to itself is zero
        dist.set(source, 0);

        // boolean array to track vertices for which minimum
        // cost is already found
        boolean[] done = new boolean[N];
        done[0] = true;

        // stores predecessor of a vertex (to print path)
        int prev[] = new int[N];
        prev[0] = -1;

        // run till minHeap is not empty
        while (!minHeap.isEmpty())
        {
            // Remove and return best vertex
            Node node = minHeap.poll();

            // get vertex number
            int u = node.vertex;

            // do for each neighbor v of u
            for (Edge edge: graph.adjList.get(u))
            {
                int v = edge.dest;
                int weight = edge.weight;

                // Relaxation step
                if (!done[v] && (dist.get(u) + weight) < dist.get(v))
                {
                    dist.set(v, dist.get(u) + weight);
                    prev[v] = u;
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }

            // marked vertex u as done so it will not get picked up again
            done[u] = true;

        }

        for (int i = 1; i < N; ++i)
        {
            System.out.print("Path from vertex 0 to vertex " + i +
                    " has minimum cost of " + dist.get(i) +
                    " and the route is [ ");
            printRoute(prev, i);
            System.out.println("]");
        }
    }

    public static void main(String[] args)
    {
        // initialize edges as per above diagram
        // (u, v, w) triplet represent undirected edge from
        // vertex u to vertex v having weight w
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10), new Edge(0, 4, 3),
            new Edge(1, 2, 2), new Edge(1, 4, 4),
            new Edge(2, 3, 9), new Edge(3, 2, 7),
            new Edge(4, 1, 1), new Edge(4, 2, 8),
            new Edge(4, 3, 2)
    );

        // Set number of vertices in the graph
        final int N = 5;

        // construct graph
        Graph graph = new Graph(edges, N);

        shortestPath(graph, 0, N);
    }
}
