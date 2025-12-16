public class LiChaoTree {

    // Represents a line y = mx + c
    static class Line {
        long m, c;

        public Line(long m, long c) {
            this.m = m;
            this.c = c;
        }

        // Evaluates the line at a given x-coordinate
        long eval(long x) {
            return m * x + c;
        }
    }

    // Node of the Li-Chao Tree
    static class Node {
        Line line;
        Node left, right;

        public Node(Line line) {
            this.line = line;
        }
    }

    private Node root;
    private final long minCoord;
    private final long maxCoord;
    private final Line identityLine; // Represents "no line" or infinity for min queries

    // Constructor for the Li-Chao Tree
    // minCoord and maxCoord define the range of x-values the tree will handle.
    // identityLine should return a very large value for min queries (or very small for max queries)
    public LiChaoTree(long minCoord, long maxCoord) {
        this.minCoord = minCoord;
        this.maxCoord = maxCoord;
        // For minimum queries, an identity line should return a very large value.
        // Using Long.MAX_VALUE for 'c' and 0 for 'm' ensures it's always "worse" than any real line.
        this.identityLine = new Line(0, Long.MAX_VALUE);
        this.root = new Node(identityLine);
    }

    // Adds a new line to the tree
    public void addLine(Line newLine) {
        addLine(root, minCoord, maxCoord, newLine);
    }

    private void addLine(Node node, long currentMin, long currentMax, Line newLine) {
        long mid = currentMin + (currentMax - currentMin) / 2;
        boolean leftBetter = newLine.eval(currentMin) < node.line.eval(currentMin);
        boolean midBetter = newLine.eval(mid) < node.line.eval(mid);

        if (midBetter) {
            // If the new line is better at the midpoint, swap it with the current line
            Line temp = node.line;
            node.line = newLine;
            newLine = temp; // The old line now becomes the 'new' line to be pushed down
        }

        // If the interval is a single point, we are done
        if (currentMin == currentMax) {
            return;
        }

        // Decide which child to push the 'worse' line to
        if (leftBetter != midBetter) { // Intersection point is in the left child's range
            if (node.left == null) {
                node.left = new Node(identityLine);
            }
            addLine(node.left, currentMin, mid, newLine);
        } else if (leftBetter == midBetter && leftBetter == false) { // Intersection point is in the right child's range
            if (node.right == null) {
                node.right = new Node(identityLine);
            }
            addLine(node.right, mid + 1, currentMax, newLine);
        }
        // If leftBetter == midBetter == true, it means the new line is better across the whole interval
        // and the old line is completely dominated, so no need to push it down.
    }

    // Queries the minimum value at a given x-coordinate
    public long query(long x) {
        return query(root, minCoord, maxCoord, x);
    }

    private long query(Node node, long currentMin, long currentMax, long x) {
        if (node == null) {
            return identityLine.eval(x); // No line in this path, return identity value
        }

        long res = node.line.eval(x);

        if (currentMin == currentMax) {
            return res; // Reached a leaf node
        }

        long mid = currentMin + (currentMax - currentMin) / 2;
        if (x <= mid) {
            res = Math.min(res, query(node.left, currentMin, mid, x));
        } else {
            res = Math.min(res, query(node.right, mid + 1, currentMax, x));
        }
        return res;
    }

    public static void main(String[] args) {
        // Example Usage:
        // Create a Li-Chao Tree for x-coordinates from -1000 to 1000
        LiChaoTree lct = new LiChaoTree(-1000, 1000);

        // Add some lines: y = mx + c
        lct.addLine(new Line(1, 5));   // y = x + 5
        lct.addLine(new Line(-1, 10)); // y = -x + 10
        lct.addLine(new Line(0, 7));   // y = 7

        // Query minimum values at different x-coordinates
        System.out.println("Min at x = 0: " + lct.query(0));   // Expected: min(5, 10, 7) = 5
        System.out.println("Min at x = 2: " + lct.query(2));   // Expected: min(7, 8, 7) = 7
        System.out.println("Min at x = 6: " + lct.query(6));   // Expected: min(11, 4, 7) = 4
        System.out.println("Min at x = -5: " + lct.query(-5)); // Expected: min(0, 15, 7) = 0

        lct.addLine(new Line(2, -3)); // y = 2x - 3
        System.out.println("Min at x = 0 (after new line): " + lct.query(0)); // Expected: min(5, 10, 7, -3) = -3
        System.out.println("Min at x = 2 (after new line): " + lct.query(2)); // Expected: min(7, 8, 7, 1) = 1
    }
}
