
public class SegTreeSimple { }
class SegmentTree {
  private int[] tree; private int n;
  public SegmentTree(int[] arr) {
    this.n = arr.length; this.tree = new int[4 * n];
    build(arr, 0, 0, n - 1);
  }
  private void build(int[] arr,int node,int start,int end) {
    if (start == end) {
      tree[node] = arr[start]; return;
    }
    int mid = (start + end) / 2;
    build(arr, 2 * node + 1, start, mid);
    build(arr, 2 * node + 2, mid + 1, end);
    tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
  }
  public void update(int index, int value) {
    update(0, 0, n - 1, index, value);
  }
  private void update(int node,int st,int en,int id,int val) {
    if (st == en) {
      tree[node] = val; return;
    } int mid = (st + en) / 2;
    if (id <= mid) {
      update(2 * node + 1, st, mid, id, val);
    } else {
      update(2 * node + 2, mid + 1, en, id, val);
    }
    tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
  }
  public int query(int left, int right) {
    return query(0, 0, n - 1, left, right);
  }
  private int KthOne(int node,int start,int end,int k) {
    if (start == end) return start;
    int leftCount = tree[2 * node + 1];
    if (k < leftCount) {
      return KthOne(2*node+1,start,(start+end)/2,k);
    } else {
      return KthOne(2*node+2,(start+end)/2+1,end,k-leftCount);
    }
  }
  public int findKthOne(int k) {
    return KthOne(0, 0, n - 1, k);
  }
  private int query(int node,int start,int end,int l,int r) {
    if (r < start || l > end) return 0;// outside
    if (l <= start && end <= r) return tree[node];// inside
    int mid = (start + end) / 2;
    int leftSum = query(2 * node + 1, start, mid, l, r);
    int rightSum = query(2 * node + 2, mid + 1, end, l, r);
    return leftSum + rightSum;
  }
}
