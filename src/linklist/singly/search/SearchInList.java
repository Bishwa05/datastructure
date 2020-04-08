package linklist.singly.search;

import linklist.singly.ListNode;

public class SearchInList {

	/*
	 * Find nth node from end of the list
	 * which can be done by brute-force method time complexity of O(n^2)
	 * which can be done by using hashmap<position, data> which will have space comp
	 */
	public ListNode nthNodeFromEndItr(ListNode head, int n) {
		ListNode curr=head,nthNode = null;
		
		for(int i =0; i<n; i++) {
			if(curr != null)
				curr = curr.getNext();
		}
		
		while(curr != null) {
			if(nthNode == null)
				nthNode = head;
			else
				nthNode = nthNode.getNext();
			curr = curr.getNext();
		}
		
		if(nthNode != null)
			return nthNode;
		return null;
	}
	
	public ListNode nthNodeFromEndRec(ListNode head, int n, int count) {
		if(head != null)
			nthNodeFromEndRec(head.getNext(), n, count);
		count++;
		if(n == count)
			return head;
		return null;
	}
	
	/*
	 * Find whether link list has cycle
	 * It can be solved via brute force if size is known
	 * It can be solved by hashmap<address of node, visited> space O(n)
	 * Time : O(n), space : O(1)
	 */
	public static boolean findIfLoopExistsUsingFloyd(ListNode head) {
		ListNode fastPtr = head;
		ListNode slowPtr = head;
		while(fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
			if(slowPtr==fastPtr)
				return true;
		}
		return false;
	}
	
	/*
	 * check whetehr the given linked list is either NULL terminated or not
	 * If there is a cycle then find the start node of the loop.
	 */
	public static ListNode findBeginOfLoop(ListNode head) {
		ListNode fastPtr = head;
		ListNode slowPtr = head;
		boolean loopExists = false;
		while(fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
			if(slowPtr==fastPtr) {
				loopExists= true;
				break;
			}
		}
		
		if(loopExists) {
			slowPtr = head;
			while(slowPtr != fastPtr) {
				slowPtr = slowPtr.getNext();
				fastPtr = fastPtr.getNext();
			}
			return fastPtr;
		} 
		else
			return null;
	}
	
	/*
	 * Check whether the given linked list is null terminated.
	 * If there is a cycle find the length of the loop.
	 * Time : O(n) , Space : O(1)
	 */
	public static int findLengthOfTheLoop(ListNode head) {
		ListNode fastPtr = head;
		ListNode slowPtr = head;
		boolean ifLoopExists = false;
		
		while(fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
			if(slowPtr == fastPtr) {
				ifLoopExists = true;
				break;
			}
		}
		int length =0;
		if(ifLoopExists) {
			while(slowPtr != fastPtr) {
				slowPtr = slowPtr.getNext();
				length++;
			}
		}
		return length;
	}
	
	
	
	/*
	 * Two List merged to 1 at a point. Find merging point.
	 * Size of 1st list is m, 2nd is n, where m=n, m<n, m>n
	 * In brute force approach the time comp would be O(mn)
	 * and the space comp would be O(1)
	 * By using hashtable,it can be solved, push 1 list in hashtable
	 * iterate on another and if address math then it is the join point
	 * space: O(m), time (m)
	 * It can be solved by using 2 stacks, insert lists in the stacks then pop
	 * from each of the list and check whether same or not.
	 * Time: (m+n) , space: (m+n)
	 * By Using arrays also will get time and space O(m+n)
	 * By combining searching and sorting , put all next pointers of the first list in the array.
	 * Then for each of the 2nd list element search in the sorted array(binary search O(logn))
	 * Time for sorting+ searching = O(Max(mlogm, nlogn)), space O(Max(m,n))
	 */
	public ListNode findIntersectNode(ListNode node1, ListNode node2) {
		int l1 =0, l2=0, diff =0;
		ListNode head1 = node1, head2 = node2;
		while(head1 != null) {
			l1++;
			head1 = head1.getNext();
		}
		
		while(head2 != null) {
			l2++;
			head2 = head2.getNext();
		}
		
		if(l1< l2) {
			head1 = node2;
			head2 = node1;
			diff = l2-l1;
		}else {
			head1 = node1;
			head2 = node2;
		}
		
		for(int i=0; i< diff; i++)
			head1 = head1.getNext();
		while(head1 != null && head2 != null) {
			if(head1 == head2)
				return head1;
			head1 = head1.getNext();
			head2 = head2.getNext();
		}
		return null;
	}
 	
	/*
	 * Find the mid of a linked list
	 * For each node count how many nodes in list and find mid,
	 * it takes time O(n*2) and space O(1).
	 * In 2 iteration we can find, 1st iteration find the length,
	 * and in the 2nd iteration go to n/2th position,
	 * here time O(n) and space O(1).
	 * In Hashtable approach time O(n) space O(n).
	 * This can be solved in 1 scan.
	 */
	public static ListNode findMiddle(ListNode head){
		ListNode ptr1x, ptr2x;
		ptr1x = ptr2x = head;
		int i=0;
		int j =0;

		while(ptr1x.getNext() != null){
			if(i==0) {
				ptr1x = ptr1x.getNext();
				i=1;
			} else if(i==1){
				ptr1x = ptr1x.getNext();
				ptr2x = ptr2x.getNext();
				i=0;
			}
			j++;
		}
		return (j%2==0)?ptr2x:ptr2x.getNext();
	}

	/**
	 * more simple approach without using any extra variables like above.
	 * Accepted in leetcode submissions.
	 * Time complexity remains O(n)
	 * @param head
	 * @return
	 */
	public static ListNode findMiddle2(ListNode head){
		ListNode slow = head, fast = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;
	}

	/**
	 * check the linked list is pallindrome or not
	 * 1. get the middle of the linked list
	 * 2. reverse the 2nd half
	 * 3. compare the 1st and 2nd half
	 * TODO:
	 */

	/**
	 *
	 * Find the last element from linked list whose n%k ==0,
	 * where n is the number of elements in the list, k is constant provided
	 * if n = 16 and k =5, it should return 15th node
	 * Time O(n) Space O(1)
	 */
	public ListNode findModularNode(ListNode head, int k) {
		ListNode mod = head;
		int i=0;
		if(k<=0)
			return null;
		while(head != null){
			if(i%k ==0){
				mod = head;
			}
			i++;
			head = head.getNext();
		}
		return mod;
	}

	/**
	 * Modular node from end
	 * Find the 1st element from end  whose n%k ==0
	 * where n is no of element and k is an integer provided
	 * if n = 19, k = 3 , then result = 16
	 *
	 * This can be achieved by 2 pointers.
	 *
	 * Space O(1) and time O(n).
	 *
	 */


	/**
	 * Find fractional node
	 * Find n/k th element from a linked list. where n is no of elements
	 * if n =15 and k = 4 , result = 12th element
	 * n is not known in advance
	 */
	public ListNode getFractionNode(ListNode head, int k) {
		ListNode fracNode = head;
		int i=0;
		if (k <= 0)
			return null;
		while(head != null){
			if(i%k ==0)
				fracNode = head;
			i++;
			head = head.getNext();
		}
		return fracNode;
	}



}
