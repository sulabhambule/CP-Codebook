import java.util.*;
public class lis {
    public static void main(String[] args) {
        // int[] arr = new int[n];
        List<Long> dp = new ArrayList<>();
        for (long x : arr) {
        // Find the position to replace or extend
          int pos = Collections.binarySearch(dp, x);
          if (pos < 0)
            pos = -(pos + 1); // If not found, get insertion point
            // If pos is within dp, replace the element
            if (pos < dp.size()) {
                dp.set(pos, x);
            } else {
                // Else, extend the subsequence
                dp.add(x);
            }
        }
        // out.println(dp.size()); length of LIS
    }
}


