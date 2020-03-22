package graph.dag;

import java.util.Arrays;

/**
 *                  A
 *             B        C
 *                  D
 *             E        F
 */
public class MyDependencyResolver {

    public static void main(String arg[]) {
        MyDAG myDag = new MyDAG();

        myDag.createMyDAG('A', null);

        myDag.createMyDAG('B', Arrays.asList('A'));
        myDag.createMyDAG('C', Arrays.asList('A'));
        myDag.createMyDAG('D', Arrays.asList('B', 'C'));
        myDag.createMyDAG('E', Arrays.asList('D'));
        myDag.createMyDAG('F', Arrays.asList('D'));

        myDag.printAllParentsPerNode();

        //Error found
        myDag.createMyDAG('B', Arrays.asList('F'));

        myDag.printAllParentsPerNode();

        //Again Error found
        myDag.createMyDAG('D', Arrays.asList('E'));

        myDag.printAllParentsPerNode();

    }
}
