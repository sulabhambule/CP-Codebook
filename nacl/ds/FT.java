
public class FT {
  static int[] fTree;
  public static void main(String[] args) {
    // int[] arr = new int[n + 1]; // 1-based
    // preProcess(arr);
  }
  // 1-based indexing
  static void preProcess(int[] arr) {
    int n = arr.length - 1;
    fTree = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      update(i, arr[i]);
    }
  }
  static int query(int l, int r) {
    return prefixSum(r) - prefixSum(l - 1);
  }
  static int prefixSum(int idx) {
    int sum = 0;
    while (idx > 0) {
      sum += fTree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }
  static void update(int idx, int delta) {
    while (idx < fTree.length) {
      fTree[idx] += delta;
      idx += (idx & -idx);
    }
  }
}
