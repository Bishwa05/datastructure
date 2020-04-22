package tree.trie;

/**
 * Hello, Hell, held, hold, xxy
 *                      0
 *
 *          H                   X
 *          E   O               X
 *     D    L   L               Y
 *          L   D
 *          O
 */
public class TrieNode {
     TrieNode root;

    static final int ALPHABET_SIZE = 26;
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    boolean isLeaf;

    public TrieNode() {
        isLeaf = false;
        for(int i=0; i<ALPHABET_SIZE; i++) {
            children[i] = null;
        }
    }

    public void insert(String word) {
        if(root == null){
            root = new TrieNode();
        }
        TrieNode curr = root;

        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(curr.children[index]==null){
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isLeaf = true;

    }

    public boolean startsWith(String prefix) {

        TrieNode curr = root;

        if(curr == null){
            return false;
        }
        for(int i=0; i<prefix.length(); i++){
            int index = prefix.charAt(i)-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }

    public boolean search(String word) {

        TrieNode curr = root;

        if(curr == null){
            return false;
        }
        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr = curr.children[index];
        }

        return curr.isLeaf?true:false;
    }


}
