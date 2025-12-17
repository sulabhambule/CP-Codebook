public class SparseTable {
  int[][] st;
  int[] log;
  public SparseTable(int[] arr) {
    int n = arr.length;
    int K = 32 - Integer.numberOfLeadingZeros(n);
    st = new int[n][K];
    log = new int[n + 1];
    log[1] = 0;
    for (int i = 2; i <= n; i++) {
      log[i] = log[i / 2] + 1;
    }
    for (int i = 0; i < n; i++) {
      st[i][0] = arr[i];
    }
    for (int j = 1; j < K; j++) {
      for (int i = 0; i + (1 << j) <= n; i++) {
        st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
      }
    }
  }
  public int query(int l, int r) {
    int len = r - l + 1;
    int j = log[len];
    return Math.min(st[l][j], st[r - (1 << j) + 1][j]);
  }
}

