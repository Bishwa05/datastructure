package graph;

import java.util.Stack;

public class Graph {
    private final int maxVertex = 20;
    public Vertex vertexList[];
    public int adjMatrix[][];
    public int vertexCount;
    public Stack<Integer> theStack;

    public Graph() {
        vertexList = new Vertex[maxVertex];
        adjMatrix = new int[maxVertex][maxVertex];
        vertexCount = 0;

        for(int y=0; y<maxVertex; y++)
            for(int x=0; x<maxVertex; x++)
                adjMatrix[x][y] =0;
            theStack = new Stack();
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
