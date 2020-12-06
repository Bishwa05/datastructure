package tree.leetcode;

public class PopulatingNextPointerInEachNodeII
{
    public void connect(Node root){
        if(root == null) return;


        Node lastHead = root; // previous levels head
        Node lastCurrent = null; // previous level pointer
        Node currentHead = null; // current level head
        Node current = null; // current level pointer

        while(lastHead != null){
            lastCurrent = lastHead;

            while(lastCurrent != null){
                //left child is not null
                if(lastCurrent.left != null){
                    if(currentHead == null){
                        currentHead = lastCurrent.left;
                        current = lastCurrent.left;
                    } else{
                        current.next = lastCurrent.left;
                        current = current.next;
                    }
                }

                //right child is not null
                if(lastCurrent.right != null){
                    if(currentHead == null){
                        currentHead = lastCurrent.right;
                        current = lastCurrent.right;
                    } else{
                        current.next = lastCurrent.right;
                        current = current.next;
                    }
                }

                lastCurrent = lastCurrent.next;
            }
            //update last head
            lastHead = currentHead;
            currentHead = null;
        }
    }

}
