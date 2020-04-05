package tree.trie;

/**
 * Hello, Hell, held, hold
 *
 *          H
 *          E   O
 *     D    L   L
 *          L   D
 *          O
 */
public class TrieNode {
    static TrieNode root;
    static int reach;

    static final int ALPHABET_SIZE = 26;
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    boolean isLeaf;

    public TrieNode() {
        isLeaf = false;
        for(int i=0; i<ALPHABET_SIZE; i++) {
            children[i] = null;
        }
    }

    public static void insert(String key){
        int length = key.length();
        int index;

        if(root == null) {
            root = new TrieNode();
        }

        TrieNode next = root;

        for(int level =0; level< length; level++) {
            index = key.charAt(level) -'a';
            if(next.children[index] == null) {
                next.children[index] = new TrieNode();
            }
        }

        //mark the leaf node.
        next.isLeaf = true;
    }

    public static String traverse() {
        TrieNode next = root;
        String prefix ="";

        while(countChildren(next) ==1 && !next.isLeaf){
            next = next.children[reach];
            prefix = prefix+(char)('a'+reach);
        }
        return prefix;
    }

    public static int countChildren(TrieNode node) {
        int count =0;
        for(int i=0; i<ALPHABET_SIZE; i++){
            if(node.children[i] != null){
                reach =i;
                count++;
            }
        }
        return count;
    }

}
