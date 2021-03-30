package tree.leetcode;

/**
 * 1483. Kth Ancestor of a Tree Node
 *
 * https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
 *
 * Use of Binary lifting
 *
 */
public class TreeAncestor
{
    int[][] up;
    int[] depth;
    int LOG=0;
    public TreeAncestor(int n, int[] parent) {

        while((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[n][LOG];
        depth = new int[n];
        parent[0] = 0;


        for(int v = 0; v < n; v++) {
            up[v][0] = parent[v];
            if(v != 0) {
                depth[v] = depth[parent[v]] + 1;
            }
            for(int j = 1; j < LOG; j++) {
                up[v][j] = up[ up[v][j-1] ][j-1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        if(depth[node] < k) {
            return -1;
        }
        for(int j = LOG - 1; j >= 0; j--) {
            if(k >= (1 << j)) { //get the bit
                node = up[node][j];
                k -= 1 << j;
            }
        }
        return node;
    }
}
