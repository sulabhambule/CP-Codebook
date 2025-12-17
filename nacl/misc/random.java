import java.util.Random;

class random {
    static final Random rng = new Random();

    static int randInt(int l, int r) {
        return l + rng.nextInt(r - l + 1);
    }

    static long randLong(long l, long r) {
        return l + (Math.abs(rng.nextLong()) % (r - l + 1));
    }
    // use inside the main 
    // int a = randInt(1, 10);
    // long b = randLong(100, 1000);
}
