package linklist.circular;

public class CircularLinkedList {
	private CLLNode tail;
	private int length;
	
	public CircularLinkedList() {
		tail = null;
		length =0;
	}
	
	//Add data to beginning of list
	public void add(int data) {
		addToHead(data);
	}

	private void addToHead(int data) {
		CLLNode tmp = new CLLNode(data);
		if(tail == null) {
			tail = tmp;
			tail.setNext(tail);
		}
	}
	
	public void addToTail(int data) {
		addToHead(data);
		tail = tail.getNext();
	}
	
	//Returns data at the head of list
	public int peek() {
		return tail.getNext().getData();
	}
	
	// remove data from head of the list
	public int removeFromHead() {
		CLLNode tmp = tail.getNext();
		if(tail == tail.getNext())
			tail = null;
		else {
			tail.setNext(tmp.getNext());
			tmp.setNext(null);
		}
		length --;
		return tmp.getData();
	}
	
	//remove data from tail of the list
	public int removeFromTail() {
		if(tail == null)
			return Integer.MIN_VALUE;
		CLLNode p = tail;
		while(p.getNext() != tail) {
			p = p.getNext();
		}
		// p now points to second to last data
		CLLNode tmp = tail;
		if(p == tail)
			tail = null;
		else {
			p.setNext(tail.getNext());
			tail = p;
		}
		length--;
		return tmp.getData();
	}
	
	//Return true if the list contains data else false
	public boolean contains(int data) {
		if(tail == null)
			return false;
		CLLNode p= tail.getNext();
		while(p != tail && p.getData() != data)
			p = p.getNext();
		return (p.getData() == data);
	}
	
	// Remove and return element == data
	public int remove(int data) {
		if(tail == null) return Integer.MIN_VALUE;
		CLLNode n = tail.getNext();
		CLLNode p = tail;
		
		for(int i=0; i<length && (!(n.getData() == data)); i++) {
			p = n;
			n = n.getNext();
		}
		
		if(p.getData()== data) {
			if(tail == tail.getNext()) {
				tail = null;
				
			} else {
				if(n == tail)
					tail = p;
				p.setNext(p.getNext().getNext());
			}
			//n data release
			n.setNext(null);
			length--;
			return n.getData();
		} else
			return Integer.MIN_VALUE;
	}
	
	public int size() {
		return length;
	}
	
	//Remove everything from CLL
	public void clear() {
		length =0;
		tail = null;
	}
	
	public String toString() {
		String result ="[";
		if(tail == null)
			return result+"]";
		CLLNode curr = tail.getNext();
		while(curr != tail) {
			result = result+" "+curr.getData();
			curr= curr.getNext();
		}
		return result+"]";
	}


	/**
	 * Split a circular linked list into 2 from mid.
	 * Store the mid and last of circular linked list.
	 * make 2nd half circular and 1st half circular, then set the head.
	 */
	public static void splitList(CLLNode head, CLLNode head1, CLLNode head2){
		CLLNode slowPtr= head, fastPtr = head;
		if(head == null)
			return;
		while((head != fastPtr.getNext()) && (head != fastPtr.getNext().getNext())){
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
		}

		// If there are even elements in the list then move fast ptr
		if(fastPtr.getNext().getNext() == head) {
			fastPtr = fastPtr.getNext();
		}
		//set the headptr of 1st half
		head1 = head;

		//set the headptr of 2nd half
		if(head.getNext() != head)
		head2 = slowPtr.getNext();

		//Make the 2nd half circular
		fastPtr.setNext(slowPtr.getNext());

		//Make the 1st half circular
		slowPtr.setNext(head);
	}

	/**
	 * Get Josehus Position.
	 * N people decided to elect a leader by arranging themselves in a circle
	 * and eliminating every Mth position around the circle.
	 *
	 * Assume the input is a circular linked list with N nodes and ach node has
	 * a number (range 1 to N) associated with it. The head node has number 1 as data.
	 */
	public CLLNode getJosephusPosition(int N, int M) {
		CLLNode p, q;
		//Create Circular linked list containing all the players
		p = new CLLNode(1);
		q = new CLLNode(1);

		for(int i=2; i<= N; i++) {
			CLLNode x = new CLLNode(i);
			p.setNext(x);
			p = p.getNext();
		}
		//Close the circular linked list
		p.setNext(q);

		//eliminate mth player as long as more than 1 player remains
		for(int count =N ; count >1 ; --count){
			for(int i=0; i<M-1; ++i)
				p = p.getNext();
			p.setNext(p.getNext().getNext());
		}
		System.out.println("Lat player lefty standing, Josephus position "+p.getData())
		return p;
	}
}
