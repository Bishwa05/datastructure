package linklist.singly.traversal;

import linklist.singly.ListNode;

import java.util.Stack;

public class Traversal {
	
	public int listLength(ListNode head) {
		int length =0;
		if(head == null)
			return length;
		
		ListNode curr = head;
		while(curr != null) {
			length++;
			curr= curr.next;
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
		while(curr != null && curr.getVal()< newNode.getVal())
			curr = curr.next;
		if(curr != null) {
			ListNode tmp = curr.next;
			newNode.next=tmp;
			curr.next=newNode;
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
			ListNode next = curr.next;
			curr.next=prev;
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
	ListNode next = curr.next;
	if(next == null) {
		head[0] = curr;
		return;
	}
	reverseListRec(next, head);
	next.next=curr;
	curr.next=null;
	}

	/*
	 * Print the linked list from end
	 * If we use stack the time O(n) and space O(n).
	 * With recursion time O(n) and space O(1)
	 */
	public static void printListFromEnd(ListNode head){
		if(head == null)
			return;

		printListFromEnd(head.next);
		System.out.println(head.getVal());
	}


	/**
	 * Find linked list length is even or odd.
	 * If the 2x pointer points to null then even or else odd.
	 * Time O(n) space O(1)
	 */
	public int isLinkedListLengthEven(ListNode head) {
		while(head != null && head.next != null)
			head = head.next.next;
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
		if(head1.getVal() <= head2.getVal()) {
			head = head1;
			head.next=mergeTwoListsRec(head1.next, head2);
		} else {
			head = head2;
			head.next=mergeTwoListsRec(head1, head2.next);
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
			if(head1.getVal() < head2.getVal()){
				curr.next=head1;
				head1 = head1.next;
			} else {
				curr.next=head2;
				head2= head2.next;
			}
		}

		if(head1 != null){
			curr.next=head1;
		} else if (head2 != null) {
			curr.next=head2;
		}
		return head.next;

	}

	/**
	 * Reverse the linked list in pairs
	 * time O(n) space O(1)
	 */
	public static ListNode reversePairRec(ListNode head){
		ListNode temp;

		if(head == null || head.next == null)
			return head;
		else {
			//Reverse 1st pair
			temp = head.next;
			head.next=temp.next;
			temp.next=head;
			head = temp;

			//Recursively compute for the rest of the list.
			head.next.next=reversePairRec(head.next.next);
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

		while (head != null && head.next != null) {
			if(temp1 != null) {
				temp1.next.next=head.next;
			}

			temp1 = head.next;
			head.next=head.next.next;
			temp1.next=head;

			if(temp2 == null)
				temp2 = temp1;
			head = head.next;
		}
		return temp2;
	}

	/**
	 * Exchange the adjacent element in linked list
	 */
	public ListNode exchangeAdjacentNodes (ListNode head){
		ListNode temp = new ListNode(0);
		temp.next=head;
		ListNode prev = temp, curr = head;
		while(curr != null && curr.next != null){
			ListNode tmp = curr.next.next;
			curr.next.next=prev.next;
			prev.next=curr.next;
			curr.next=tmp;
			prev = curr;
			curr= prev.next;
		}
		return temp.next;
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
	 *
	 *
	 * 		Input [1,2,3,4,5]
	 * 			k : 3
	 * 	Output should be 3,2,1,4,5	(Added as per leetcode)
	 */
	public static ListNode reverseKNodesRec(ListNode head, int k) {
		ListNode current = head, next = null, prev = null;
		int count = k;

		while(current!= null){
			current = current.next;
			count--;
			if(count ==0){
				break;
			}
		}

		if(count ==0){
			current = head;
			count= k;
		} else{
			return head;
		}

		//Reverse k nodes
		while(current != null && count >0){
			next = current.next;
			current.next=prev;
			prev = current;
			current = next;
			count--;
		}

		//Now next points to K+1th node, returns the pointer to the head node
		if(next != null){
			head.next=reverseKNodesRec(next,k);
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
				ListNode next = current.next;
				current.next=tail;
				tail = current;
				current = next;
				count--;
			}
			// reversed K nodes
			if (prevTail != null) {
				//Link this set and previous set
				prevTail.next=tail;
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


	/**
	 * Reorder a list like below
	 * l1-> l2->l3-> ......->ln-2, ln-1, ln
	 * l1->ln->l2->ln-1->l3->ln-2.......
	 * 	1. Divide the list into 2.
	 * 	2. reverse the 2nd half.
	 * 	3. Combine the two list
	 */
	public void reOrderList(ListNode head){
		if(head == null)
			return;
		ListNode sPointer = head;
		ListNode fPointer = head.next;

		while(fPointer != null && fPointer.next != null){
			fPointer = fPointer.next.next;
			sPointer = sPointer.next;
		}
		ListNode head2 = sPointer;
		sPointer.next=null;
		Stack<ListNode> stack= new Stack<>();

		while(head2 != null){
			ListNode temp= head2;
			head2 = head2.next;
			temp.next=null;
			stack.push(temp);
		}

		while(!stack.isEmpty()) {
			ListNode temp = stack.pop();
			temp.next=head.next;
			head.next=temp;
			head = temp.next;
		}
	}

}
