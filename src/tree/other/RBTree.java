package tree.other;

/**
 * 1. Every node is either red or black.
 * 2. The root is always black.
 * 3. If a node is red, its children must be black (although the converse isn’t
 * necessarily true).
 * 4. Every path from the root to a leaf, or to a null child, must contain the
 * same number of black nodes.
 *
 *
 *
 * Property #1: Red - Black Tree must be a Binary Search Tree.
 * Property #2: The ROOT node must be colored BLACK.
 * Property #3: The children of Red colored node must be colored BLACK. (There should not be two consecutive RED nodes).
 * Property #4: In all the paths of the tree, there should be same number of BLACK colored nodes.
 * Property #5: Every new node must be inserted with RED color.
 * Property #6: Every leaf (e.i. NULL node) must be colored BLACK.
 *
 *
 */
public class RBTree <Key extends Comparable<Key>, Value>{
    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }


    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }


    public boolean contains(Key key) {
        return get(key) != null;
    }

}
