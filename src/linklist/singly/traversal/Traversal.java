package linklist.singly.traversal;

import linklist.singly.ListNode;

public class Traversal {
	
	public int listLength(ListNode head) {
		int length =0;
		if(head == null)
			return length;
		
		ListNode curr = head;
		while(curr != null) {
			length++;
			curr= curr.getNext();
		}
		
		return length;
		
	}
	
	/*
	 * Insert a node in a sorted linked list
	 * Time : O(n), space : O(1)
	 */
	public ListNode insertInSortedList(ListNode head, ListNode newNode) {
		ListNode curr = head;
		if(head == null) return newNode;
		//traverse until encounter bigger node
		while(curr != null && curr.getData()< newNode.getData())
			curr = curr.getNext();
		if(curr != null) {
			ListNode tmp = curr.getNext();
			newNode.setNext(tmp);
			curr.setNext(newNode);
		}
		return head;
	}
	
	/*
	 * Reverse a singly linked list
	 * Time: O(n), space : O(1)
	 */
	public ListNode reverseListItr(ListNode head) {
		ListNode curr = head;
		ListNode prev = null;
		while(curr != null) {
			ListNode next = curr.getNext();
			curr.setNext(prev);
			prev=curr;
			curr=next;
		}
		return prev;
	}
	
	/*
	 * Space : O(n), time: O(n)
	 */
	public void reverseListRec(ListNode curr, ListNode[] head) {
	if(curr == null)
		return;
	ListNode next = curr.getNext();
	if(next == null) {
		head[0] = curr;
		return;
	}
	reverseListRec(next, head);
	next.setNext(curr);
	curr.setNext(null);
	}

	/*
	 * Print the linked list from end
	 * If we use stack the time O(n) and space O(n).
	 * With recursion time O(n) and space O(1)
	 */
	public static void printListFromEnd(ListNode head){
		if(head == null)
			return;

		printListFromEnd(head.getNext());
		System.out.println(head.getData());
	}


	/**
	 * Find linked list length is even or odd.
	 * If the 2x pointer points to null then even or else odd.
	 * Time O(n) space O(1)
	 */
	public int isLinkedListLengthEven(ListNode head) {
		while(head != null && head.getNext() != null)
			head = head.getNext().getNext();
		if(head == null)
			return 0;
		return 1;
	}

	/**
	 * Given 2 sorted Linked list, merge them to 1 sorted linked list
	 * Time O(n), space O(1)
	 */
	public ListNode mergeTwoListsRec(ListNode head1, ListNode head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		ListNode head = new ListNode(0);
		if(head1.getData() <= head2.getData()) {
			head = head1;
			head.setNext(mergeTwoListsRec(head1.getNext(), head2));
		} else {
			head = head2;
			head.setNext(mergeTwoListsRec(head1, head2.getNext()));
		}
		return head;
	}

	/**
	 * Given 2 sorted Linked list, merge them to 1 sorted linked list
	 * Time O(n), space O(1), in iterative
	 */
	public ListNode mergeTwoListItr(ListNode head1, ListNode head2) {

		ListNode head = new ListNode(0);
		ListNode curr = head;

		while(head1 != null && head2 != null) {
			if(head1.getData() < head2.getData()){
				curr.setNext(head1);
				head1 = head1.getNext();
			} else {
				curr.setNext(head2);
				head2= head2.getNext();
			}
		}

		if(head1 != null){
			curr.setNext(head1);
		} else if (head2 != null) {
			curr.setNext(head2);
		}
		return head.getNext();

	}

	/**
	 * Reverse the linked list in pairs
	 * time O(n) space O(1)
	 */
	public static ListNode reversePairRec(ListNode head){
		ListNode temp;

		if(head == null || head.getNext() == null)
			return head;
		else {
			//Reverse 1st pair
			temp = head.getNext();
			head.setNext(temp.getNext());
			temp.setNext(head);
			head = temp;

			//Recursively compute for the rest of the list.
			head.getNext().setNext(reversePairRec(head.getNext().getNext()));
		}

		return head;
	}

	/**
	 * Reverse the linked list in pairs Iterative
	 * time O(n) space O(1)
	 */
	public static ListNode reversePairItr(ListNode head) {
		ListNode temp1 = null;
		ListNode temp2 = null;

		while (head != null && head.getNext() != null) {
			if(temp1 != null) {
				temp1.getNext().setNext(head.getNext());
			}

			temp1 = head.getNext();
			head.setNext(head.getNext().getNext());
			temp1.setNext(head);

			if(temp2 == null)
				temp2 = temp1;
			head = head.getNext();
		}
		return temp2;
	}

	/**
	 * Exchange the adjacent element in linked list
	 */
	public ListNode exchangeAdjacentNodes (ListNode head){
		ListNode temp = new ListNode(0);
		temp.setNext(head);
		ListNode prev = temp, curr = head;
		while(curr != null && curr.getNext() != null){
			ListNode tmp = curr.getNext().getNext();
			curr.getNext().setNext(prev.getNext());
			prev.setNext(curr.getNext());
			curr.setNext(tmp);
			prev = curr;
			curr= prev.getNext();
		}
		return temp.getNext();
	}

	/**
	 * For a given K value(K>0) reverse blocks of K nodes in a list.
	 * 1. Check if remaining list has K nodes
	 * 		a. if yes get the pointer of k+1th node.
	 * 		b. else return
	 * 2. Reverse 1st K nodes
	 * 3. Set next of last node(after reversal) to K+1th node.
	 * 4. Move to k+1th node
	 * 5.Go to step1.
	 * 6.K-1th node of first K nodes become the new head if available.
	 * Otherwise we can return head
	 */
	public static ListNode reverseKNodesRec(ListNode head, int k) {
		ListNode current = head, next = null, prev = null;
		int count = k;
		//Reverse k nodes
		while(current != null && count >0){
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
			count--;
		}

		//Now next points to K+1th node, returns the pointer to the head node
		if(next != null){
			head.setNext(reverseKNodesRec(next,k));
		}
		//return head node
		return prev;
	}


	public static ListNode reverseKNodesItr(ListNode head, int k) {
		//Start with head
		ListNode current = head;
		//LastNode after reverse
		ListNode prevTail = null;
		//FirstNode after reverse
		ListNode prevCurrent = head;

		while(current != null) {
			//loop for reversing K nodes
			int count = k;
			ListNode tail = null;
			while (current != null && count > 0) {
				ListNode next = current.getNext();
				current.setNext(tail);
				tail = current;
				current = next;
				count--;
			}
			// reversed K nodes
			if (prevTail != null) {
				//Link this set and previous set
				prevTail.setNext(tail);
			} else {
				//we just reversed 1st set of k nodes, update the head point to kth node
				head = tail;
			}
			//save the last node after reverse since we need to connect to the next set
			prevTail = prevCurrent;
			//Save the current node which will become the last node after reverse
			prevCurrent = current;

		}
		return head;
	}

	/**
	 * Is it possible to get O(1) access time for Linked list
	 * Create a linked list and keep the elements in hash table
	 */



}
