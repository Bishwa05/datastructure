package tree.trie;

public class WordFilter
{
    class TrieNode {
        int weight;
        boolean isWord;
        TrieNode[] children;

        public TrieNode(){
            weight = -1;
            children = new TrieNode[256];
        }
    }

    class Trie {
        TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        public void insert(String word, int weight){
            if(word == null || word.length() ==0) return;

            TrieNode curr = root;

            for(char c:word.toCharArray()){
                int index = c;
                if(curr.children[index] == null){
                    curr.children[index] = new TrieNode();
                }
                curr =curr.children[index];
                curr.weight = weight;
            }
            curr.isWord = true;
        }


        public TrieNode search(String word){
            if(word == null || word.length() == 0)return null;

            TrieNode curr = root;

            for(char c: word.toCharArray()){
                int index = c;
                if(curr.children[index] == null){
                    return null;
                }
                curr = curr.children[index];
            }
            return curr;
        }

        public int getWeight(String word){
            TrieNode node = search(word);
            return node == null? -1: node.weight;
        }
    }

    Trie trie;
    public WordFilter(String[] words){
        trie = new Trie();

        for(int i =0; i<words.length; i++){
            String curWord = words[i];
            String key = "#"+ curWord;
            trie.insert(key, i);

            for(int j = curWord.length() -1; j>=0; j--){
                key = curWord.charAt(j) +key;
                trie.insert(key, i);
            }
        }
    }

    public int f(String prefix, String suffix){
        return trie.getWeight(suffix +"#"+ prefix);
    }
}
