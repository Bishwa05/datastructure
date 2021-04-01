package tree.trie;

import java.util.HashMap;

/**
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer).
 * The string represents the key and the integer represents the value.
 * If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix,
 * and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 */
public class MapSum {

    /** Initialize your data structure here. */
    class TrieNode{
        public int data;
        public TrieNode[] children;

        public TrieNode(){
            data =0;
            children = new TrieNode[26];
        }
    }
    private HashMap<String, Integer> map;
    private TrieNode root;

    public MapSum() {
        this.map = new HashMap<>();
        this.root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode curr = root;

        int k = val;
        if(map.containsKey(key)){
            k = val - map.get(key);
            if(k ==0){
                return;
            }
        }

        map.put(key,val);

        root.data = root.data+k;

        for(int i=0; i<key.length(); i++){
            int index = key.charAt(i)-'a';
            if(curr.children[index]==null){
                curr.children[index]=new TrieNode();
            }
            curr = curr.children[index];
            curr.data = curr.data+k;
        }

    }

    public int sum(String prefix) {
        TrieNode curr = root;

        for(int i =0; i<prefix.length(); i++){
            int index = prefix.charAt(i)-'a';
            if(curr.children[index]==null){
                return 0;
            }
            curr = curr.children[index];
        }
        return curr.data;

    }

    public static void main(String arg[]) {
         MapSum obj = new MapSum();
         obj.insert("apple",3);
         int param_1 = obj.sum("ap");
         System.out.println(param_1);

        obj.insert("app",2);
        int param_2 = obj.sum("ap");
        System.out.println(param_2);

        obj.insert("dc",2);
        int param_3 = obj.sum("a");

        System.out.println(param_3);

    }
}
