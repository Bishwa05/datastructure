package graph.more;

import java.util.Stack;

/**
 * An Eulerian path is a path (not necessarily simple) that
 *  uses every edge in the graph exactly once.
 *
 *  v and w are 2 vertices to which the edge connects to.
 */
public class EulerianPath {
    private Stack<Integer> path = null;

    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        // returns the other vertex of the edge
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }
}
