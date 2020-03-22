package graph.dag;

import java.util.LinkedList;

public class MyDAGNode {

    private char data;
    private LinkedList<Character> childList;
    private LinkedList<Character> parentList;


    MyDAGNode(char data)
    {
       this.data = data;
       this.childList = null;
       this.parentList = null;
    }


}
