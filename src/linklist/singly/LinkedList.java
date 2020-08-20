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
		node.next =head;
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
			for(p =head; (q=p.next)!= null ; p=q);
			p.next =node;
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
			tmp.next=head;
			head = tmp;
		} else {
			ListNode tmp = head;
			for(int i=0; i<position; i++) {
				tmp = tmp.next;
			}
			ListNode newNode = new ListNode(data);
			newNode.next=tmp.next;
			tmp.next=newNode;
		}
		length +=1;
		
	}
	
	public ListNode removeFromBegin() {
		ListNode node = head;
		if(node != null) {
			head = node.next;
			node.next=null;
		}
		return node;
	}
	
	public ListNode removeFromEnd() {
		if(head ==null)
			return null;
		ListNode p = head, q= null, next = head.next;
		if(next == null) {
			head = null;
			return p;
		}
		while((next = p.next)!= null) {
			q = p;
			p = next;
		}
		q.next=null;
		return p;
	}
	
	public void removeMatched(ListNode node) {
		if(head == null)
			return;
		if(node.equals(head)) {
			head = head.next;
			return;
		}
		ListNode p = head, q= null;
		while((q = p.next)!= null) {
			if(node.equals(q)) {
				p.next=q.next;
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
			head = head.next;
		else {
			ListNode tmp = head;
			for(int i=1; i<position; i++)
				tmp = tmp.next;
			tmp.next=tmp.next.next;
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
			result = result+tmp.getVal();
			tmp = tmp.next;
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
			if(data == curr.getVal()) {
				return pos;
			}
			curr = curr.next;
			pos++;
		}
		
		return Integer.MIN_VALUE;
	}
	
	public void clearList() {
		head = null;
		length =0;
	}

	/**
	 *
	 *61. Rotate List
	 *
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		int size = 1;
		ListNode p = head;
		while(p.next != null) {
			size ++;
			p = p.next;
		}
		p.next = head;
		p = head;
		k = size - k % size - 1;
		while ( k -- > 0) {
			p = p.next;
		}
		head = p.next;
		p.next = null;
		return head;

	}

    /**
     * https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3430/
     * @param head
     */
	public static void reorderList(ListNode head) {

		if (head != null && head.next != null) {

			ListNode slow = head;
			ListNode fast = head;

			//use a fast and slow pointer to break the link to two parts.
			while (fast != null && fast.next != null && fast.next.next!= null) {
				//why need third/second condition?
				System.out.println("pre "+slow.val + " " + fast.val);
				slow = slow.next;
				fast = fast.next.next;
				System.out.println("after " + slow.val + " " + fast.val);
			}

			ListNode second = slow.next;
			slow.next = null;// need to close first part

			// now should have two lists: head and fast

			// reverse order for second part
			second = reverseOrder(second);

			ListNode p1 = head;
			ListNode p2 = second;

			//merge two lists here
			while (p2 != null) {
				ListNode temp1 = p1.next;
				ListNode temp2 = p2.next;

				p1.next = p2;
				p2.next = temp1;

				p1 = temp1;
				p2 = temp2;
			}
		}
	}



	public static ListNode reverseOrder(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = head;
		ListNode curr = head.next;

		while (curr != null) {
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}

		// set head node's next
		head.next = null;

		return pre;
	}

}
