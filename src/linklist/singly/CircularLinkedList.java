package linklist.singly;

public class CircularLinkedList {
	private ListNode tail;
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
        ListNode tmp = new ListNode(data);
		if(tail == null) {
			tail = tmp;
			tail.next=tail;
		}
	}
	
	public void addToTail(int data) {
		addToHead(data);
		tail = tail.next;
	}
	
	//Returns data at the head of list
	public int peek() {
		return tail.next.getVal();
	}
	
	// remove data from head of the list
	public int removeFromHead() {
        ListNode tmp = tail.next;
		if(tail == tail.next)
			tail = null;
		else {
			tail.next=tmp.next;
			tmp.next=null;
		}
		length --;
		return tmp.getVal();
	}
	
	//remove data from tail of the list
	public int removeFromTail() {
		if(tail == null)
			return Integer.MIN_VALUE;
        ListNode p = tail;
		while(p.next != tail) {
			p = p.next;
		}
		// p now points to second to last data
        ListNode tmp = tail;
		if(p == tail)
			tail = null;
		else {
			p.next=tail.next;
			tail = p;
		}
		length--;
		return tmp.getVal();
	}
	
	//Return true if the list contains data else false
	public boolean contains(int data) {
		if(tail == null)
			return false;
        ListNode p= tail.next;
		while(p != tail && p.getVal() != data)
			p = p.next;
		return (p.getVal() == data);
	}
	
	// Remove and return element == data
	public int remove(int data) {
		if(tail == null) return Integer.MIN_VALUE;
        ListNode n = tail.next;
        ListNode p = tail;
		
		for(int i = 0; i<length && (!(n.getVal() == data)); i++) {
			p = n;
			n = n.next;
		}
		
		if(p.getVal()== data) {
			if(tail == tail.next) {
				tail = null;
				
			} else {
				if(n == tail)
					tail = p;
				p.next=p.next.next;
			}
			//n data release
			n.next=null;
			length--;
			return n.getVal();
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
        ListNode curr = tail.next;
		while(curr != tail) {
			result = result+" "+curr.getVal();
			curr= curr.next;
		}
		return result+"]";
	}


	/**
	 * Split a circular linked list into 2 from mid.
	 * Store the mid and last of circular linked list.
	 * make 2nd half circular and 1st half circular, then set the head.
	 */
	public static void splitList(ListNode head, ListNode head1, ListNode head2){
        ListNode slowPtr= head, fastPtr = head;
		if(head == null)
			return;
		while((head != fastPtr.next) && (head != fastPtr.next.next)){
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
		}

		// If there are even elements in the list then move fast ptr
		if(fastPtr.next.next == head) {
			fastPtr = fastPtr.next;
		}
		//set the headptr of 1st half
		head1 = head;

		//set the headptr of 2nd half
		if(head.next != head)
		head2 = slowPtr.next;

		//Make the 2nd half circular
		fastPtr.next=slowPtr.next;

		//Make the 1st half circular
		slowPtr.next=head;
	}

	/**
	 * Get Josehus Position.
	 * N people decided to elect a leader by arranging themselves in a circle
	 * and eliminating every Mth position around the circle.
	 *
	 * Assume the input is a circular linked list with N nodes and ach node has
	 * a number (range 1 to N) associated with it. The head node has number 1 as data.
	 */
	public ListNode getJosephusPosition(int N, int M) {
        ListNode p, q;
		//Create Circular linked list containing all the players
		p = new ListNode(1);
		q = new ListNode(1);

		for(int i=2; i<= N; i++) {
            ListNode x = new ListNode(i);
			p.next=x;
			p = p.next;
		}
		//Close the circular linked list
		p.next=q;

		//eliminate mth player as long as more than 1 player remains
		for(int count =N ; count >1 ; --count){
			for(int i=0; i<M-1; ++i)
				p = p.next;
			p.next=p.next.next;
		}
		System.out.println("Lat player lefty standing, Josephus position "+p.getVal());
		return p;
	}
}
