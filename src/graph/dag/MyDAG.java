package graph.dag;

import java.util.*;

class MyDAGNode {

    public char data;
    public Set<Character> parentSet;


    MyDAGNode(char data) {
        this.data = data;
        this.parentSet = null;
    }
}
public class MyDAG {

    MyDAGNode root;
    Map<Character, MyDAGNode> pathMap = new HashMap<>();



    public void createMyDAG(char data, List<Character> parentDataList){
        MyDAGNode currentNode = null;

        if(pathMap.get(data)!= null){
            currentNode = pathMap.get(data);
        } else {
            currentNode = new MyDAGNode(data);
        }

        if(this.root == null) {
            this.root = currentNode;
        } else {

            if(parentDataList.size()>0) {
                Set<Character> parentS = new HashSet<>();
                for(int i=0; i<parentDataList.size(); i++){

                    Character p = parentDataList.get(i);
                    parentS.add(p);
                    MyDAGNode parentNode = pathMap.get(p);
                    if (parentNode !=null && parentNode.parentSet != null) {
                        if(parentNode.parentSet.contains(currentNode.data)) {
                            StringBuffer sb = new StringBuffer();
                            parentDataList.forEach((e) ->sb.append(e));
                            System.out.println("Cyclic Dependency found: Execution broken  at "+data+" with parent : " +sb.toString());
                            return;
                        }
                        parentS.addAll(parentNode.parentSet);
                    }

                }
                currentNode.parentSet = parentS;
            }
        }

        pathMap.put(data,currentNode);

    }


    public void printAllParentsPerNode(){
        for(Map.Entry e : pathMap.entrySet()) {
            Set<Character> parentSet = ((MyDAGNode)e.getValue()).parentSet;
            System.out.print(e.getKey()+ " ----");
            if(parentSet != null){
                for(Character c : parentSet){
                    System.out.print(c+ ", ");
                }
            } else {
                System.out.print(" ");
            }
            System.out.println("");
        }
    }


}
