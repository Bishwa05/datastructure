package tree.trie;

public class Operations {

    public static void main(String arg[]){

        TrieNode trie = new TrieNode();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));

//        TrieNode.insert("app");
        System.out.println(trie.startsWith("a"));
    }
}
