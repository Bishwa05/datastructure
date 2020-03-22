package graph.dag;

import java.util.Set;

public class MyDAGNode {

    public char data;
    public Set<Character> parentSet;


    MyDAGNode(char data)
    {
       this.data = data;
       this.parentSet = null;
    }




}
