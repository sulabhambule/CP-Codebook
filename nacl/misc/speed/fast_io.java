// use Scanner in Simple tasks and if requuired then 
// use fast io



import java.io.*;
import java.util.*;

public class fast_io {
  public static PrintWriter out = 
  new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();

  public static void main(String[] args) throws IOException {
    int cp = in.nextInt();
    while (cp-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    public FASTIO() {
      br = new BufferedReader(
        new InputStreamReader(System.in)
      );
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

  }

}