package tree.trie;

/**
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 */
public class WordDictionary {
    TrieNode root;
    class TrieNode{
        boolean isLeaf;
        TrieNode[] children;

        TrieNode(){
            children = new TrieNode[26];
            isLeaf= false;
        }
    }

    /**
     * The commented code for leetcode
     *
     *     public WordDictionary() {
     *         root = new TrieNode();
     *     }
     *
     */

//    public void addWord(String word){
//
//        if(root == null){
//            root = new TrieNode();
//        }
//
//        TrieNode curr = root;
//        for(char c : word.toCharArray()){
//            if(curr.children[c-'a']== null){
//                curr.children[c-'a'] = new TrieNode();
//            }
//            curr = curr.children[c-'a'];
//        }
//        curr.isLeaf = true;
//    }

    public void addWord(String word){
        if(root== null){
            root = new TrieNode();
        }
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c-'a']==null){
                curr.children[c-'a']= new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isLeaf = true;
    }
    public boolean searchUtil(String word, TrieNode curr) {
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(c=='.') {
                String newWord = word.substring(i+1);

                boolean found = false;
                //Recursively go to each child and find the feasible path.
                for(int j =0; j<26; j++){
                    if (curr.children[j] != null) {
                        found = searchUtil(newWord, curr.children[j]);
                    }

                    if(found){
                        return true;
                    }
                }
                return false;
            }
            else {
                if (curr.children[c - 'a'] == null) {
                    return false;
                }
                curr = curr.children[c - 'a'];
            }
        }
        return curr != null && curr.isLeaf;
    }


    public boolean search(String word){

        TrieNode curr = root;
        return searchUtil(word,curr);


    }

    public static void main(String arg[]){
        WordDictionary w = new WordDictionary();
        w.addWord("bad");
        w.addWord("dad");
        w.addWord("mad");
        System.out.println(w.search("pad")); // -> false
        System.out.println(w.search("bad")); //-> true
        System.out.println(w.search(".an")); //-> false
        System.out.println(w.search("b..")); //-> true
    }
}
