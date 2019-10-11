package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Operations {

    /**
     * Tower of Hanoi is a mathematical puzzle where we have three rods and
     * n disks. The objective of the puzzle is to move the entire stack to
     * another rod, obeying the following simple rules:
     *
     * 1) Only one disk can be moved at a time.
     * 2) Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack i.e. a disk can only be moved if it is the uppermost disk on a stack.
     * 3) No disk may be placed on top of a smaller disk.
     *
     * For n disks, total 2^n – 1 moves are required.
     */
    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod){


        if (n == 1)
        {
            System.out.println("Move disk 1 from rod " +  fromRod + " to rod " + toRod);
            return;
        }
        towerOfHanoi(n-1, fromRod, auxRod, toRod);
        System.out.println("Move disk " + n + " from rod " +  fromRod + " to rod " + toRod);
        towerOfHanoi(n-1, auxRod, toRod, fromRod);

    }

    public static void main(String args[])
    {
        int n = 4; // Number of disks
        towerOfHanoi(n, 'A', 'C', 'B');  // A, B and C are names of rods
    }

    /**
     * Check each successive pair of stack is consecutive or not.
     * [4,5,-2,-3,11,12,9,8,20]
     */
    public static boolean checkStackPairWiseOrder(Stack<Integer> s) {
        Queue<Integer> q = new LinkedList<>();
        boolean pairwiseOrdered = false;
        while(!s.isEmpty()) {
            int n = s.pop();
            q.add(n);

            if(!s.isEmpty()){
                int m = s.pop();
                q.add(m);
                if(Math.abs(n-m)!=1)
                    pairwiseOrdered=false;
            }
        }

        while(!q.isEmpty()){
            s.push(q.remove());
        }
        return pairwiseOrdered;
    }
}
