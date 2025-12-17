import java.io.*;
import java.util.*;
/*
 * parent[node][i] = parent[parent[node][i - 1]][i - 1];
 * This means that the 2^i th parent of the node is
 *  2^i - 1 th parent of the node ka 2^i-1 th parent
 */
public class BinaryLiftting {
  private static final int MAX_LOG = 20;
  private static void solve() {
    int[][] par = new int[n + 1][MAX_LOG];
    dfs(1, 0, adj, par);
  }
  private static void dfs(int node, int parent,
     List<List<Integer>> adj, int[][] par) {
    par[node][0] = parent;
    for (int j = 1; j < MAX_LOG; j++) {
      par[node][j] = par[par[node][j - 1]][j - 1];
    }
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent)
        dfs(adjNode, node, adj, par);
    }
  }
  static int Kthparent(int node, int k, int[][] par) {
    for (int i = MAX_LOG - 1; i >= 0; i--) {
      if (((1 << i) & k) != 0) {
        node = par[node][i];
        if (node == 0) return 0;
      }
    }
    return node;
  }
}