package tree.trie;

public class PrefixTree {
    TrieNode root;
    public class TrieNode {
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode() {
            isLeaf = false;
            children = new TrieNode[26];
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

    public static void main(String arg[]){

        PrefixTree p = new PrefixTree();
        TrieNode root = p.new TrieNode();
        p.root= root;

        p.insert("apple");
        System.out.println(p.search("apple"));
        System.out.println(p.search("app"));
        System.out.println(p.startsWith("app"));
        p.insert("app");
        System.out.println(p.search("app"));

        System.out.println(p.startsWith("a"));
    }
}
