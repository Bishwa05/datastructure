package graph.belmanford;

import java.util.HashMap;

public class BellmanFord {

    public static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public String toString() {
            return this.u + " " + this.v;
        }
    }

    public static class Node {
        int val;
        int dist;
        String path;

        public Node(int val, int dist, String path) {
            this.val = val;
            this.dist = dist;
            this.path = path;
        }

        public String toString() {
            return "Distance of " + this.val + " from source is " + this.dist + " and path followed is " + this.path;
        }

    }

    public static Edge[] getEdges(int numEdges, int[][] graph) {
        Edge[] rv = new Edge[numEdges];

        int idx = 0;
        for (int u = 0; u < graph.length && idx < rv.length; u++) {
            for (int v = 0; v < graph[u].length && idx < rv.length; v++) {
                rv[idx] = new Edge(u, v);
                idx = graph[u][v] != 0 ? idx + 1 : idx;
            }
        }

        return rv;
    }

    public static void bellmanford(int src, int nodes, int edges, int[][] graph) {
        /*
         * we use hashmap to store the nodes of every vertex,
         * (vertex name, node) will be the key, value pair
         */
        HashMap<Integer, Node> nodesMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            /*
             * initialize the shortest distance of the every
             *  vertex equal to Infinity as for this vertex
             *  the shortest distance is yet to be calculated,
             *  and initialize an empty path. But if the vertex
             *  is source vertex itself, then the shortest distance
             *  for it will be 0 and path will be initialized with
             *  vertex name.
             */
            nodesMap.put(i, new Node(i, i == src ? 0 : (int) 1e9 + 1, i == src ?
                    Integer.toString(i) : ""));
        }

        /* outer loop will run for vertices - 1 times */
        for (int var = 1; var <= nodes - 1; var++) {
            /* running inner loop on the set of edges returned
             * from getEdges function */
            for (Edge e : getEdges(edges, graph)) {
                Node u = nodesMap.get(e.u);
                Node v = nodesMap.get(e.v);

                /*
                 * the basic condition for updation of shortest
                 * distance of any node as mentioned in the above
                 * discussion.
                 */
                if (v.dist > u.dist + graph[u.val][v.val]) {
                    v.dist = u.dist + graph[u.val][v.val];
                    v.path = u.path + "->" + Integer.toString(v.val);
                }
            }
        }

        /*
         * one more loop in the random set of edges to detect if
         *  there is any negative cycle or not
         */
        for (Edge e : getEdges(edges, graph)) {
            Node u = nodesMap.get(e.u);
            Node v = nodesMap.get(e.v);

            /*
             * if the we still are able to find shorted distance
             * this simply means that there is a negative cycle
             * for sure and hence we return from the function as
             * shortest distance for every vertex from source can
             * not be found for such graph as we can get even
             * shorter distance by looping once again in that
             * negative cycle.
             */
            if (v.dist > u.dist + graph[u.val][v.val]) {
                System.out.println("Negative Cycle Detected");
                return;
            }
        }

        for (int node : nodesMap.keySet()) {
            System.out.println(nodesMap.get(node));
        }

    }

    /**
     * Negative cycle
     * 5 7
     * 1 2 2
     * 1 5 5
     * 1 3 1
     * 2 4 1
     * 3 3 4
     * 4 1 -5
     * 5 2 -6
     * @param args
     */
    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] { {0, -1, 4, 0, 0},
                {0, 0, 3, 2, 2},
                {0, 0, 0, 5, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, -3, 0} };
        int srcVertex = 0;
        int noOfVertex = 5;
        int numEdges = 8;
        bellmanford(srcVertex, noOfVertex, numEdges, graph);

    }
}
