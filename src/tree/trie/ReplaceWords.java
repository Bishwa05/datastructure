package tree.trie;

import java.util.Arrays;
import java.util.List;

public class ReplaceWords {
    class TrieNode{
        public TrieNode[] children;
        boolean isLeaf;

        TrieNode(){
            children = new TrieNode[26];
            isLeaf=false;
        }
    }

    public void insert(String word, TrieNode root) {
        TrieNode curr = root;

        for(char c : word.toCharArray()){
            if(curr.children[c-'a']==null){
                curr.children[c-'a']= new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isLeaf = true;
    }

    public String getRepalcedWord(TrieNode root, String sWord){
        TrieNode curr = root;

        for(int i =0; i< sWord.length(); i++){
            char c = sWord.charAt(i);
            if(curr.children[c-'a']==null){
                return sWord;
            }
            curr = curr.children[c-'a'];
            if(curr.isLeaf){
                return sWord.substring(0,i+1 );
            }
        }
        return sWord;

    }


    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();

        for(String dictWord: dict){
            insert(dictWord, root);
        }

        StringBuilder sb = new StringBuilder();
        String[] senWords = sentence.split(" ");


        for(String sWords : senWords) {
            sb.append(" ").append(getRepalcedWord(root,sWords));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    public static void main(String args[]){
        String sentence = "the cattle was rattled by the battery";

        String[] dict = {"cat","bat","rat"};
        ReplaceWords r = new ReplaceWords();
        System.out.println(r.replaceWords(Arrays.asList(dict), sentence));

    }
}
