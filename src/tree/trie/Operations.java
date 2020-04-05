package tree.trie;

public class Operations {

    public static void constructTrie(String arr[]){

        for(int i=0; i<arr.length; i++) {
            TrieNode.insert(arr[i]);
        }
    }

    public static String longestCommonPrefix(String arr[]){
        constructTrie(arr);

        return TrieNode.traverse();
    }

    public static void main(String arg[]){
        String arr[] = {"hello", "hell", "held"};

        String ans = longestCommonPrefix(arr);

        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
    }
}
