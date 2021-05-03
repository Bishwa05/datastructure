package tree.binarytree.views;

import com.sun.tools.javac.util.Pair;
import tree.binarytree.BinaryTreeNode;

import java.util.*;

public class TopView
{

    public List view(BinaryTreeNode root){

        Queue<Pair> queue = new LinkedList<>();
        Integer c = 0;

        Pair<Integer, BinaryTreeNode> p = new Pair<>(c, root);
        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        queue.offer(p);

        while(!queue.isEmpty()){
            Pair<Integer, BinaryTreeNode> px =
                queue.poll();
            if(px != null){

                if(px.snd.left != null){
                    Pair<Integer, BinaryTreeNode> py =
                        new Pair<>(px.fst-1, px.snd.left);
                    queue.offer(py);

                }

                if(!tMap.containsKey(px.fst)){
                    tMap.put(px.fst, px.snd.data);
                }

                if(px.snd.right != null){
                    Pair<Integer, BinaryTreeNode> py =
                        new Pair<>(px.fst+1, px.snd.right);
                    queue.offer(py);

                }
            }
        }


        return new ArrayList(tMap.values());
    }


    public static void main(String arg[]){
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);


        TopView t = new TopView();

        t.view(root).forEach(e->{
            System.out.println(e);
        });

    }
}
