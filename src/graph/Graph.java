package graph;

import java.util.*;

public class Graph {
    private final int maxVertex = 20;
    public Vertex vertexList[];
    public int adjMatrix[][];
    public int vertexCount;

    //For dfs traversal
    public Stack<Integer> theStack;

    //For bfs traversal
    public Queue<Integer> theQueue;

    public Graph() {
        vertexList = new Vertex[maxVertex];
        adjMatrix = new int[maxVertex][maxVertex];
        vertexCount = 0;

        for(int y=0; y<maxVertex; y++)
            for(int x=0; x<maxVertex; x++)
                adjMatrix[x][y] =0;

            theStack = new Stack();

            theQueue = new LinkedList<>();

    }

    public void addVertex(char lab) {
        vertexList[vertexCount++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.println(vertexList[v].label);
    }
}
