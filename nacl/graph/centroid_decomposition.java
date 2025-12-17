public class centroid_decomposition {
  // Find the size of the subtree under this node.
  public static int subtreeSize(int node, int par) {
    int res = 1;
    for (int next : adj[node]) {
      if (next == par) {
        continue;
      }
      res += subtreeSize(next, node);
    }
    return (subSize[node] = res);
  }
  
  // Find the centroid of the tree (the subtree with <= N/2 nodes)
  public static int getCentroid(int node, int par) {
    for (int next : adj[node]) {
      if (next == par) {
        continue;
      }
      // Keep searching for the centroid if there are subtrees with more
      // than N/2 nodes.
      if (subSize[next] * 2 > N) {
        return getCentroid(next, node);
      }
    }
    return node;
  }
}
