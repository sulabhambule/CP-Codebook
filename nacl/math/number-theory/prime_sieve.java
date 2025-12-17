public  class prime_sieve {
    static final int MAXN = 1_000_000;
    static boolean[] isPrime = new boolean[MAXN];
    public static void main(String[] args) { }
    static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; (long) i * i < MAXN; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAXN; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}