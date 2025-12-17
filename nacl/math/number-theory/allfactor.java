import java.util.*;

public class allfactor {
    public static void main(String[] args) { }
    static int N = 100000;
    static int[] spf = new int[N + 1]; 
    // store the smallest prime factor of i in spf[i].
    static void spf() {
        for (int i = 2; i <= N; i++) {
            spf[i] = i;
        }
        // Sieve of Eratosthenes modified to find smallest prime factor
        for (int i = 2; i * i <= N; i++) {
            if (spf[i] == i) { // If i is prime
                for (int j = i * i; j <= N; j += i) {
                    if (spf[j] == j) 
                        // Mark spf[j] with the smallest prime factor
                        spf[j] = i;
                }
            }
        }
    }
    static List<Integer> allFactors(int n) {
        List<Integer> fac = new ArrayList<>();
        fac.add(1);
        while (n > 1) {
            int p = spf[n];
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            while (n % p == 0) {
                n /= p;
                cur.add(cur.get(cur.size() - 1) * p);
            }
            List<Integer> next = new ArrayList<>();
            for (int x : fac)
                for (int y : cur)
                    next.add(x * y);
            fac = next;
        }
        return fac;
    }
}