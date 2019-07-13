package linklist.doubly;

public class DoublyLinkedList {
	
	private DLLNode head;
	private DLLNode tail;
	private int length;
	
	// Create a new empty list
	public DoublyLinkedList() {
		head = new DLLNode(Integer.MIN_VALUE, null, null);
		tail = new DLLNode(Integer.MIN_VALUE, null, null);
		head.setNext(tail);
		length =0;
	}
	
	public int getPosition(int data) {
		DLLNode tmp = head;
		int pos =0;
		while(tmp != null) {
			if(tmp.getData() == data) {
				return pos;
			}
			pos +=1;
			tmp = tmp.getNext();
		}
		return Integer.MIN_VALUE;
	}
	
	public int length() {
		return length;
	}
	
	// Add a new value to the front of the list
	public void insert(int newValue) {
		DLLNode newNode = new DLLNode(newValue, head,head.getNext());
		newNode.getNext().setPrev(newNode);
		head.setNext(newNode);
		length++;
	}
	
	public void insert(int data, int position) {
		if(position< 0)
			position =0;
		if(position>length)
			position = length;
		
		if(head == null) {
			head = new DLLNode(data);
			tail = head;
		}
		else if(position ==0) {
			DLLNode tmp = new DLLNode(data);
			tmp.setNext(head);
			head = tmp;
		}
		else {
			DLLNode tmp = head;
			for(int i=1; i<position;i++)
				tmp = tmp.getNext();
			DLLNode newNode = new DLLNode(data);
			newNode.setNext(tmp.getNext());
			newNode.setPrev(tmp);
			newNode.getNext().setPrev(newNode);
			tmp.setNext(newNode);
		}
		length++;
	}
	
	//Add a new value to the rear of the list
	public void insertAtEnd(int newValue) {
		DLLNode newNode = new DLLNode(newValue, tail.getPrev(), tail);
		newNode.getPrev().setNext(newNode);
		tail.setPrev(newNode);
		length++;
	}
	
	public void remove(int position) {
		if(position<0)
			position =0;
		if(position>=length)
			position = length-1;
		if(head == null)
			return;
		// if remove the head element
		if(position ==0) {
			head = head.getNext();
			if(head == null)
				tail = null;
		}
		// else advance to the correct position
		else {
			DLLNode tmp = head;
			for(int i=1; i<position; i++)
				tmp = tmp.getNext();
			tmp.getNext().setPrev(tmp.getPrev());
			tmp.getPrev().setNext(tmp.getNext());
		}
		length--;
	}
	
	public void removeMatched(DLLNode node) {
		if(head==null) return;
		if(node.equals(head)) {
			head = head.getNext();
			if(head == null)
				tail = null;
			return;
		}
		DLLNode p = head;
		while(p!= null) {
			if(node.equals(p)) {
				p.getPrev().setNext(p.getNext());
				p.getNext().setPrev(p.getPrev());
				return;
			}
		}
	}
	
	// Remove head from the list
	public int removeHead() {
		if(length ==0)
			return Integer.MIN_VALUE;
		DLLNode curr = head.getNext();
		head.setNext(curr.getNext());
		curr.getNext().setPrev(head);
		length -= 1;
		return curr.getData();
	}
	
	// Remove tail from the list
	public int removetail() {
		if(length ==0)
			return Integer.MIN_VALUE;
		DLLNode curr = tail.getPrev();
		tail.setPrev(curr.getPrev());
		curr.getPrev().setNext(tail);
		length -=1;
		return curr.getData();
	}
	
	public String toString() {
		String result ="[";
		if(length ==0)
			return result+"]";
		DLLNode tmp = head;
		while(tmp != tail) {
			result = result+ tmp.getData()+" ";
			tmp = tmp.getNext();
		}
		return result+"]";
	}
	
	//Remove everything from DLL
	public void clearList() {
		head = null;
		tail = null;
		length =0;
	}
	

}
