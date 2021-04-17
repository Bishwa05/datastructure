package graph.scc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * We can find all strongly connected components in O(V+E) time using Kosaraju’s algorithm.
 * Following is detailed Kosaraju’s algorithm.
 * 1) Create an empty stack ‘S’ and do DFS traversal of a graph.
 * In DFS traversal, after calling recursive DFS for adjacent vertices of a vertex,
 * push the vertex to stack. In the above graph, if we start DFS from vertex 0,
 * we get vertices in stack as 1, 2, 4, 3, 0.
 * 2) Reverse directions of all arcs to obtain the transpose graph.
 * 3) One by one pop a vertex from S while S is not empty. Let the popped vertex be ‘v’.
 * Take v as source and do DFS (call DFSUtil(v)). The DFS starting from v prints strongly
 * connected component of v. In the above example, we process vertices in order 0, 3, 4, 2, 1
 * (One by one popped from stack).
 *
 *
 */
public class KosarajuAlgorithm

{
    // Driver method
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components "+
            "in given graph ");
        g.printSCCs();
    }
}

class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List

    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w)  { adj[v].add(w); }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose()
    {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack stack)
    {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(new Integer(v));
    }

    // The main function that finds and prints all strongly
    // connected components
    void printSCCs()
    {
        Stack stack = new Stack();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        // Create a reversed graph
        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by Stack
        while (stack.empty() == false)
        {
            // Pop a vertex from stack
            int v = (int)stack.pop();

            // Print Strongly connected component of the popped vertex
            if (visited[v] == false)
            {
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }


}