package linklist.singly;

import java.util.HashMap;
import java.util.Map;

class RLNode {
    private int data;
    private RLNode next;
    private RLNode random;

    public RLNode(int data){
        this.data= data;
        this.next = null;
        this.random = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public RLNode getNext() {
        return next;
    }

    public void setNext(RLNode next) {
        this.next = next;
    }

    public RLNode getRandom() {
        return random;
    }

    public void setRandom(RLNode random) {
        this.random = random;
    }
}

public class RandomListOperation {

    /**
     * Given a singly linked list contains a random pointer which points to
     * random node of a list. Clone this list
     *
     * 	1. Scan the original list for each node X, create a new node Y for data of
     * 	X, then store pair(X,Y) in a hashmap. In this scan set Y.next and Y.random
     * 	to NULL.
     * 	2. Scan again the original list and use hashmap to build the new list.
     */
    public RLNode cloneRL(RLNode head) {
        RLNode x = head, y;
        Map<RLNode, RLNode> map = new HashMap<>();
        while(x!= null) {
            y = new RLNode(x.getData());
            map.put(x,y);
            x = x.getNext();
        }
        x = head;
        while(x != null){
            y = map.get(x);
            y.setNext(x.getNext());
            y.setRandom(x.getRandom());
            x = x.getNext();
        }
        return map.get(head);
    }
}
