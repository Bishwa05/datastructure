package linklist.singly;

public class LinkedList {
	
	private int length =0;
	ListNode head;
	
	public LinkedList() {
		length =0;
	}
	
	/*
	 * Insert at beginning
	 */
	public void insertAtBegining(ListNode node) {
		node.setNext(head);
		head = node;
		length++;
	}
	
	/*
	 * Insert at end
	 */
	public void insertAtEnd(ListNode node) {
		if(head == null)
			head = node;
		else {
			ListNode p,q;
			for(p =head; (q=p.getNext())!= null ; p=q);
			p.setNext(node);
		}
		length++;
	}
	
	/*
	 * Insert at a position
	 */
	public void insert(int data, int position) {
		if(position < 0)
			position = 0;
		if(position > length)
			position = length;
		if(head == null)
			head = new ListNode(data);
		
		else if(position ==0) {
			ListNode tmp = new ListNode(data);
			tmp.setNext(head);
			head = tmp;
		} else {
			ListNode tmp = head;
			for(int i=0; i<position; i++) {
				tmp = tmp.getNext();
			}
			ListNode newNode = new ListNode(data);
			newNode.setNext(tmp.getNext());
			tmp.setNext(newNode);
		}
		length +=1;
		
	}
	
	public ListNode removeFromBegin() {
		ListNode node = head;
		if(node != null) {
			head = node.getNext();
			node.setNext(null);
		}
		return node;
	}
	
	public ListNode removeFromEnd() {
		if(head ==null)
			return null;
		ListNode p = head, q= null, next = head.getNext();
		if(next == null) {
			head = null;
			return p;
		}
		while((next = p.getNext())!= null) {
			q = p;
			p = next;
		}
		q.setNext(null);
		return p;
	}
	
	public void removeMatched(ListNode node) {
		if(head == null)
			return;
		if(node.equals(head)) {
			head = head.getNext();
			return;
		}
		ListNode p = head, q= null;
		while((q = p.getNext())!= null) {
			if(node.equals(q)) {
				p.setNext(q.getNext());
				return;
			}
			p=q;
		}
	}
	
	public void remove(int position) {
		if(position<0)
			position =0;
		if(position> length)
			position =length-1;
		if(head == null)
			return;
		if(position ==0)
			head = head.getNext();
		else {
			ListNode tmp = head;
			for(int i=1; i<position; i++)
				tmp = tmp.getNext();
			tmp.setNext(tmp.getNext().getNext());
		}
		length--;
	}
	

	// Return a string representation of this collection in the form["str1","str2"..]
	public String toString() {
		String result ="[";
		if(head == null)
			return result+"]";
		ListNode tmp = head;
		while(tmp != null) {
			result = result+tmp.getData();
			tmp = tmp.getNext();
		}
		
		return result+"]";
	}
	
	//Return the current length of the list
	public int length() {
		return length;
	}
	
	//Find the position of the first value that is equals to given value
	public int getPosition(int data) {
		ListNode curr = head;
		int pos =0;
		
		while(curr != null) {
			if(data == curr.getData()) {
				return pos;
			}
			curr = curr.getNext();
			pos++;
		}
		
		return Integer.MIN_VALUE;
	}
	
	public void clearList() {
		head = null;
		length =0;
	}
}
