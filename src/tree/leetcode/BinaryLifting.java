package tree.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Time complexity
 *  O(nlogn) to preprocess the tree.
 *  O(logN) for each lca query.
 */
public class BinaryLifting
{
    static ArrayList<Integer> g[];
    static int memo[][], lev[], log;

    // Pre-processing to calculate values of memo[][]
    static void dfs(int u, int p)
    {

        // Using recursion formula to calculate
        // the values of memo[][]
        memo[u][0] = p;
        for (int i = 1; i <= log; i++)
            memo[u][i] = memo[memo[u][i - 1]][i - 1];
        for (int v : g[u]) {
            if (v != p) {

                // Calculating the level of each node
                lev[v] = lev[u] + 1;
                dfs(v, u);
            }
        }
    }

    // Function to return the LCA of nodes u and v
    static int lca(int u, int v)
    {
        // The node which is present farthest
        // from the root node is taken as u
        // If v is farther from root node
        // then swap the two
        if (lev[u] < lev[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Finding the ancestor of u
        // which is at same level as v
        for (int i = log; i >= 0; i--) {
            if ((lev[u] - (int)Math.pow(2, i)) >= lev[v])
                u = memo[u][i];
        }

        // If v is the ancestor of u
        // then v is the LCA of u and v
        if (u == v)
            return u;

        // Finding the node closest to the root which is
        // not the common ancestor of u and v i.e. a node
        // x such that x is not the common ancestor of u
        // and v but memo[x][0] is
        for (int i = log; i >= 0; i--) {
            if (memo[u][i] != memo[v][i]) {
                u = memo[u][i];
                v = memo[v][i];
            }
        }

        // Returning the first ancestor
        // of above found node
        return memo[u][0];
    }

    // Driver code
    public static void main(String args[])
    {

        // Number of nodes
        int n = 9;
        g = new ArrayList[n + 1];

        // log(n) with base 2
        log = (int)Math.ceil(Math.log(n) / Math.log(2));
        memo = new int[n + 1][log + 1];

        // Stores the level of each node
        lev = new int[n + 1];

        // Initialising memo values with -1
        for (int i = 0; i <= n; i++)
            Arrays.fill(memo[i], -1);
        for (int i = 0; i <= n; i++)
            g[i] = new ArrayList<>();

        // Add edges
        g[1].add(2);
        g[2].add(1);
        g[1].add(3);
        g[3].add(1);
        g[1].add(4);
        g[4].add(1);
        g[2].add(5);
        g[5].add(2);
        g[3].add(6);
        g[6].add(3);
        g[3].add(7);
        g[7].add(3);
        g[3].add(8);
        g[8].add(3);
        g[4].add(9);
        g[9].add(4);
        dfs(1, 1);
        System.out.println("The LCA of 6 and 9 is " + lca(6, 9));
        System.out.println("The LCA of 5 and 9 is " + lca(5, 9));
        System.out.println("The LCA of 6 and 8 is " + lca(6, 8));
        System.out.println("The LCA of 6 and 1 is " + lca(6, 1));
    }
}
