package graph;

public class Traversal {

    public void dfs(Graph g){
        g.vertexList[0].visited = true;
        g.displayVertex(0);
        g.theStack.push(0);

        while(!g.theStack.isEmpty()){
           //get an unvisited vertex adjacent to stack top
           int v = getUnvisitedAdjVertex(g,g.theStack.peek());
           if(v == -1)
               g.theStack.pop();
           else {
               g.vertexList[v].visited= true;
               g.displayVertex(v);
               g.theStack.push(v);
           }
        }

        //reset the flag
        for(int j=0; j<g.vertexCount; j++)
            g.vertexList[j].visited = false;
    }

    public int getUnvisitedAdjVertex(Graph g,int v){
        for(int j=0; j<g.vertexCount; j++) {
            if(g.adjMatrix[v][j]==1 && g.vertexList[j].visited== false)
                return j;
        }
        return -1;

    }

    public void bfs(Graph g) {
        g.vertexList[0].visited = true;
        g.displayVertex(0);
        g.theQueue.add(0);
        int v2;

        while(!g.theQueue.isEmpty()) {
             int v1 = g.theQueue.remove();
             while((v2 = getUnvisitedAdjVertex(g,v1)) != -1) {
                 g.vertexList[v2].visited = true;
                 g.displayVertex(v2);
                 g.theQueue.add(v2);
             }
        }

        //reset the flag
        for(int j=0; j<g.vertexCount; j++)
            g.vertexList[j].visited = false;
    }
}
