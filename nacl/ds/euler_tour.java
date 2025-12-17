import java.io.*;
import java.util.*;
public class euler_tour {
  static List<Integer>[] adj;
  static int time = 0;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    // out.close();
  }

  static void solve() {
    long[] euler = new long[2 * n];
    int[] inTime = new int[n + 1];
    int[] outTime = new int[n + 1];

    dfs(1, -1, inTime, outTime);

    for (int i = 1; i <= n; i++) {
      euler[inTime[i]] = v[i - 1];
      euler[outTime[i]] = -v[i - 1];
    }

    SegTree seg = new SegTree();
    seg.init(2 * n); // Euler array size
    seg.build(0, 2 * n - 1, 0, euler);

    while (q-- > 0) {
      int type = in.nextInt();
      if (type == 1) {
        int s = in.nextInt();
        long x = in.nextLong();
        seg.update(0, 2 * n - 1, inTime[s], 0, x);
        seg.update(0, 2 * n - 1, outTime[s], 0, -x);
      } else {
        int s = in.nextInt();
        out.println(seg.query(0, 2 * n - 1, 0, inTime[s], 0));
      }
    }
  }

  private static void dfs(int node, int parent, int[] inTime, int[] outTime) {
    inTime[node] = time++;
    for (int adjNode : adj[node]) {
      if (adjNode != parent) {
        dfs(adjNode, node, inTime, outTime);
      }
    }
    outTime[node] = time++;
  }
}