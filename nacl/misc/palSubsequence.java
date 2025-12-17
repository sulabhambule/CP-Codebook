
public class palSubsequence {
  public static void main(String[] args) {
    solve();
  }
  public static void solve() {
    for (int gap = 0; gap < n; gap++) {
      for (int i = 0, j = gap; j < n; i++, j++) {
        if (gap == 0) {
          // single char is a palindrome
          dp[i][j] = 1;
        } else if (gap == 1) {
          // if both char are same then 3 else 2
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 3;
          } else {
            dp[i][j] = 2;
          }
        } else {
          // the we have two cases
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
          } else {
            dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
          }
        }
      }
    }
    // println(dp[0][n - 1]);
  }
}
